package com.prices.inditex.application.port;

import com.prices.inditex.domain.model.PriceResponseDTO;

import java.time.LocalDateTime;

/**
 * Interface for the use case to retrieve the preferred price.
 * Provides a method to fetch price details based on application date, product ID, and brand ID.
 */
public interface IGetPriceUseCase {

    /**
     * Retrieves the preferred price for a given application date, product ID, and brand ID.
     *
     * @param applicationDate The date and time for which the price is requested.
     * @param productId The ID of the product.
     * @param brandId The ID of the brand.
     * @return A PriceResponseDTO containing the details of the preferred price.
     */
    PriceResponseDTO getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}