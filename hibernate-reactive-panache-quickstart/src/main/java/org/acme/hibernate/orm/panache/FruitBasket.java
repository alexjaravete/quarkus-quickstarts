package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "fruit_basket")
public class FruitBasket extends PanacheEntity {

    @Column
    public String name;

    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY)
    public Collection<Fruit> fruits;
}
