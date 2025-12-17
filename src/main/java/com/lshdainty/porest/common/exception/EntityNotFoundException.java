package com.lshdainty.porest.common.exception;

/**
 * 엔티티 조회 실패 예외 (404 Not Found)
 * 데이터베이스에서 엔티티를 찾을 수 없을 때 사용
 *
 * 사용 예시:
 * - throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND);
 * - throw new EntityNotFoundException(ErrorCode.VACATION_NOT_FOUND, "휴가 ID: " + vacationId);
 */
public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EntityNotFoundException(ErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }

    public EntityNotFoundException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public EntityNotFoundException(ErrorCode errorCode, String customMessage, Throwable cause) {
        super(errorCode, customMessage, cause);
    }
}
