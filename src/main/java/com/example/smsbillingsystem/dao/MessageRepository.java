package com.example.smsbillingsystem.dao;

import com.example.smsbillingsystem.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MessageRepository extends JpaRepository<MessageDto, Integer> {
    int countByCustomerIdAndCreatedAtBetween(int id, Date startDate, Date endDate);
}
