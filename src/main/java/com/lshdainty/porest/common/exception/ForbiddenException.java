package com.lshdainty.porest.common.exception;

/**
 * 접근 권한 없음 예외 (403 Forbidden)
 * 인증은 되었으나 해당 리소스에 대한 권한이 없을 때 사용
 *
 * 사용 예시:
 * - throw new ForbiddenException(ErrorCode.PERMISSION_DENIED);
 * - throw new ForbiddenException(ErrorCode.USER_INACTIVE, "비활성화된 사용자입니다.");
 */
public class ForbiddenException extends BusinessException {

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ForbiddenException(ErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }

    public ForbiddenException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ForbiddenException(ErrorCode errorCode, String customMessage, Throwable cause) {
        super(errorCode, customMessage, cause);
    }
}
