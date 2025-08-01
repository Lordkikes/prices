package com.prices.inditex.domain.model;

import java.time.LocalDateTime;

public record PriceList(int id, Brand brand, LocalDateTime startDate, LocalDateTime endDate,
                        Product product, int priority, double price, Currency currency) {

}