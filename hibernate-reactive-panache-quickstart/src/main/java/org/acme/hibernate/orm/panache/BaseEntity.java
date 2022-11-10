package org.acme.hibernate.orm.panache;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

@MappedSuperclass
public class BaseEntity extends PanacheEntityBase implements Serializable {

    @Id
    // FIX: For some reason, the automatically generated id by hibernate leads to id duplication
    // Using sequence identifier types, such as Long, leads to an incorrect amount of inserted data and duplication too
    // Temporary fixed by uuid and session.setBatchSize(entitiesMap.values().size());
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "org.acme.hibernate.orm.panache.CustomUUIDGenerator"
    )
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
