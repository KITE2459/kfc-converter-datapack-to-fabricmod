# 카트 물리: 한 틱 처리 흐름(kartriderpack v2.8.7)

본 문서는 `data/kartmobil/function/main.mcfunction`을 기점으로, 그 하위 함수 전반의 로직을 추적하여 "한 틱" 동안 카트에 적용되는 처리 순서와 각 단계에서 사용/갱신되는 수치(scoreboard, storage, 태그)를 상세히 정리합니다. 숫자 표기는 소수점 없는 scoreboard 정수 연산 기준입니다.

참조 표시 규칙
- 함수 경로는 백틱으로 표기합니다. 예: `kartmobil:move/move`
- scoreboard objectives와 상수 플레이어는 각각 `@s`(현재 카트), `#...`(상수/임시 변수, objective 명은 뒤에 붙음) 형식으로 표기합니다.
- 플레이어/엔티티 태그는 `tag=...`로 표기합니다.


## 핵심 개념과 단위

- 속도 단위: 댓글에 명시된 바와 같이 "수치 1000 = 1 m/s". 즉 `@s kartmove` 1000은 실제 속도 1 m/s를 의미합니다.
- UI용 속도(`#kartspeed kartmove`): `#kartspeed = @s kartmove / #kart278(kartmain)`으로 계산(주석상 UI 표시용). 일부 UI에는 `#kart139`를 사용하여 km/h 2배 표기를 하기도 합니다.
- 엔진 타입(`@s kart-engine`):
  - 0:X, 1:EX, 2:jiu, 3:new, 4:z7, 5:v1, 6:a2, 7:1.0, 8:pro, 9:rally
  - 1000..1005: 특수/혼합 모드(예: 1002=jiu 기반 WASD, 1003=mario-like, 1005=gear 모드 등)
- 주요 엔티티 역할(카트는 item_display 기반 복합 구조):
  - 본체 `@s`(주로 item_display): 카트의 속도/상태 scoreboard의 주체
  - 승객 플레이어 `@p[tag=kartpassenger]`: 조작 입력(키 프레디킷)과 UI 대상
  - 방향표시 `@s[tag=kartdirection,type=item_display]`: 진행 방향/모델 회전 보조
  - 데이터 캐리어 `@s[tag=kartdatacarrier,type=item_display]`: 플레이어 시선(yaw) 전달자
  - 새들(모델) `@s[tag=kartmodelsaddle,type=item_display]`: 모델 표시에 사용


## 한 틱 처리 전체 흐름(상위 → 하위)

아래 순서는 `kartmobil:function/main.mcfunction`의 실행 순서에 따릅니다.

1) 자동 회수 및 파손 가드
- `function kartmain:break-kart/break`
  - 이미 다른 플레이어가 타고 있는 등 파손 조건이면 즉시 파손 처리(사운드/파티클) 후 `kartmain:break-kart/kill-kart`로 종료.

2) 태그/리스너 세팅(입력・사운드 기준점)
- 승객 지정: `execute on passengers ... tag @p[...] add kartpassenger`
- 청취자 지정: `@a[tag=kartpassenger]`와 주변 관전 모드 플레이어에 `kart-listener` 부여(사운드/액션바 수신용)

3) 시선 전달(데이터 캐리어 회전)
- `execute rotated as @p[tag=kartpassenger] on passengers run rotate @s[tag=kartdatacarrier] ~ 0`
  - 플레이어 yaw를 `kartdatacarrier`에 복사

4) 플레이어 숨기기(옵션)
- `tag=karthideplayer`면 승객에게 `invisibility` 1초 부여(완전 숨김 처리)

5) UI용 속도 계산
- 로그감자의 코멘트: 여기에서 사용하는 속력은 실제 속도계에 나오는 속력의 절반입니다. 즉 속도계 UI가 2배 뻥튀기임

- `#kartspeed = @s kartmove / #kart278`
  - 이후 각종 액션바/게이지 표시에 사용

6) 조작 입력 처리(엔진 분기)
- 기본: `kartmobil:control/control`(단, `kart-engine`가 1004..1005 또는 9가 아니어야)
- 기어: `kartmobil:control/gear-mode/control`(엔진=1005)
- 랠리: `kartmobil:control/rally-mode/control`(엔진=9)

