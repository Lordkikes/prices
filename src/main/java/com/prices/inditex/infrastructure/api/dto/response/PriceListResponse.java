package com.prices.inditex.infrastructure.api.dto.response;

import com.prices.inditex.domain.model.PriceList;

import java.time.LocalDateTime;


public record PriceListResponse(int brandId, int productId, LocalDateTime startDate, LocalDateTime endDate, double price) {

    public static PriceListResponse fromDomain(PriceList priceList) {
        return new PriceListResponse(priceList.brand().id(), priceList.product().id(), priceList.startDate(), priceList.endDate(), priceList.price());
    }

}