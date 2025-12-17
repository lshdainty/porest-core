package com.lshdainty.porest.common.util;

import com.lshdainty.porest.common.exception.ErrorCode;
import com.lshdainty.porest.common.message.MessageKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 다국어 메시지 처리를 위한 통합 유틸리티
 * MessageKey enum 또는 ErrorCode를 사용하여 타입 안전하게 메시지 조회
 * LocaleContextHolder를 통해 현재 요청의 Locale에 맞는 메시지 반환
 */
@Component
@RequiredArgsConstructor
public class MessageResolver {

    private final MessageSource messageSource;

    // ========================================
    // MessageKey 기반 메시지 조회 (권장)
    // ========================================

    /**
     * MessageKey로 메시지 가져오기 (현재 Locale 사용)
     *
     * @param messageKey 메시지 키 enum
     * @return 다국어 메시지
     */
    public String getMessage(MessageKey messageKey) {
        return messageSource.getMessage(
                messageKey.getKey(),
                null,
                messageKey.getKey(),
                LocaleContextHolder.getLocale()
        );
    }

    /**
     * MessageKey로 메시지 가져오기 (파라미터 포함)
     *
     * 예시:
     * messages.properties에 "error.file.notfound=파일을 찾을 수 없습니다: {0}"
     * getMessage(MessageKey.FILE_NOT_FOUND, "test.txt") -> "파일을 찾을 수 없습니다: test.txt"
     *
     * @param messageKey 메시지 키 enum
     * @param args 메시지 파라미터
     * @return 다국어 메시지
     */
    public String getMessage(MessageKey messageKey, Object... args) {
        return messageSource.getMessage(
                messageKey.getKey(),
                args,
                messageKey.getKey(),
                LocaleContextHolder.getLocale()
        );
    }

    // ========================================
    // ErrorCode 기반 메시지 조회 (예외 처리용)
    // ========================================

    /**
     * ErrorCode에서 메시지 가져오기 (현재 Locale 사용)
     *
     * @param errorCode 에러 코드 enum
     * @return 다국어 메시지
     */
    public String getMessage(ErrorCode errorCode) {
        return messageSource.getMessage(
                errorCode.getMessageKey(),
                null,
                errorCode.getCode(),
                LocaleContextHolder.getLocale()
        );
    }

    /**
     * ErrorCode에서 메시지 가져오기 (파라미터 포함)
     *
     * @param errorCode 에러 코드 enum
     * @param args 메시지 파라미터
     * @return 다국어 메시지
     */
    public String getMessage(ErrorCode errorCode, Object... args) {
        return messageSource.getMessage(
                errorCode.getMessageKey(),
                args,
                errorCode.getCode(),
                LocaleContextHolder.getLocale()
        );
    }

    // ========================================
    // 문자열 키 기반 메시지 조회 (하위 호환용)
    // ========================================

    /**
     * 메시지 키 문자열로 직접 메시지 가져오기
     * 가능하면 MessageKey enum 사용을 권장
     *
     * @param messageKey 메시지 키 문자열
     * @return 다국어 메시지
     */
    public String getMessage(String messageKey) {
        return messageSource.getMessage(
                messageKey,
                null,
                messageKey,
                LocaleContextHolder.getLocale()
        );
    }

    /**
     * 메시지 키 문자열로 직접 메시지 가져오기 (파라미터 포함)
     * 가능하면 MessageKey enum 사용을 권장
     *
     * @param messageKey 메시지 키 문자열
     * @param args 메시지 파라미터
     * @return 다국어 메시지
     */
    public String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(
                messageKey,
                args,
                messageKey,
                LocaleContextHolder.getLocale()
        );
    }
}
