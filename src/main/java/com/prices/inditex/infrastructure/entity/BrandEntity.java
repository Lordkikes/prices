package com.prices.inditex.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a Brand in the database.
 * Maps to the "BRAND" table in the database.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BRAND")
public class BrandEntity {

    /**
     * The unique identifier of the brand.
     * Maps to the primary key column in the "BRAND" table.
     */
    @Id
    private Integer id;

    /**
     * The name of the brand.
     */
    private String name;
}