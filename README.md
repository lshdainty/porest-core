<p align="center">
  <img src="https://img.shields.io/badge/POREST_Core-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="POREST Core" />
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

예외 처리, 국제화(i18n), API 응답 포맷, JPA Auditing, 공통 설정 등 프로젝트 전반에서 사용되는 컴포넌트를 제공합니다.

---

## 주요 기능

### Domain

| 클래스 | 설명 |
|--------|------|
| `AuditingFields` | JPA Auditing 기본 필드 (createAt, createBy, modifyAt, modifyBy) |

### Configuration

| 패키지 | 설명 |
|--------|------|
| `config.web` | Locale 설정 |
| `config.properties` | Security Properties |
| `PasswordEncoderConfig` | BCrypt 비밀번호 인코더 |

### Controller

| 클래스 | 설명 |
|--------|------|
| `ApiResponse` | 통일된 API 응답 포맷 |
| `GlobalExceptionHandler` | 전역 예외 처리 |
| `PageRequest` / `CursorRequest` | 페이지네이션 요청 DTO |

### Exception

| 클래스 | HTTP Status | 설명 |
|--------|-------------|------|
| `BusinessException` | - | 비즈니스 예외 기본 클래스 |
| `EntityNotFoundException` | 404 | 엔티티 조회 실패 |
| `ResourceNotFoundException` | 404 | 리소스 조회 실패 |
| `InvalidValueException` | 400 | 입력값 검증 실패 |
| `BusinessRuleViolationException` | 400 | 비즈니스 규칙 위반 |
| `DuplicateException` | 409 | 중복 데이터 |
| `UnauthorizedException` | 401 | 인증 실패 |
| `ForbiddenException` | 403 | 권한 없음 |
| `ExternalServiceException` | 5xx | 외부 서비스 연동 실패 |

### Type (Interface & Enum)

| 타입 | 설명 |
|------|------|
| `DisplayType` | 화면 표시 타입 인터페이스 |
| `CountryCode` | 국가 코드 |
| `Environment` | 실행 환경 타입 |
| `SortDirection` | 정렬 방향 타입 |
| `YNType` | Y/N 타입 |

### Utility

| 클래스 | 설명 |
|--------|------|
| `MessageResolver` | 다국어 메시지 조회 |
| `TimeUtils` | 날짜/시간 유틸리티 |
| `FileUtils` | 파일 처리 (저장, 읽기, 해시 계산 등) |
| `HttpUtils` | HTTP 요청 유틸리티 (IP, 헤더, 파라미터 등) |
| `CryptoUtils` | 암호화 유틸리티 |
| `JsonUtils` | JSON 변환 유틸리티 |
| `MaskUtils` | 마스킹 유틸리티 |
| `RegexPatterns` | 정규식 패턴 |

### Security

| 클래스 | 설명 |
|--------|------|
| `AuditorPrincipal` | JPA Auditing용 Principal 인터페이스 |

### Message

| 클래스 | 설명 |
|--------|------|
| `MessageKey` | 다국어 메시지 키 enum |

---

## 프로젝트 구조

```
src/main/java/com/porest/core/
├── config/
│   ├── web/                 # Locale 설정
│   ├── properties/          # Security Properties
│   └── PasswordEncoderConfig.java
├── constant/                # 상수 정의
├── controller/
│   ├── dto/                 # PageRequest, CursorRequest
│   ├── ApiResponse.java
│   └── GlobalExceptionHandler.java
├── domain/
│   └── AuditingFields.java  # JPA Auditing 기본 필드
├── exception/
│   ├── ErrorCode.java
│   ├── ErrorCodeProvider.java
│   ├── BusinessException.java
│   └── ...
├── logging/                 # 로깅 AOP
├── message/
│   └── MessageKey.java
├── security/
│   └── AuditorPrincipal.java
├── type/
│   ├── DisplayType.java
│   ├── CountryCode.java
│   ├── Environment.java
│   ├── SortDirection.java
│   └── YNType.java
├── util/
│   ├── MessageResolver.java
│   ├── TimeUtils.java
│   ├── FileUtils.java
│   ├── HttpUtils.java
│   ├── CryptoUtils.java
│   ├── JsonUtils.java
│   ├── MaskUtils.java
│   └── RegexPatterns.java
└── validation/              # 검증 유틸리티
```

---

## 사용 예시

### AuditingFields

```java
// 기본 사용
@Entity
@Table(name = "users")
public class User extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}

// IP 필드 확장 (각 서비스에서)
@MappedSuperclass
public abstract class AuditingFieldsWithIp extends AuditingFields {
    @Column(name = "create_ip", length = 45)
    private String createIp;

    @Column(name = "modify_ip", length = 45)
    private String modifyIp;

    @PrePersist
    public void prePersist() {
        this.createIp = HttpUtils.getClientIp();
        this.modifyIp = this.createIp;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifyIp = HttpUtils.getClientIp();
    }
}
```

### Exception

```java
// 엔티티 조회 실패
User user = userRepository.findById(id)
    .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

// 비즈니스 규칙 위반
if (vacation.getBalance() < requestedDays) {
    throw new BusinessRuleViolationException(HrErrorCode.VACATION_INSUFFICIENT);
}
```

### Utility

```java
// 파일 해시 계산
String hash = FileUtils.calculateSha256("/files/document.pdf");

// HTTP 요청 정보
String clientIp = HttpUtils.getClientIp();
Map<String, String> params = HttpUtils.getParameterMapSingleValue();

// 다국어 메시지
String message = messageResolver.getMessage(MessageKey.COMMON_SUCCESS);
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
    implementation 'com.porest:porest-core:2.0.1'
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
  Made with by <a href="https://github.com/lshdainty">lshdainty</a>
</p>
