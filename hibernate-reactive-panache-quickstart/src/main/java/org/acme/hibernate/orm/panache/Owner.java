package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Cacheable
public class Owner extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @OneToMany(fetch = FetchType.EAGER,targetEntity = Fruit.class)
    public List<Fruit> fruits;

    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

}
