package com.lshdainty.porest.common.controller;

import com.lshdainty.porest.common.exception.ErrorCode;
import lombok.Getter;

/**
 * API 공통 응답 포맷
 * 성공/실패 응답을 표준화하여 일관성 있는 API 응답 제공
 *
 * 성공 응답 예시:
 * {
 *   "success": true,
 *   "code": "COMMON_200",
 *   "message": "요청이 성공적으로 처리되었습니다.",
 *   "data": { ... }
 * }
 *
 * 실패 응답 예시:
 * {
 *   "success": false,
 *   "code": "USER_NOT_FOUND",
 *   "message": "존재하지 않는 사용자입니다.",
 *   "data": null
 * }
 */
@Getter
public class ApiResponse<T> {

    private final boolean success;
    private final String code;
    private final String message;
    private final T data;

    // 생성자는 private으로 막고, static 팩토리 메서드만 사용
    private ApiResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // ========================================
    // 성공 응답
    // ========================================

    /**
     * 성공 응답 (데이터 포함)
     * 메시지는 "OK" 고정
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                ErrorCode.SUCCESS.getCode(),
                "OK",
                data
        );
    }

    /**
     * 성공 응답 (데이터 없음)
     * 메시지는 "OK" 고정
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(
                true,
                ErrorCode.SUCCESS.getCode(),
                "OK",
                null
        );
    }

    /**
     * 성공 응답 (커스텀 메시지 + 데이터)
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                true,
                ErrorCode.SUCCESS.getCode(),
                message,
                data
        );
    }

    // ========================================
    // 실패 응답 (error 메서드)
    // ========================================

    /**
     * 실패 응답 (커스텀 코드 + 메시지)
     * GlobalExceptionHandler에서 ErrorMessageResolver를 통해 메시지를 가져온 후 사용
     */
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(
                false,
                code,
                message,
                null
        );
    }
}
