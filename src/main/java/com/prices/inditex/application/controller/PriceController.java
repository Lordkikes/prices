package com.prices.inditex.application.controller;

import com.prices.inditex.domain.model.PriceResponseDTO;
import com.prices.inditex.application.port.IGetPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Controller class for handling price-related requests.
 * Implements the PricesApi interface to provide REST endpoints.
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    // Use case for retrieving the preferred price.
    private final IGetPriceUseCase getPriceUseCase;

    /**
     * Retrieves the preferred price based on the application date, product ID, and brand ID.
     *
     * @param applicationDate The date and time for which the price is requested.
     * @param productId The ID of the product.
     * @param brandId The ID of the brand.
     * @return A ResponseEntity containing the PriceResponseDTO with the preferred price details.
     */
    @Override
    public ResponseEntity<PriceResponseDTO> getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return ResponseEntity.ok(getPriceUseCase.getPreferredPrice(applicationDate, productId, brandId));
    }

}