control/control의 핵심 동작
- 부스트 게이지 UI: 엔진별 `control/boost-gauge/...` 호출
- 전진/브레이크
  - W: `kartmobil:control/accel` → `@s kartmove += @s kartaccel`(음수 가속은 부호 반전 적용), 부스트 중이면 `@s kartmove += @s kartboost`(엔진/조건별 가감) 및 엔진별 듀얼부스트 가속 추가
  - S: `kartmobil:control/brake` → 충돌 타이밍(5..8) 제외 `@s kartmove -= 1000`, 음수 방지
- 키 이벤트 태그 업데이트: `kart-w-press/release`, `kart-boost-press`, `kart-space-press/release`
- WASD 엔진(1002/1003): 전용 제어 진입
- A2 특성: 드리프트 직후 앞키 떼면 순간 감속 보정
- EX 특성: 좌/우 입력으로 즉시 부스트 사용
- 부스트 사용 규칙:
  - `@s kartboosttime` 1틱씩 감소
  - 조건 충족 시 `kartmobil:control/useboost`: 사운드/파티클, `@s kartboosttime = @s kartboostduration`, `@s kartboostcount -= 1`
  - 엔진 1002는 자동부스트 조건 보유

7) 라이트 토글
- `kart-w-press/release`+모델/라이트 태그 조건에 따라 `bright-blocks/light-on|off`

8) 바닥과의 거리(`#floor-distance floor-distance`)
- 0으로 초기화 후 `kartmobil:detect-floor-distance` 재귀로 1블록 단위 측정(최대 5)

9) PRO 엔진 후처리
- `kartmobil:pro-always-transform`: 부스트 블록 태그 정리, 모델 표시 등 시각 효과 정리

10) 이동 상태 이름표 처리 - (Tick Sync 모드 대응 기능)
- `@s kartmove > 0`면 `CustomName` 제거, 아니면 `"mcrider-stop"`로 설정

11) 사운드/FOV
- 엔진별 사운드 함수 호출(`kartmobil:soundandfov/.../sound`), FOV 연동 기능 포함

12) 이동 본동작 분기
- 일반 카트: `@s kartmove > 0`면 `kartmobil:move/move`
- 보트 엔진(1004): `kartmobil:boat-engine/main`

13) 중력 및 수직 운동(점프/착지/피치)
- 지면 판정 시 `@s kartmovey += 250`
- `#movetp-y = @s kartmovey`
- 공중에서 하강 중 착지 판별 시 `kartmobil:move/movetp/landing`
- 수직 이동 보정: `#movetp-y` 크기에 따라 `moveup|movedown` 호출 및 {`kartmodelsaddle` 피치 회전(±7.5°) - 그냥 모델링 연출임!}

14) R키 고스팅 표시(충돌 후 점멸)
- `kartcollisiontime` 특정 틱에 `rkey-ghost/show|hide` 순차 호출

15) 충돌 / 뭉개기 쿨타임 감소
- `@s kartcollisiontime` 1씩 감소(1..), `@s kartweirdcollisioncount`도 조건부 감소

16) 모델 회전 보조(서버사이드 모드 분기)
- `modeldir-support-vanilla` 또는 `modeldir-support-sidite`
- `kartdatacarrier`/`kartmodelsaddle` 회전 동기화

17) 태그 정리
- `kartpassenger`, `kart-listener` 제거

## 이동 코어: `kartmobil:move/move`

1) 준비/각도 측정
- `tag @s add kartself`
- `#kartangle`: 플레이어 yaw(데이터캐리어) × 10 저장
- `#kartdirectionangle`: 모델 방향(item_display) yaw × 10 저장
- `kartmobil:move/steer/getsteerangle`
  - `#kartangle = #kartangle - #kartdirectionangle`(가끔 1800 보정), `#kartangleabs = |#kartangle|`

2) 기어(엔진=1005) 감속
- 드리프트 중이 아니고, 특정 각도 이상이면 `@s kartmove`를 두 번에 걸쳐 38씩 감산(고속 선회 감속)

3) 드리프트 시작/유지/종료
- 시작 조건: 엔진/각도 범위별로 `kartmobil:move/steer/drift-start`(태그 `kart-drifting` 부여, 팀부 게이지 스냅샷)
- 유지: `kartmobil:move/steer/drift`
  - 매 틱 `@s kartdrifttime += 1`
  - 부스트 게이지 적립: 속도/지면/엔진(A2, 1.0 특성) 조건에 따라 `@s kartboostgauge += ...`
  - 코너 감속: 속도 60 이상이면 `@s kartmove -= @s kartcorner`, `-= #kartangleabs`, `+= #kartdecel(= angleabs/6, 단 엔진7은 0)`
  - 드래그 가속(모드별 / 끌기 보정) - 끌기 보정은 톡톡이가 없는 모든 엔진에 적용
  - 저속 무감속: 속도 낮으면 보정
  - 이펙트: `drifteffect`
