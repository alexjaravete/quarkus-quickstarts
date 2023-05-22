package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@IdClass(FruitId.class)
@Table(name = "fruit")
public class Fruit extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public Long id;

    @Id
    @JoinColumn(name = "basket_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "basket_fk"))
    @ManyToOne(fetch = FetchType.LAZY)
    public FruitBasket basket;

    @Column(length = 40, unique = true)
    public String name;


    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }



}
