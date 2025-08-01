package com.prices.inditex.application.usecase;


import com.prices.inditex.domain.model.PriceList;
import com.prices.inditex.domain.port.out.GetPriceUserCase;
import com.prices.inditex.domain.port.out.PriceListRepositoryPort;

import java.time.LocalDateTime;

public class GetPrice implements GetPriceUserCase {

    private PriceListRepositoryPort priceListRepositoryPort;

    public GetPrice(PriceListRepositoryPort priceListRepositoryPort) {
        this.priceListRepositoryPort = priceListRepositoryPort;
    }

    @Override
    public PriceList get(int brandId, int productId, LocalDateTime dateTime) {
        return this.priceListRepositoryPort.get(productId, brandId, dateTime);
    }



}
