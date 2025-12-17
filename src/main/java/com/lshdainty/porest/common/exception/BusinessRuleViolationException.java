package com.lshdainty.porest.common.exception;

/**
 * 비즈니스 규칙 위반 예외 (400 Bad Request)
 * 비즈니스 로직 상 허용되지 않는 동작일 때 사용
 *
 * 사용 예시:
 * - throw new BusinessRuleViolationException(ErrorCode.VACATION_INSUFFICIENT_BALANCE);
 * - throw new BusinessRuleViolationException(ErrorCode.VACATION_CANNOT_CANCEL, "이미 시작된 휴가는 취소할 수 없습니다.");
 * - throw new BusinessRuleViolationException(ErrorCode.DEPARTMENT_HAS_MEMBERS);
 */
public class BusinessRuleViolationException extends BusinessException {

    public BusinessRuleViolationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessRuleViolationException(ErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }

    public BusinessRuleViolationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BusinessRuleViolationException(ErrorCode errorCode, String customMessage, Throwable cause) {
        super(errorCode, customMessage, cause);
    }
}
