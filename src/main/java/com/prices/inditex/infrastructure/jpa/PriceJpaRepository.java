package com.prices.inditex.infrastructure.jpa;

import com.prices.inditex.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository interface for accessing price data from the database.
 * Extends JpaRepository to provide CRUD operations for PriceEntity.
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    /**
     * Retrieves the top price entity based on product ID, brand ID, and application date range,
     * ordered by priority in descending order.
     *
     * @param productId The ID of the product.
     * @param brandId The ID of the brand.
     * @param applicationDate The application date to check if it falls within the start and end date range.
     * @param applicationDate2 The same application date to check if it falls within the start and end date range.
     * @return An Optional containing the top PriceEntity with the highest priority, or an empty Optional if no match is found.
     */
    Optional<PriceEntity> findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Integer productId, Integer brandId, LocalDateTime applicationDate, LocalDateTime applicationDate2);

}