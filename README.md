<p align="center">
  <img src="https://img.shields.io/badge/POREST_Common-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="POREST Common" />
</p>

<h1 align="center">POREST Core</h1>

<p align="center">
  <strong>POREST 백엔드 공통 라이브러리</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-007396?logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/GitHub_Packages-181717?logo=github&logoColor=white" alt="GitHub Packages" />
</p>

---

## 소개

**porest-core**는 [POREST](https://github.com/lshdainty/POREST) 백엔드 프로젝트에서 공통으로 사용되는 라이브러리입니다.

예외 처리, 국제화(i18n), API 응답 포맷, 공통 설정 등 프로젝트 전반에서 사용되는 컴포넌트를 제공합니다.

---

## 주요 기능

### Configuration

| 패키지 | 설명 |
|--------|------|
| `config.web` | Locale 설정 |
| `config.openapi` | Swagger/OpenAPI 설정 |
| `config.properties` | Security Properties |

### Controller

| 클래스 | 설명 |
|--------|------|
| `ApiResponse` | 통일된 API 응답 포맷 |
| `GlobalExceptionHandler` | 전역 예외 처리 |
| `TypesApi` | 공통 타입 조회 API |

### Exception

| 클래스 | 설명 |
|--------|------|
| `BusinessException` | 비즈니스 예외 기본 클래스 |
| `EntityNotFoundException` | 엔티티 조회 실패 |
| `InvalidValueException` | 입력값 검증 실패 |
| `DuplicateException` | 중복 데이터 |
| `ForbiddenException` | 권한 없음 |
| `UnauthorizedException` | 인증 실패 |

### Type (Interface & Enum)

| 타입 | 설명 |
|------|------|
| `CompanyType` | 회사 타입 인터페이스 |
| `SystemType` | 시스템 타입 인터페이스 |
| `DisplayType` | 화면 표시 타입 인터페이스 |
| `CountryCode` | 국가 코드 |
| `YNType` | Y/N 타입 |

### Utility

| 클래스 | 설명 |
|--------|------|
| `MessageResolver` | 다국어 메시지 조회 |
| `PorestTime` | 날짜/시간 유틸리티 |
| `PorestFile` | 파일 처리 유틸리티 |

---

## 프로젝트 구조

```
src/main/java/com/porest/core/
├── config/
│   ├── web/             # Locale 설정
│   ├── openapi/         # Swagger 설정
│   └── properties/      # Security Properties
├── constant/            # 상수 정의
├── controller/
│   ├── ApiResponse.java
│   ├── GlobalExceptionHandler.java
│   └── TypesApi.java
├── exception/           # 예외 클래스
│   ├── BusinessException.java
│   ├── EntityNotFoundException.java
│   ├── InvalidValueException.java
│   └── ...
├── logging/             # 로깅 유틸리티
├── message/             # 메시지 리소스
├── security/            # 보안 관련
├── type/
│   ├── CompanyType.java      # Interface
│   ├── SystemType.java       # Interface
│   ├── DisplayType.java      # Interface
│   ├── CountryCode.java
│   └── YNType.java
├── util/                # 유틸리티
│   ├── MessageResolver.java
│   ├── PorestTime.java
│   └── PorestFile.java
└── validation/          # 검증 유틸리티
```

---

## 설치

### Gradle (build.gradle)

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/lshdainty/porest-core")
        credentials {
            username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user")
            password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key")
        }
    }
}

dependencies {
    implementation 'com.porest:porest-core:2.0.0'
}
```

---

## 관련 저장소

| Repository | Description |
|------------|-------------|
| [POREST](https://github.com/lshdainty/POREST) | 통합 레포지토리 (서비스 소개) |
| [porest-hr-back](https://github.com/lshdainty/porest-hr-back) | HR 백엔드 |
| [porest-hr-front](https://github.com/lshdainty/porest-hr-front) | HR 프론트엔드 |
| [porest-sso-back](https://github.com/lshdainty/porest-sso-back) | SSO 백엔드 |
| [porest-sso-front](https://github.com/lshdainty/porest-sso-front) | SSO 프론트엔드 |

---

<p align="center">
  Made with ❤️ by <a href="https://github.com/lshdainty">lshdainty</a>
</p>
