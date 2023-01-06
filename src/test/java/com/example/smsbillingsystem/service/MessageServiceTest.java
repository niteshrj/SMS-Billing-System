package com.example.smsbillingsystem.service;

import com.example.smsbillingsystem.dao.MessageRepository;
import com.example.smsbillingsystem.dto.CustomerDto;
import com.example.smsbillingsystem.dto.MessageDto;
import com.example.smsbillingsystem.model.Plan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    MessageService messageService;

    @Test
    public void testSendSMS() {
        String message = "Sample message text.";
        int customerId = 123;
        when(messageRepository.save(any(MessageDto.class))).thenReturn(null);

        String actualAck = messageService.sendSMS(message, customerId);

        verify(messageRepository, times(1)).save(any(MessageDto.class));
        assertEquals("Message sent successfully!", actualAck);
    }

    @Test
    public void testCalculateBillAmount() {
        int customerId = 123;
        CustomerDto customer = getCustomer(customerId);
        when(messageRepository.countByCustomerIdAndCreatedAtBetween(any(Integer.class), any(Date.class), any(Date.class))).thenReturn(5);

        BigDecimal actualAmount = messageService.calculateBillAmount(customer);

        assertEquals(BigDecimal.valueOf(0.005), actualAmount);
    }

    private CustomerDto getCustomer(int customerId) {
        CustomerDto customer = new CustomerDto();
        customer.setId(customerId);
        customer.setName("Bank");
        customer.setPlan(Plan.BASIC);
        return customer;
    }
}