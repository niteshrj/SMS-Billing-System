package com.example.smsbillingsystem.dto;

import com.example.smsbillingsystem.model.Plan;

import javax.persistence.*;

@Entity(name = "Customer")
public class CustomerDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    int id;

    @Column
    String name;

    @Enumerated(EnumType.STRING)
    Plan plan;

    public int getId() {
        return id;
    }

    public Plan getPlan() {
        return plan;
    }
}
