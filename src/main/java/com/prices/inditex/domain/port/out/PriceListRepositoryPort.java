package com.prices.inditex.domain.port.out;


import com.prices.inditex.domain.model.PriceList;

import java.time.LocalDateTime;

public interface PriceListRepositoryPort {

    public PriceList get(int productId, int brandId, LocalDateTime dateTime);

}
