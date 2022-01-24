package com.bluebank.api.entity;

import com.bluebank.api.customer.Customer;
import com.bluebank.api.listener.EntityModifierUpdatingListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(EntityModifierUpdatingListener.class)
public abstract class AbstractAuditEntity<I> {

    @Transient
    private String randomId;

    @Column
    private Long creatingUserId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
