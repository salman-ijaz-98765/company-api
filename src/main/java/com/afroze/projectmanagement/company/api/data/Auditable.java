package com.afroze.projectmanagement.company.api.data;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @param <U> The type for auditors (users)
 * @param <K> The type for the entity id
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U, K extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private K id;

    @Column(name = "created_on", nullable = false)
    @CreatedDate
    private Date createdOn;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private U createdBy;

    @Column(name = "modified_on", nullable = false)
    @LastModifiedDate
    private Date modifiedOn;

    @Column(name = "modified_by", nullable = false)
    @LastModifiedBy
    private U modifiedBy;
}