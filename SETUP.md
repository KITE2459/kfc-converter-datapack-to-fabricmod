# 셋업 가이드 — 데이터팩을 자바 모드 소스로 변환하기

이 문서대로 따라하면 **데이터팩(.zip 또는 폴더) → 네이티브 Fabric 모드 자바 소스**가
명령 한 줄로 나옵니다. 마인크래프트/Fabric jar는 Gradle이 자동으로 받으니
직접 구할 필요 없습니다.

---

## 0. 폴더 구조 확인

받은 파일들을 한 폴더에 두세요. 이런 모양이면 됩니다:

```
내폴더/
├── convert.py              ← 변환기 (이미 있음)
├── emit.py  assemble.py  KfcGen.java  datapack_io.py
├── HeadlessParser.java     ← 헤드리스 파서
├── build_config.py  parser_runner.py
├── 데이터팩.zip            ← 변환할 데이터팩 (직접 넣기)
│
└── gradle_project/         ← 빌드 환경 (이 폴더 통째로 포함)
    ├── build.gradle
    ├── settings.gradle
    ├── gradle.properties
    ├── gradlew  gradlew.bat
    └── gradle/wrapper/...
```

`HeadlessParser.java` 와 `gradle_project/` 가 **같은 폴더 안에 나란히** 있어야 합니다
(build.gradle 이 `../HeadlessParser.java` 를 참조하므로).

---

## 1. 사전 준비 (한 번만)

### ⚠️ JDK 21 설치 (정확히 21 — 25/26 아님!)
마인크래프트 1.21.5 + Gradle 8.12 는 **Java 21** 이 필요합니다.
**Java 25/26 같은 최신 버전으로는 `Unsupported class file major version` 에러가 납니다.**

- 받기: [Adoptium Temurin 21](https://adoptium.net/temurin/releases/?version=21)
  → Windows 는 x64 `.msi`.
- 설치 경로 예: `C:\Program Files\Eclipse Adoptium\jdk-21.0.x.x-hotspot\`

### Gradle 이 Java 21 을 쓰게 지정

이미 다른 자바(25 등)가 깔려 있어도 **21 을 지우지 말고**, Gradle 만 21 을 보게 하면 됩니다.
아래 셋 중 하나:

**(A) 이번 실행만 (PowerShell, 가장 간단)**
```powershell
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-21.0.5.11-hotspot"
.\gradlew.bat convert -Pdatapack=..\kart-all.zip -Pout=..\generated_src
```

**(B) 영구 고정** — `gradle_project\gradle.properties` 의 마지막 줄 주석을 풀고 경로 수정:
```properties
org.gradle.java.home=C\:\\Program Files\\Eclipse Adoptium\\jdk-21.0.5.11-hotspot
```
(Windows 경로는 `\` → `\\`, `:` → `\:` 로 이스케이프)

> 한 번 21 로 빌드하면 Gradle 데몬이 떠 있을 수 있으니, 자바를 바꾼 뒤엔
> `.\gradlew.bat --stop` 으로 기존 데몬을 끄고 다시 실행하세요.

### Python 3 확인
- `python --version` → 3.10 이상 권장. 이름이 `python3` 면 명령에 `-Ppython=python3` 추가.

> Gradle 자체는 설치 안 해도 됩니다. `gradlew`(래퍼)가 받습니다.

---

## 2. 변환 실행 (핵심)

`gradle_project` 폴더로 이동해서 한 줄 실행:

**macOS / Linux**
```bash
cd gradle_project
./gradlew convert -Pdatapack=../데이터팩.zip -Pout=../generated_src
```

**Windows (PowerShell / CMD)**
```bat
cd gradle_project
gradlew.bat convert -Pdatapack=..\데이터팩.zip -Pout=..\generated_src
```

- `-Pdatapack` : 변환할 데이터팩 (.zip 또는 폴더 경로)
- `-Pout`      : 생성될 자바 소스가 들어갈 폴더
- (선택) `-Ppython=python3` : 파이썬 실행 이름이 `python3` 인 경우

### 무슨 일이 일어나나
1. **(첫 실행만)** Gradle 이 MC 1.21.5 + Fabric jar 를 자동 다운로드 — 몇 분 소요.
2. **extract** — 데이터팩 → `lines.json` (Python, zip 직접 읽기).
3. **parse** — Fabric 서버를 잠깐 띄워(`runServer`) 명령을 정확히 파싱 → `trees.json`.
   서버는 평평한 월드를 부팅하자마자 파싱하고 **자동 종료**합니다(게임 안 함).
4. **generate** — `trees.json` → 자바 소스 + 리포트 (Python).

> parse 단계에서 Fabric 서버를 거치는 이유: 마인크래프트 명령 파서는 Fabric Loader 의
> 클래스 변환을 거친 런타임에서만 정상 동작합니다(직접 실행 시 IllegalAccessError).
> 그래서 서버를 잠깐 띄우지만, 월드 생성·게임 루프 없이 파싱만 하고 즉시 끕니다.

끝나면 `generated_src/CONVERSION_REPORT.md` 에서 변환율을 볼 수 있습니다.

---

## 3. 버전 바꾸기 (확장)

다른 마인크래프트/Fabric 버전으로 돌리려면 `gradle_project/gradle.properties` 의
4줄만 바꾸면 됩니다:

```properties
minecraft_version=1.21.5
yarn_mappings=1.21.5+build.1
loader_version=0.18.4
fabric_version=0.119.5+1.21.5
```

> 큰 버전 점프(예: 1.21 → 1.22)에서 명령 문법이 크게 바뀐 경우에만
> `HeadlessParser.java` 의 `buildDispatcher()` 손봄이 필요할 수 있습니다.
> 마이너 버전은 위 4줄 교체로 충분합니다.

---

## 자주 나는 문제

| 증상 | 해결 |
|---|---|
| `Unsupported class file major version 69`(=Java25)/`68`(=Java24) | **Java 21 로 실행**. 위 1번의 (A) 또는 (B) 적용 후 `.\gradlew.bat --stop` 하고 재실행 |
| `java: command not found` / 버전이 21 아님 | JDK 21 설치 후 `JAVA_HOME` 을 21 로 |
| 첫 실행이 너무 오래/멈춘 듯 | MC jar 다운로드 중입니다. 네트워크 확인하며 대기 |
| `python: command not found` | `-Ppython=python3` 추가 |
| 대용량(100MB+) 데이터팩에서 메모리 부족 | `gradle.properties` 의 `org.gradle.jvmargs=-Xmx6G` 로 상향 |
| 서버는 떴는데 `[ParseDumper]` 로그 없이 멈춤 (모드 목록에 kfc_parser 없음) | `build\resources` 와 `src\main\java\datapackconvert` 삭제 후 `--stop` 하고 재실행. ParseDumper.java/build.gradle/fabric.mod.json 이 최신본인지 확인 |
| Gradle 캐시 꼬임 | `./gradlew convert ... --refresh-dependencies` |

---

## 요약: 딱 이것만

```bash
# 1) JDK 21 설치 (한 번)
# 2) 실행
cd gradle_project
./gradlew convert -Pdatapack=../데이터팩.zip -Pout=../generated_src
# 3) generated_src/ 에 자바 소스 완성
```