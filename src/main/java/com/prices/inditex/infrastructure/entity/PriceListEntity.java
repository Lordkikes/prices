package com.prices.inditex.infrastructure.entity;

import com.prices.inditex.domain.model.Currency;
import com.prices.inditex.domain.model.PriceList;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="prices")
public class PriceListEntity {

    @Id
    @Column(name="id")
    private int id;

    @JoinColumn(name = "brand_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private BrandEntity brand;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @JoinColumn(name = "product_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductEntity product;

    @Column(name="priority")
    private int priority;

    @Column(name="price")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name="currency")
    private Currency currency;

    public PriceList toDomain() {
        return new PriceList(this.id, this.brand.toDomain(), this.startDate, this.endDate, this.product.toDomain(), this.priority, this.price, this.currency);
    }

}