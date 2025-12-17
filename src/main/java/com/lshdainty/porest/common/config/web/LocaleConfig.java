package com.lshdainty.porest.common.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;
import java.util.Locale;

/**
 * 다국어 지원을 위한 Locale 설정
 * Accept-Language 헤더 기반으로 Locale을 결정하며, lang 파라미터로 변경 가능
 */
@Configuration
public class LocaleConfig {

    /**
     * LocaleResolver 설정
     * Accept-Language 헤더를 기반으로 Locale을 결정
     * 기본 Locale: 한국어 (ko)
     * 지원 Locale: 한국어 (ko), 영어 (en)
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.KOREAN);
        resolver.setSupportedLocales(List.of(Locale.KOREAN, Locale.ENGLISH));
        return resolver;
    }

    /**
     * LocaleChangeInterceptor 설정
     * URL 파라미터 'lang'으로 Locale 변경 가능
     * 예: /api/v1/users?lang=en
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }
}
