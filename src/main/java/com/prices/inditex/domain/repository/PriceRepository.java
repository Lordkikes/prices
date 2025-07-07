package com.prices.inditex.domain.repository;

import com.prices.inditex.domain.model.PriceResponseDTO;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository interface for accessing price data.
 * Provides a method to retrieve the preferred price based on application date, product ID, and brand ID.
 */
public interface PriceRepository {

    /**
     * Retrieves the preferred price for a given application date, product ID, and brand ID.
     *
     * @param applicationDate The date and time for which the price is requested.
     * @param productId The ID of the product.
     * @param brandId The ID of the brand.
     * @return An Optional containing the PriceResponseDTO with the preferred price details,
     *         or an empty Optional if no price is found.
     */
    Optional<PriceResponseDTO> getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}