- 종료 조건: 엔진/각도/드리프트 시간에 따라 `drift-end`(A2는 8틱 이상 시 추가 쿨타임 처리)
- 부스트 지급: 엔진 1003/9 제외, `@s kartboostgauge >= 2000`이면 `getboost`로 게이지 초기화 + 카운트 증가

4) 모델 회전(시야-모델 보정)
- 엔진 7(1.0) 전용 모델 회전 또는 일반 모델 회전

5) 부스트 시스템
- `move/boost-effect/boost-main`: 엔진(JIU/기어/랠리) 자동 변신, 부스트 허브 처리
- `@s kartboostpadtime` 카운트 다운

6) 러너 애니메이션
- `kart-run-anime`로 간격 제어, 속도/플레이어수 조건에 따라 상태 전환

7) A2/A8 즉시부스트(순부)
- `move/instant-boost/instant-boost`
  - W-press + 드리프트 시간>0 + 지면 근접 시 `use` 실행
  - `a2-using-instant-boost` 중에는 엔진6(+350/틱), 엔진8(+65/틱) 추가 가속, FOV/표시 보정
  - 게이지 관리: `storage minecraft:kartmain instantgauge`, 이후 `.../gauge` 호출

8) V1 익시드 처리
- 엔진5(v1) 전용: `v1-exceed/main`

9) 점프
- `@p[kartpassenger, space]` + `@s[tag=can-jump, !kart-jumped]` + `@s kartmovey < 1000`
- `move/jump`: `@s kartmovey = -2000`, 사운드, `tag=kart-jumped`

10) 전진(이동량 누적 및 TP)
- 로그감자의 코멘트: kartmove-remain은, 카트의 최소 이동 속도가 1000이기에 1~999까지는 무시되는 문제를 해결하기 위해
이전 틱에 남은 이동을 다음 틱에 보상하는 알고리즘에 사용되는 변수임. 1.0엔진에는 그런 게 없었기 때문에 무시됨

- 정지 태그면 `@s kartmove = 0`
- 엔진7이면 남은 이동량 `@s kartmove-remain = 0`
- 누적: `kartmove-remain += kartmove`; 10000 이상이면 0으로 리셋('차지대시 버그' 방지용)
- 1틱 전의 속도(`kart-old-velocity`)엔티티에 속력과 방향 설정(밀리초 시간 연산에 사용)
- `kartmove-remain >= 1000`이면 방향 회전 기준으로 `movetp/moveforward` 재귀 실행
  - stepdown/stepup/저속 스텝업
  - 대각선 벽 통과 판정(막히면 리턴)
  - 점프/부스트 패드 감지
  - 8000/4000/2000/1000 단위로 전방 블록 클리어 시 소량 TP(`tp-bug-fix/tp`), 남은 양 차감 후 재귀
  - 8방향 벽 충돌 검사 시 `wallcollision/main` → 조건 충족 시 소리/불꽃, 기어 엔진 추가 감속, `wall-crash`시 전용 처리(충돌 루프)

11) 드래프트 타이머/보너스
- `@s kartmain` 60..200 구간 카운트, 이후 리셋
- JIU 특성: 이 구간에 추가 공기저항 보정(+3)

12) 마찰/지면 감속/공기저항
- 기본 감속: `@s kartmove -={20}`
- 소울샌드 감속: 플레이어에 `kartdeceleration` 태그 후 `@s kartmove -= 350`
- 공기저항: 엔진별 호출
  - 기본: `move/airresist`
  - 기어: `move/airresist-gear`(기어 단계에 따른 상수 계산을 storage에 저장 후 사용)
  - 랠리: `move/airresist-rally`
- 공기저항 수식(공통 구조)
  - `#kartairresist = @s kartmove`
  - `#kartairresistconst = @s kartspeed`(속도 스펙 기반)
  - 부스트 중(또는 pro 엔진)은 `#kartairresistconst += 84`
  - JIU 특성: `@s kartmain` 특정 구간(드래프트 발동 직후)에서 `+3`
  - 비례-제곱 형태로 감속량 계산 후, 속도의 부호에 맞게 가/감산

13) 드리프트 물리(드리프트 탈출 보정)
- `move/steer/drift-physics/main`
  - 드리프트 계수 `#kartdrift = 230400 / (카트/플레이어 kartdrift 합)`을 `storage kartmain driftmacro`로 전달
  - 가속 중/후진/저속 각 상황에서 `driftmacro`/`driftmacro-back`/`driftmacro-lowspeed` 호출(시야 기준 전방 레이)
  - 마지막에 `@p kartdrift = 0` 초기화

