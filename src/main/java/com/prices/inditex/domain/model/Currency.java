package com.prices.inditex.domain.model;

public enum Currency {
    EUR("EUR");

    private final String value;

    private Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
