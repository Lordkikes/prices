package com.prices.inditex.application.port;

import com.prices.inditex.domain.model.PriceResponseDTO;

import java.time.LocalDateTime;

public interface IGetPriceUseCase {
    PriceResponseDTO getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}