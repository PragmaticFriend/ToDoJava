package com.todoNuraliev.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.log4j.Log4j;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Log4j
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"created_at", "updated_at"},
        allowGetters = true
)
public abstract class BaseModel implements Serializable {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updated_at;

    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

}
