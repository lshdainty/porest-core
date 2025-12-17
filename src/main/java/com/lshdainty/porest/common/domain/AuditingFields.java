package com.lshdainty.porest.common.domain;

import com.lshdainty.porest.common.config.database.IpAuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners({AuditingEntityListener.class, IpAuditingEntityListener.class})
public class AuditingFields {
    /**
     * 생성 일자
     */
    @CreatedDate
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    /**
     * 생성자
     */
    @CreatedBy
    @Column(name = "create_by", length = 20, updatable = false)
    private String createBy;

    /**
     * 생성 IP
     */
    @Column(name = "create_ip", length = 49, updatable = false)
    @Setter // IpAuditingEntityListener에서 사용
    private String createIP;

    /**
     * 최종 수정 일자
     */
    @LastModifiedDate
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    /**
     * 최종 수정자
     */
    @LastModifiedBy
    @Column(name = "modify_by", length = 20)
    private String modifyBy;

    /**
     * 최종 수정 IP
     */
    @Column(name = "modify_ip", length = 49)
    @Setter // IpAuditingEntityListener에서 사용
    private String modifyIP;
}
