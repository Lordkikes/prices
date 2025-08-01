package com.prices.inditex.domain.port.out;

import com.prices.inditex.domain.model.PriceList;

import java.time.LocalDateTime;

public interface GetPriceUserCase {

    public PriceList get(int brandId, int productId, LocalDateTime dateTime);

}
