package com.prices.inditex.application.service;

import com.prices.inditex.domain.model.PriceList;
import com.prices.inditex.domain.port.out.GetPriceUserCase;

import java.time.LocalDateTime;

public class PriceService implements GetPriceUserCase {

    private GetPriceUserCase getPrice;

    public PriceService(GetPriceUserCase getPrice) {
        this.getPrice = getPrice;
    }

    @Override
    public PriceList get(int brandId, int productId, LocalDateTime dateTime) {
        return getPrice.get(brandId, productId, dateTime);
    }

}