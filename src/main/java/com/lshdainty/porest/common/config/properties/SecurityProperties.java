package com.lshdainty.porest.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 보안 관련 설정
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * IP 블랙리스트 설정
     */
    private IpBlacklist ipBlacklist = new IpBlacklist();

    @Getter
    @Setter
    public static class IpBlacklist {
        /**
         * IP 블랙리스트 활성화 여부
         */
        private boolean enabled = true;

        /**
         * IP 블랙리스트 파일 경로
         * 파일이 존재하지 않으면 경고만 로그에 남기고 계속 진행
         * 예: "config/ip-blacklist.txt", "/etc/porest/ip-blacklist.txt"
         */
        private String filePath;

        /**
         * 차단 시 로그 레벨 (WARN 또는 ERROR)
         */
        private String logLevel = "WARN";
    }
}
