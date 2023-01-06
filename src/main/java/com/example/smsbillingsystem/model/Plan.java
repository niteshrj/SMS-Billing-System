package com.example.smsbillingsystem.model;

import java.math.BigDecimal;

public enum Plan {

    BASIC(0, new BigDecimal("0.001"), "USD"),
    SILVER(100, new BigDecimal("0.002"), "USD"),
    GOLD(1000, new BigDecimal("0.003"), "USD");

    private final int freeMessages;
    private final BigDecimal pricePerMessage;
    private final String currencyUnit;

    Plan(int freeMessages, BigDecimal pricePerMessage, String currencyUnit) {
        this.freeMessages = freeMessages;
        this.pricePerMessage = pricePerMessage;
        this.currencyUnit = currencyUnit;
    }

    public int getFreeMessages() {
        return freeMessages;
    }

    public BigDecimal getPricePerMessage() {
        return pricePerMessage;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }
}