14) 임시 태그 정리
- `kartdirectiontemp`, `kartself` 제거


## 착지/수직 이동 관련
- 로그감자의 코멘트: 이 부분은 좀 빡빡이같이 개발해서 양수면 떨어지는 거고 음수면 올라가는 겁니다. 점프를 만들거라고 생각을 못 했음

- `movetp/landing`
  - 큰 낙하 시 효과음/파티클, 피치 떨림 연출(-7.5°), 큰 낙하 클라우드
  - `@s kartmovey = 0`, `tag kart-jumped` 제거
  - `mad-crash` 태그 시 랜덤 추가 점프(감속) 처리
- `moveup`/`movedown`
  - 수직 충돌 회피용 마이크로 TP(`tp-bug-fix/tp`)와 함께 `#movetp-y`를 단계적으로 0 방향으로 보정

## 충돌 시스템(요약)

- 벽 충돌: `movetp/wallcollision`에서 조건 충족 시 소리/불꽃, 기어 엔진은 추가 감속, `wall-crash` 루프 진입 가능
- 카트-카트 충돌: `move/collision/collisionmain` 내부에서 속도 교환(`swapvelocity`), `kartcollisiontime = 8` 설정, `mad-crash`/충돌 게이지 보정 등
- R-key 고스트: `kartcollisiontime` 특정 틱에서 `show/hide` 점멸


## 부스트/게이지/표시 체계
- 부스트 사용: `control/useboost` → 사운드, `@s kartboosttime = @s kartboostduration`, `@s kartboostcount -= 1`
- 게이지 적립: 드리프트/속도/지면/패드/엔진 특성에 따라 `@s kartboostgauge += ...`; 임계 도달 시 `move/steer/getboost`로 카운트 증가/초기화
- UI(ActionBar): `control/boost-gauge/...`에서 `storage kartactbar message[]`를 구성 후 액션바 표시
  - 기본형은 좌우 게이지와 속도계(표시 속도는 `@s kartmove`를 상수로 나눠 표기)
  - 1.0/mario 모드는 별도 레이아웃 사용


## 주요 scoreboard와 의미(요지)

- `@s kartmove` 현재 속도(1000=1 m/s)
- `@s kartmove-remain` 이번 틱에 이동하고 남은 것(전방 TP 세분화용) - 이론상 0~999까지
- `@s kartmovey` 수직 이동량(착지/점프/피치 효과에 사용)
- `@s kartaccel` 가속력(가/감속 기초)
- `@s kartboost` 부스트 가속량(엔진/상태에 따라 가산)
- `@s kartboosttime` 남은 부스트 시간, `@s kartboostduration` 부스트 사용 시 세팅되는 기준값
- `@s kartboostcount` 보유 부스트 개수, `@s kartmaxboostcount` 최대치
- `@s kartboostgauge`, `@s kartboostgaugecharge` 부스트 게이지 및 추가 적립량
- `@s kartboostpadtime` 패드 효과 잔여 시간
- `@s kartcorner` 코너 감속 상수(속도 60 이상에서 적용)
- `@s kartdrifttime` 드리프트 지속 시간(틱)
- `@s kartdrift` 드리프트 탈출력(플레이어와 합산해 계수 계산)
- `@s kart-teamboost-gauge` 팀부 게이지 스냅샷/증감
- `@s kartmain` 드래프트 타이머(60..200 주기)
- `@s kart-run-anime` 러닝 애니메이션 틱 카운터
- `@s kartcollisiontime`, `@s kartweirdcollisioncount` 충돌 관련 쿨타임/보정 카운터
- `@s kartspeed` 공기저항 상수 계산의 기준 속도 스펙
- `@s kart-engine` 엔진 타입(동작 전반 분기)

