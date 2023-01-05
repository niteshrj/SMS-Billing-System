package com.example.smsbillingsystem.model;

public class Message {
    private final int customerId;
    private final String text;

    Message(int customerId, String text) {
        this.customerId = customerId;
        this.text = text;
    }
    public int getCustomerId() {
        return customerId;
    }

    public String getText() {
        return text;
    }
}
