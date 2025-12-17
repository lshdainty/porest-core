<p align="center">
  <img src="https://img.shields.io/badge/POREST_Common-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="POREST Common" />
</p>

<h1 align="center">POREST Backend Common</h1>

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

**porest-back-common**은 [POREST](https://github.com/lshdainty/POREST) 백엔드 프로젝트에서 공통으로 사용되는 라이브러리입니다.

예외 처리, 국제화(i18n), API 응답 포맷, 공통 설정 등 프로젝트 전반에서 사용되는 컴포넌트를 제공합니다.

---

## 주요 기능

### Configuration

| 패키지 | 설명 |
|--------|------|
| `config.database` | JPA 설정, Auditing, P6Spy SQL 로깅 |
| `config.web` | Locale 설정 |
| `config.openapi` | Swagger/OpenAPI 설정 |
| `config.properties` | Security Properties |

### Domain

| 클래스 | 설명 |
|--------|------|
| `AuditingFields` | 생성일시, 수정일시, 생성자, 수정자 자동 관리 |

### Controller

| 클래스 | 설명 |
|--------|------|
| `ApiResponse` | 통일된 API 응답 포맷 |
| `GlobalExceptionHandler` | 전역 예외 처리 |
| `TypesApi` | 공통 타입 조회 API |

### Type (Interface & Enum)

| 타입 | 설명 |
|------|------|
| `CompanyType` | 회사 타입 인터페이스 |
| `SystemType` | 시스템 타입 인터페이스 |
| `DisplayType` | 화면 표시 타입 인터페이스 |
| `CountryCode` | 국가 코드 |
| `YNType` | Y/N 타입 |

---

## 프로젝트 구조

```
src/main/java/com/lshdainty/porest/common/
├── config/
│   ├── database/        # JPA, Auditing, P6Spy 설정
│   ├── web/             # Locale 설정
│   ├── openapi/         # Swagger 설정
│   └── properties/      # Security Properties
├── controller/
│   ├── ApiResponse.java
│   ├── GlobalExceptionHandler.java
│   └── TypesApi.java
├── domain/
│   └── AuditingFields.java
└── type/
    ├── CompanyType.java      # Interface
    ├── SystemType.java       # Interface
    ├── DisplayType.java      # Interface
    ├── DefaultCompanyType.java
    ├── DefaultSystemType.java
    ├── CountryCode.java
    └── YNType.java
```

---

## 설치

### Gradle (build.gradle)

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/lshdainty/porest-back-common")
        credentials {
            username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user")
            password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key")
        }
    }
}

dependencies {
    implementation 'com.lshdainty:porest-back-common:1.0.0'
}
```

---

## 관련 저장소

| Repository | Description |
|------------|-------------|
| [POREST](https://github.com/lshdainty/POREST) | 통합 레포지토리 (서비스 소개) |
| [porest-back](https://github.com/lshdainty/porest-back) | Spring Boot 기반 백엔드 |
| [porest-front](https://github.com/lshdainty/porest-front) | React 기반 프론트엔드 |

---

<p align="center">
  Made with ❤️ by <a href="https://github.com/lshdainty">lshdainty</a>
</p>
