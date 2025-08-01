package com.prices.inditex.infrastructure.entity;

import com.prices.inditex.domain.model.Product;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="products")
public class ProductEntity {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<PriceListEntity> priceLists;

    public Product toDomain() {
        return new Product(this.id, this.name);
    }

}