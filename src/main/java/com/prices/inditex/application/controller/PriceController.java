package com.prices.inditex.application.controller;


import com.prices.inditex.domain.model.PriceResponseDTO;
import com.prices.inditex.application.port.IGetPriceUseCase;
import com.prices.inditex.infrastructure.controller.PricesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final IGetPriceUseCase getPriceUseCase;

    @Override
    public ResponseEntity<PriceResponseDTO> getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return ResponseEntity.ok(getPriceUseCase.getPreferredPrice(applicationDate, productId, brandId));
    }

}