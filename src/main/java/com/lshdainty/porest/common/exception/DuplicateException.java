package com.lshdainty.porest.common.exception;

/**
 * 중복 데이터 예외 (409 Conflict)
 * 이미 존재하는 데이터를 다시 생성하려 할 때 사용
 *
 * 사용 예시:
 * - throw new DuplicateException(ErrorCode.USER_ALREADY_EXISTS);
 * - throw new DuplicateException(ErrorCode.USER_DUPLICATE_EMAIL, "이메일: " + email);
 */
public class DuplicateException extends BusinessException {

    public DuplicateException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateException(ErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }

    public DuplicateException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DuplicateException(ErrorCode errorCode, String customMessage, Throwable cause) {
        super(errorCode, customMessage, cause);
    }
}