임시/상수(#, objective)
- 코멘트: (반복)#kartspeed는 실제 표시되는 속도계의 절반입니다

- `#kartspeed kartmove`(내부 계산용), `#kart278 kartmain`, `#kart139 kartmain` 등 상수
- `#kart360/#kart6/#kart4/#kart2/#kart-1 kartmain` 등 다목적 상수
- `#kartangle/#kartdirectionangle/#kartangleabs kartmain` 조향/드리프트용 각도(배수=10)
- `#kartairresist/#kartairresistconst kartmove` 공기저항 계산 임시값
- `#movetp-y kartmain` 수직 이동량 복사본
- `#term kart-run-anime` 애니 간격

Storage(NBT)
- `storage minecraft:kartmain gear-airresist`(기어/랠리 공기저항 상수 저장)
- `storage minecraft:kartmain instantgauge`(순부 게이지 계산용)
- `storage kartactbar message[]`(액션바 메시지 빌드)

태그(발췌)
- 입력/상태: `kart-w-press/release/pressed`, `kart-boost-press/pressed`, `kart-space-press/release/pressed`
- 드리프트/부스트: `kart-drifting`, `a2-using-instant-boost`, { `kart-boost`, `kart-boost-blocks` - 변신 부스터 '연출'에 들어가는 태그}
- 점프: `can-jump`, `kart-jumped`
- 기타: `kartpassenger`, `kart-listener`, `kartself`, `kartdirectiontemp`, `kart-stop`, `mad-crash`, `wall-crash`, `kartdeceleration`, `karthideplayer`

## 한 눈에 보는 호출 트리(요약)

- `kartmobil:main`
  - `kartmain:break-kart/break`
  - 승객/리스너/회전/숨김/UI 세팅
  - 제어 분기 → `kartmobil:control/control | gear-mode/control | rally-mode/control`
    - `.../boost-gauge/*`(UI)
    - `.../accel`, `.../brake`, `.../useboost` 등
  - 라이트 토글
  - `detect-floor-distance`
  - `pro-always-transform`
  - 이동명표 처리
  - `soundandfov/sound`
  - 이동 본동작 → `kartmobil:move/move`(또는 `boat-engine/main`)
    - 조향/드리프트 블록: `steer/getsteerangle`, `steer/drift*`, `steer/drift-physics/main`
    - 부스트계: `move/boost-effect/*`
    - A2 순부: `instant-boost/*`
    - 전진 TP: `movetp/moveforward` → `stepup/down`, `jump-and-boost/...`, `wallcollision/*`, `tp-bug-fix/tp`
    - 마찰/지면/공기저항: `airresist*`
  - 중력/점프/착지: `movetp/landing`, `moveup/down`
  - R키 고스트 점멸: `rkey-ghost/*`
  - 태그 정리/모델 회전 보조


## 엔진별 주요 차이(핵심만)

- 1.0(7)
  - 드리프트 중 감속이 매우 강함
  - 모델 회전/사운드/FOV 전용 처리
  - 이동 누적량 즉시 소비(남은 이동량 0)
- A2(6), Pro(8)
  - 순부 시스템, 순부 중 추가 가속(+350/틱 또는 +65/틱)
  - A2: 드리프트 직후 앞키 떼는 순간 감속 최소화 로직
- JIU(2)
  - 드래프트(뒤따라가기) 특성/공기저항 보정(특정 타이머 구간 +3)
- EX(1)
  - 좌/우로 즉시 부스트 사용 가능
- 기어(1005)
  - 감속/공기저항이 기어단수(`kartboostcount`)에 따라 다른 멀티플라이어 적용(storage 활용)
- 랠리(9)
  - 별도의 공기저항 멀티플라이어/조향 각도 조건
- 보트(1004)
  - 전용 제어/이동 파이프라인(`boat-engine/*`)


## 디버깅 팁

- 속도/가속 추적: `@s kartmove`, `@s kartaccel`, `@s kartboost`, `@s kartboosttime`
- 드리프트 상태: `tag kart-drifting`, `@s kartdrifttime`, `#kartangleabs`
- 공기저항 확인: `#kartairresist`, `#kartairresistconst`, `@s kartspeed`, 부스트/엔진 플래그
- 이동 TP 경로: `@s kartmove-remain`과 `movetp/moveforward` 분기
- 착지/점프: `@s kartmovey`, `#movetp-y`, `tag kart-jumped`


## 부록: 키 프레디킷(입력)

- `predicate=kartmobil:w|a|s|d|space` 를 통해 승객 플레이어의 입력을 감지합니다.
- `control/*` 계층에서 이 프레디킷을 태그(`kart-w-press` 등)로 변환하여 다음 단계에서 일관되게 사용합니다.


---
문서 범위: 본 README는 `main.mcfunction`에서 직접/간접으로 호출되는 주요 물리/이동/게이지/표시 관련 함수를 추적하여 작성되었습니다. 시각효과(모델 쇼/하이드)나 사운드 전용 하위 함수의 세부 구현은 요약만 포함했으며, 필요 시 해당 파일을 참고하십시오.