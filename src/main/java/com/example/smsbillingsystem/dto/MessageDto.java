package com.example.smsbillingsystem.dto;

import javax.persistence.*;

@Entity(name = "Message")
public class MessageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    int id;

    @Column
    int customerId;

    @Column
    String text;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setText(String text) {
        this.text = text;
    }
}