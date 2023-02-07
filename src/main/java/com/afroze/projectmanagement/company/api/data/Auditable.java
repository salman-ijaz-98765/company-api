package com.afroze.projectmanagement.company.api.data;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public Date getCreatedOn() {
        return createdOn;
    }

    @SuppressWarnings("unused")
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @SuppressWarnings("unused")
    public U getCreatedBy() {
        return createdBy;
    }

    @SuppressWarnings("unused")
    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    @SuppressWarnings("unused")
    public Date getModifiedOn() {
        return modifiedOn;
    }

    @SuppressWarnings("unused")
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @SuppressWarnings("unused")
    public U getModifiedBy() {
        return modifiedBy;
    }

    @SuppressWarnings("unused")
    public void setModifiedBy(U modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}