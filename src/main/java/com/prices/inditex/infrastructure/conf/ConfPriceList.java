package com.prices.inditex.infrastructure.conf;

import com.prices.inditex.application.service.PriceService;
import com.prices.inditex.application.usecase.GetPrice;
import com.prices.inditex.domain.port.out.GetPriceUserCase;
import com.prices.inditex.domain.port.out.PriceListRepositoryPort;
import com.prices.inditex.infrastructure.jpa.PriceListRespositoryAdapter;
import com.prices.inditex.infrastructure.jpa.repository.PriceListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfPriceList {

    @Bean
    public PriceService priceService(GetPriceUserCase getPrice) {
        return new PriceService(getPrice);
    }

    @Bean
    public GetPriceUserCase getPriceUserCase(PriceListRepositoryPort priceListRepositoryPort) {
        return new GetPrice(priceListRepositoryPort);
    }

    @Bean
    public PriceListRepositoryPort priceListRepositoryPort(PriceListRepository priceListRepository) {
        return new PriceListRespositoryAdapter(priceListRepository);
    }

}