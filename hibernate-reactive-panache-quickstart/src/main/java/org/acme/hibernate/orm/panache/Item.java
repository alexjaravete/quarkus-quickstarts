package org.acme.hibernate.orm.panache;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Cacheable
public class Item extends BaseEntity {

    @Column(updatable = false, unique = true)
    private Long productId;

    @Column(length = 4000)
    private String name;

    public Item() {
    }

    public Item(Long productId) {
        this.productId = productId;
        this.name = "item " + productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getId() + ":" + name + ":" + productId;
    }
}
