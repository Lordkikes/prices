package com.prices.inditex.infrastructure.entity;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    /**
     * Entity class representing a Price in the database.
     * Maps to the "PRICES" table in the database.
     */
    @Getter
    @Setter
    @Entity
    @Table(name = "PRICES", indexes = @Index(columnList = "productId, brandId, startDate, endDate, priority DESC"))
    public class PriceEntity {

        /**
         * The unique identifier of the price list.
         * Maps to the primary key column in the "PRICES" table.
         */
        @Id
        private Integer priceList;

        /**
         * The ID of the product associated with the price.
         */
        private Integer productId;

        /**
         * The priority of the price. Higher values indicate higher priority.
         */
        private Integer priority;

        /**
         * The currency of the price.
         * Stored as a string in the "CURR" column of the "PRICES" table.
         */
        @Enumerated(EnumType.STRING)
        @Column(name = "CURR")
        private Currency currency;

        /**
         * The price value.
         */
        private BigDecimal price;

        /**
         * The start date and time when the price becomes effective.
         */
        private LocalDateTime startDate;

        /**
         * The end date and time when the price is no longer effective.
         */
        private LocalDateTime endDate;

        /**
         * The brand associated with the price.
         * Represents a many-to-one relationship with the BrandEntity.
         */
        @ManyToOne
        private BrandEntity brand;
    }