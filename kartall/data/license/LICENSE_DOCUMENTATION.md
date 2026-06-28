# 라이센스 데이터팩 문서

## 개요
**라이센스 시스템**은 게임 내 플레이어의 라이센스 진행 상황을 관리합니다. 라이센스 테스트는 시간 제한이 있는 미션으로 구성되어 있으며, 플레이어는 이를 완료하여 새로운 카트와 트랙을 잠금 해제할 수 있습니다. 이 시스템은 미션 초기화, 시간 추적, 성공/실패 조건 확인 및 데이터 저장을 담당합니다.

디렉토리: `gamesystem/data/license/`

---

## 핵심 변수 (스코어보드)

| 스코어보드 | 목표(Objective) | 설명 |
| :--- | :--- | :--- |
| `licensecount` | `dummy` | 현재 라이센스 시도의 내부 틱 카운터입니다. <br> - `0`: 비활성 <br> - `1`: 막 시작함 <br> - `5`: 시작 지점으로 텔레포트 <br> - `61+`: 체크포인트 활성화 |
| `licensestage` | `dummy` | 플레이어가 도전 중인 현재 스테이지/레벨입니다. <br> - `1-3`: 루키 (Rookie) <br> - `4-6`: L3 <br> - `7-9`: L2 <br> - `10-12`: L1 <br> - `13-17`: 프로 (Pro) |
| `time timermain` | `dummy` | 테스트 중 경과 시간을 밀리초(ms) 단위로 추적합니다. 보스바 표시 및 실패 확인에 사용됩니다. |
| `#map trackselect-map-id` | `dummy` | 맵 시스템을 사용하는 고급 라이센스 미션(L2+, 프로)의 맵 ID를 저장합니다. |
| `clear-data licensestage` | `dummy` | 플레이어가 *클리어한* 가장 높은 라이센스 스테이지입니다. (인메모리 스코어보드) |
| `clear-data-from-file` | `dummy` | 스토리지(`license-cleardata:license-data`)와 클리어 데이터를 동기화하는 데 사용됩니다. |

---

## 로직 흐름

### 1. 초기화 (`readytoplay.mcfunction`)
플레이어가 라이센스 도전을 시작할 때:
1.  다른 게임 모드가 실행 중인지 확인합니다.
2.  플레이어가 카트를 들고 있는지 확인합니다 (`custom_data~{kartmobil:1}`).
3.  현재 진행 상황(또는 선택)에 따라 `licensestage`를 설정합니다.
4.  메인 루프를 트리거하기 위해 `licensecount`를 `1`로 설정합니다.
5.  **맵 설정**: 고급 스테이지(L2 일부, L1 일부, 프로)의 경우, `#map trackselect-map-id`을 특정 트랙 ID로 설정합니다.

### 2. 메인 루프 (`loop.mcfunction`)
매 틱마다 실행되는 함수입니다 (주로 글로벌 `tick.json` 또는 메인 게임 루프에서 호출됨).
1.  **데이터 동기화**: `check-data`를 호출하여 파일 스토리지와 스코어보드를 동기화합니다.
2.  **보스바**: 활성 플레이어에게 `minecraft:license` 보스바를 업데이트합니다.
3.  **플레이어 틱**: `licensecount >= 1`인 모든 플레이어에 대해 `license:system/main`을 실행합니다.

### 3. 플레이어 틱 로직 (`system/main.mcfunction`)
테스트 중인 플레이어(`@s`)로서 매 틱마다 실행됩니다:
1.  **초기화 (1 틱)**: `system/init`을 호출하여 타이머, 타이틀, 체크포인트를 리셋합니다.
2.  **카운터**: `licensecount`를 1씩 증가시킵니다.
3.  **하차 체크**: `licensecount >= 20`이고 플레이어가 카트에 타고 있지 않다면 `system/retire`를 호출합니다 (실패 처리).
4.  **카운트다운**: `licensecount <= 100` (5초)인 동안 카운트다운 로직을 실행합니다.
5.  **미션 실행**: `licensestage`에 따라 특정 미션 함수를 호출합니다 (예: `system/rookie/mission1`, `system/pro/mission1`).

---

## 미션 유형

라이센스 미션에는 두 가지 주요 유형이 있습니다:

### 유형 A: 단순 이동 (루키, L3, 초기 L2/L1)
*   **설정**: 플레이어를 하드코딩된 좌표로 직접 텔레포트합니다.
*   **결승선**: 카트 아래 감지되는 `end_stone` 블록입니다.
*   **로직**:
    *   `time timermain`과 제한 시간을 비교합니다.
    *   `end_stone`이 감지되면 완료 처리합니다.
    *   시간이 초과되면 실패 처리합니다.

### 유형 B: 맵 기반 (후기 L2/L1, 프로)
*   **설정**: `license:movetomap`을 사용하여 `#map trackselect-map-id`에 기반한 맵 데이터를 로드합니다.
*   **체크포인트**: 특정 미션은 `checkpoint:system/player-main`을 호출하여 진행 상황을 추적합니다.
*   **결승선**: 카트 아래 `magma_block`을 감지하며, 동시에 `check-pass-last` 태그(모든 체크포인트 통과)가 있어야 합니다.
*   **로직**:
    *   랩(Lap) 로직을 포함합니다 (`trackselect-lap`으로 감지).
    *   시간 초과 또는 코스 이탈 시 실패합니다 (일부 미션은 `grass_block` 감지 시 실패).

---

## 주요 함수

| 함수 경로 | 설명 |
| :--- | :--- |
| `license:system/retire` | "중도 포기"를 처리합니다 (직접 포기 또는 카트 하차). 상태를 리셋하고 로비로 이동시킵니다. |
| `license:system/retirefail` | "실패"를 처리합니다 (시간 초과). 실패 문구를 표시하고 상태를 리셋합니다. |
| `license:system/<stage>/mission<N>complete` | 성공을 처리합니다. 효과음을 재생하고, 다음 스테이지를 잠금 해제하며, 클리어 데이터를 업데이트합니다. |
| `license:movetomap` | 스토리지에서 트랙 데이터를 로드하고 플레이어를 유효한 시작 위치로 텔레포트합니다 (유형 B 미션용). |
| `license:check-data` | `license-cleardata:license-data` (스토리지)와 `clear-data` (스코어보드)를 동기화합니다. |

---

## 데이터 저장 (Persistence)
세이브 데이터는 `storage license-cleardata:license-data`에 저장됩니다.
*   **로드**: `check-data`가 스토리지 값과 스코어보드를 비교합니다. 스토리지가 더 높으면 스코어보드를 업데이트합니다.
*   **저장**: 스테이지를 클리어하면(`mission...complete`), 시스템이 스코어보드와 스토리지 모두를 업데이트합니다.
