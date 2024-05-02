package org.acme.hibernate.orm.panache;

import jakarta.persistence.*;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Cacheable
public class Fruit extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @ManyToOne(fetch = FetchType.EAGER)
    public Owner owner;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

}
