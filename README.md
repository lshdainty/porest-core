# porest-core

POREST 백엔드 공통 라이브러리입니다. 예외 처리, 공통 API 응답, 감사(Auditing) 필드, 유효성 검증, 유틸리티, 로깅 AOP를 제공합니다.

## Tech Stack
- Java 25
- Spring Boot 4.0.x (compileOnly)
- QueryDSL 7.1 (compileOnly)
- springdoc-openapi 3.0.1 (compileOnly)
- Gradle (java-library, maven-publish)

## 현재 제공 모듈
- `config`: Locale, SecurityProperties, PasswordEncoder 설정
- `controller`: `ApiResponse`, `GlobalExceptionHandler`, 페이징/커서 DTO
- `domain`: `AuditingFields`
- `exception`: 공통 비즈니스 예외 계층
- `logging`: 메서드 실행 로깅 AOP
- `message`: `MessageKey`
- `security`: `AuditorPrincipal`
- `type`: `CountryCode`, `DisplayType`, `Environment`, `SortDirection`, `YNType`
- `util`: Crypto/File/Http/Json/Mask/Message/Regex/Time 유틸
- `validation`: `@DateRange`, `@EnumValue`, `@Password`, `@Phone`

## 사용 방법

### 1) GitHub Packages 인증
`~/.gradle/gradle.properties` 또는 환경 변수에 인증 정보를 설정합니다.

```properties
gpr.user=YOUR_GITHUB_ID
gpr.key=YOUR_GITHUB_TOKEN
```

### 2) 의존성 추가

```gradle
repositories {
  maven {
    url = uri("https://maven.pkg.github.com/lshdainty/porest-core")
    credentials {
      username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user")
      password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key")
    }
  }
}

dependencies {
  implementation "com.porest:porest-core:2.0.1"
}
```

## 로컬 빌드

```bash
./gradlew clean build
```

## 배포(Publish)

```bash
./gradlew publish
```
