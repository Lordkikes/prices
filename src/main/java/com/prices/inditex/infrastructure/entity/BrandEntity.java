package com.prices.inditex.infrastructure.entity;

import com.prices.inditex.domain.model.Brand;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="brands")
public class BrandEntity {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    private List<PriceListEntity> priceLists;

    public Brand toDomain() {
        return new Brand(this.id, this.name);
    }

}