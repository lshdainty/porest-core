package com.lshdainty.porest.common.config.database;

import com.lshdainty.porest.common.domain.AuditingFields;
import com.lshdainty.porest.common.util.PorestIP;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * IP 주소 자동 설정을 위한 JPA EntityListener
 * AuditingFields를 상속받은 엔티티의 생성/수정 시 자동으로 IP 주소를 설정합니다.
 */
public class IpAuditingEntityListener {

    /**
     * 엔티티 생성 전 IP 주소 설정
     */
    @PrePersist
    public void setCreatedIp(Object entity) {
        if (entity instanceof AuditingFields) {
            AuditingFields auditingFields = (AuditingFields) entity;
            String currentIp = PorestIP.getClientIp();
            if (currentIp != null) {
                auditingFields.setCreateIP(currentIp);
            }
        }
    }

    /**
     * 엔티티 수정 전 IP 주소 설정
     */
    @PreUpdate
    public void setModifiedIp(Object entity) {
        if (entity instanceof AuditingFields) {
            AuditingFields auditingFields = (AuditingFields) entity;
            String currentIp = PorestIP.getClientIp();
            if (currentIp != null) {
                auditingFields.setModifyIP(currentIp);
            }
        }
    }
}