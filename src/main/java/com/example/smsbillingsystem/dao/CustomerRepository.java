package com.example.smsbillingsystem.dao;

import com.example.smsbillingsystem.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDto, Integer> {
    CustomerDto findById(int id);
}
