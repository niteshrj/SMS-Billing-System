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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Plan getPlan() {
        return plan;
    }
}
