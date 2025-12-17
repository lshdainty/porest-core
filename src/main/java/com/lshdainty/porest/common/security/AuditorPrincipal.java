package com.lshdainty.porest.common.security;

/**
 * JPA Auditing을 위한 Principal 인터페이스
 * Spring Security의 Principal 구현체가 이 인터페이스를 구현하면
 * LoginUserAuditorAware에서 사용자 ID를 가져올 수 있습니다.
 */
public interface AuditorPrincipal {
    /**
     * 사용자 ID를 반환합니다.
     * @return 사용자 ID (문자열)
     */
    String getUserId();
}
