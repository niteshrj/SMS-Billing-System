package com.example.smsbillingsystem.model;

import java.math.BigDecimal;

public enum Plan {

    BASIC(0, new BigDecimal("0.001")),
    SILVER(100, new BigDecimal("0.002")),
    GOLD(1000, new BigDecimal("0.003"));

    private final int freeMessages;

    private final BigDecimal pricePerMessage;

    Plan(int freeMessages, BigDecimal pricePerMessage) {
        this.freeMessages = freeMessages;
        this.pricePerMessage = pricePerMessage;
    }

    public int getFreeMessages() {
        return freeMessages;
    }

    public BigDecimal getPricePerMessage() {
        return pricePerMessage;
    }
}