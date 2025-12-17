package com.lshdainty.porest.common.exception;

/**
 * 외부 서비스 연동 실패 예외 (502 Bad Gateway 또는 503 Service Unavailable)
 * 외부 API, 메시징 서비스 등 연동 실패 시 사용
 *
 * 사용 예시:
 * - throw new ExternalServiceException(ErrorCode.INTERNAL_SERVER_ERROR, "OAuth2 인증 서버 연동 실패");
 * - throw new ExternalServiceException(ErrorCode.INTERNAL_SERVER_ERROR, "메일 발송 서버 응답 없음", cause);
 */
public class ExternalServiceException extends BusinessException {

    public ExternalServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ExternalServiceException(ErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }

    public ExternalServiceException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ExternalServiceException(ErrorCode errorCode, String customMessage, Throwable cause) {
        super(errorCode, customMessage, cause);
    }
}
