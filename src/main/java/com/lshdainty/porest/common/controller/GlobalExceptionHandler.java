package com.lshdainty.porest.common.controller;

import com.lshdainty.porest.common.exception.BusinessException;
import com.lshdainty.porest.common.exception.EntityNotFoundException;
import com.lshdainty.porest.common.exception.ErrorCode;
import com.lshdainty.porest.common.exception.ExternalServiceException;
import com.lshdainty.porest.common.exception.UnauthorizedException;
import com.lshdainty.porest.common.message.MessageKey;
import com.lshdainty.porest.common.util.MessageResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

/**
 * 전역 예외 처리 핸들러
 * 모든 예외를 ApiResponse 포맷으로 통일하여 반환
 * MessageSource를 통해 다국어 지원
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageResolver messageResolver;

    /**
     * EntityNotFoundException 처리 (엔티티 조회 실패)
     * BusinessException보다 먼저 처리되어야 함
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(EntityNotFoundException e) {
        log.warn("EntityNotFoundException: code={}, message={}", e.getErrorCode().getCode(), e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        String message = resolveMessage(e, errorCode);
        ApiResponse<Void> response = ApiResponse.error(errorCode.getCode(), message);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    /**
     * ExternalServiceException 처리 (외부 서비스 연동 실패)
     * 외부 서비스 연동 실패는 에러 레벨로 로깅
     */
    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ApiResponse<Void>> handleExternalServiceException(ExternalServiceException e) {
        log.error("ExternalServiceException: code={}, message={}", e.getErrorCode().getCode(), e.getMessage(), e);

        ErrorCode errorCode = e.getErrorCode();
        String message = resolveMessage(e, errorCode);
        ApiResponse<Void> response = ApiResponse.error(errorCode.getCode(), message);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    /**
     * UnauthorizedException 처리 (인증 실패)
     * BusinessException을 상속받지만 별도 핸들러로 명시적 처리
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnauthorizedException(UnauthorizedException e) {
        log.warn("UnauthorizedException: code={}, message={}", e.getErrorCode().getCode(), e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        String message = resolveMessage(e, errorCode);
        ApiResponse<Void> response = ApiResponse.error(errorCode.getCode(), message);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    /**
     * BusinessException 처리
     * 비즈니스 로직 상의 예외 (가장 많이 사용됨)
     * 하위 예외들(EntityNotFoundException, InvalidValueException 등)이 먼저 처리됨
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        log.warn("BusinessException: code={}, message={}", e.getErrorCode().getCode(), e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        String message = resolveMessage(e, errorCode);
        ApiResponse<Void> response = ApiResponse.error(errorCode.getCode(), message);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }

    /**
     * AccessDeniedException 처리 (권한 없음)
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("AccessDeniedException: {}", e.getMessage());

        String message = messageResolver.getMessage(ErrorCode.FORBIDDEN);
        ApiResponse<Void> response = ApiResponse.error(ErrorCode.FORBIDDEN.getCode(), message);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    /**
     * NoResourceFoundException 처리 (존재하지 않는 리소스)
     * 정적 리소스 요청 시 파일이 없을 때 발생 (Spring 6.0+)
     * - 500 에러 대신 404로 응답하여 해커에게 서버 에러 정보 노출 방지
     * - 스택 트레이스 로그 제거로 디스크 용량 절약 및 로그 가독성 향상
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResourceFoundException(NoResourceFoundException e) {
        // 스택 트레이스 없이 간단한 warn 로그만 남김 (보안 + 디스크 절약)
        log.warn("Invalid resource access: {}", e.getResourcePath());

        String message = messageResolver.getMessage(MessageKey.COMMON_404);
        ApiResponse<Void> response = ApiResponse.error("COMMON_404", message);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    /**
     * IllegalArgumentException 처리 (잘못된 인자)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException: {}", e.getMessage());

        String message = e.getMessage() != null && !e.getMessage().isEmpty()
                ? e.getMessage()
                : messageResolver.getMessage(ErrorCode.INVALID_INPUT);

        ApiResponse<Void> response = ApiResponse.error(ErrorCode.INVALID_INPUT.getCode(), message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * MethodArgumentNotValidException 처리 (@Valid 검증 실패)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        log.warn("MethodArgumentNotValidException: {}", errorMessage);

        ApiResponse<Void> response = ApiResponse.error(
                ErrorCode.INVALID_INPUT.getCode(),
                errorMessage
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * BindException 처리 (바인딩 실패)
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<Void>> handleBindException(BindException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.warn("BindException: {}", errorMessage);

        ApiResponse<Void> response = ApiResponse.error(
                ErrorCode.INVALID_INPUT.getCode(),
                errorMessage
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * MethodArgumentTypeMismatchException 처리 (타입 불일치)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String errorMessage = String.format("'%s' 파라미터의 값이 유효하지 않습니다.", e.getName());
        log.warn("MethodArgumentTypeMismatchException: {}", errorMessage);

        ApiResponse<Void> response = ApiResponse.error(
                ErrorCode.INVALID_INPUT.getCode(),
                errorMessage
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * 그 외 모든 예외 처리 (최종 fallback)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception e) {
        log.error("Unexpected Exception", e);

        String message = messageResolver.getMessage(ErrorCode.INTERNAL_SERVER_ERROR);
        ApiResponse<Void> response = ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), message);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * BusinessException 메시지 처리 공통 메서드
     * 커스텀 메시지가 있으면 사용, 없으면 MessageSource에서 가져오기
     */
    private String resolveMessage(BusinessException e, ErrorCode errorCode) {
        return e.getMessage() != null && !e.getMessage().equals(errorCode.getMessageKey())
                ? e.getMessage()
                : messageResolver.getMessage(errorCode);
    }
}
