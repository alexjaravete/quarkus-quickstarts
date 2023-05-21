package org.acme.hibernate.orm.panache;

import java.io.Serializable;
import java.util.Objects;

public class FruitId implements Serializable {

    private Long id;
    private FruitBasket basket;

    public Long getId() {
        return id;
    }

    public FruitId setId(Long id) {
        this.id = id;
        return this;
    }

    public FruitBasket getBasket() {
        return basket;
    }

    public FruitId setBasket(FruitBasket basket) {
        this.basket = basket;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FruitId fruitId = (FruitId) o;
        return Objects.equals(id, fruitId.id) && Objects.equals(basket, fruitId.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basket);
    }
}
