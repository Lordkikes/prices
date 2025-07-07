package com.prices.inditex.application.service;

import com.prices.inditex.domain.model.PriceResponseDTO;
import com.prices.inditex.application.port.IGetPriceUseCase;
import com.prices.inditex.domain.exception.NotFoundException;
import com.prices.inditex.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service implementation of the IGetPriceUseCase interface.
 * Provides the logic to retrieve the preferred price based on the application date, product ID, and brand ID.
 */
@Service
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements IGetPriceUseCase {

    // Repository for accessing price data.
    private final PriceRepository priceRepository;

    /**
     * Retrieves the preferred price for a given application date, product ID, and brand ID.
     * If no price is found, a NotFoundException is thrown.
     *
     * @param applicationDate The date and time for which the price is requested.
     * @param productId The ID of the product.
     * @param brandId The ID of the brand.
     * @return A PriceResponseDTO containing the details of the preferred price.
     * @throws NotFoundException If no price is available for the given parameters.
     */
    @Override
    public PriceResponseDTO getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceRepository.getPreferredPrice(applicationDate, productId, brandId).
                orElseThrow(() -> new NotFoundException("No price available."));
    }
}