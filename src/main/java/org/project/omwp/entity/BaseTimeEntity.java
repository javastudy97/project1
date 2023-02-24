package org.project.omwp.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createTime; //생성 시에만

//    @UpdateTimestamp
//    @Column(insertable = false)
//    private LocalDateTime updateTime; //수정 시에만
}
