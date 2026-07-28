# 최적화 작업 변경 요약 (이번 세션)

기준선 → 최종: **서버 틱 15.88 → 12.35 mspt (-22.2%)**, **모드 틱 10.40 → 7.28 mspt (-30.0%)**
(16인 주행, spark 간격 4ms, 20 TPS 유지 조건에서 실측)

## 신규 파일

| 파일 | 내용 |
|---|---|
| `KfcSectionIndexMixin.java` | **N1** — `TypeFilterableList` 선형 제거 → swap-remove O(1). `@Redirect` 로 Lithium/VMP 와 공존 |
| `KfcEntityTagMixin.java` | **B1** — `Entity.addCommandTag/removeCommandTag` 훅으로 태그 버킷 증분 유지 |

> 두 파일 모두 `convert.py` 가 `Kfc*Mixin.java` 글롭으로 자동 수집하므로 `mixins.json` 에 자동 등록됩니다.

## 변경 파일

### `KfcGen.java`
- **월드 종료 시 전체 캐시 해제** (`resetAll`) — 싱글플레이 재입장 GC 폭주 수정
- **B1** — `markExternalFunction(Identifier)` 로 개정, `fnMask` 로 축별 선별 무효화
- **A4** — 엔티티 홀더 스코어 셀(`ObjRef.idx` + 3-stride 압축 배열)
- **속성 해소 캐시** — `attrEntry`/`attrModId`
- **`withEntityOnly`** — SCS 리바인드에 ND 캐시 적용 + **이름 버그 수정**
  (`getNameForScoreboard()` → `getName().getString()`; 비플레이어에서 UUID 가 들어가던 문제)
- **셀렉터 조기종료** — `allEntitiesAnyType(..., cap)`, `@e[limit=N]` 전수 수집 제거
- **`typeBucketCopy` 틱-세대 공유 복사본**
- **`tagBucketsOnRemove` 대칭화** — 전 버킷 순회 → 엔티티 자기 태그만
- **`TYPE_INDEX`** IdentityHashMap → fastutil
- **이름 캐시 개별 무효화** (`invalidateNameOf`) — CustomName 쓰기가 전역 `NAME_GEN++` 하던 것 수정
- **주기 화해 위상 분산** — 3축이 같은 틱에 몰려 60초마다 스파이크 나던 것 분산
- **`posLoaded`** — 바닐라 `ExecuteCommand.isLoaded` 3조건 1:1 재현
  (`ChunkStatus.FULL` → `ChunkLevelType.ENTITY_TICKING`; 저사양 트랙 복제 버그 수정)
- 진단: `-Dkfc.debug.tagbucket=true` (재구축 사유·유발자), null-id 경고

### `KfcSchedCoherenceMixin.java`
- `CommandFunction` 인자를 캡처해 id 전달 (B1 의 전제)

### `emit.py` (7곳) · `opt_post.py` (1곳)
- `source.withEntity(e)` → `KfcGen.withEntityOnly(source, e)`
- 단, `emit.py` 의 `(x != null ? src.withEntity(x) : null)` 는 null 시맨틱이 달라 **의도적 제외**

## 런타임 토글

```
-Dkfc.sectionidx=off        N1 원복
-Dkfc.taghook=off           B1 태그 훅 원복
-Dkfc.entcells=off          A4 원복
-Dkfc.reconphase=off        주기 화해 위상 분산 원복
-Dkfc.extsel=off            마스크 선별 화해 전체 원복
-Dkfc.debug.tagbucket=true  태그 버킷 재구축 진단
```

## ⚠️ 빌드 시 주의 — 믹스인 동기화

이번 세션에서 **KfcGen 만 갱신하고 믹스인은 구버전으로 빌드**한 사례가 있었습니다.
그 경우 B1 이 조용히 무력화되어 `tagBucket` 이 0.08 → 1.59 mspt 로 20배가 됩니다.

증상 확인:
```
[KFC-TAGBUCKET] gen 유발자: bridgeReconcile=100 | gen명령: schedfn:?:mask127x99
                                                            ^^^ 함수 id 가 null
```
이제 이 경우 부팅 시 `[KFC] *** 경고: markExternalFunction(null)` 이 찍힙니다.

**재변환하면 자동으로 맞습니다.** 기존 `generated_src` 를 재사용한다면
`<group>/mixin/` 6개 파일과 `<mod_id>.mixins.json` 목록을 반드시 함께 동기화하세요.
