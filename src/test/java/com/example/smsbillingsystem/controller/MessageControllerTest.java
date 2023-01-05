package com.example.smsbillingsystem.controller;

import com.example.smsbillingsystem.dao.CustomerRepository;
import com.example.smsbillingsystem.dto.CustomerDto;
import com.example.smsbillingsystem.model.Message;
import com.example.smsbillingsystem.model.Plan;
import com.example.smsbillingsystem.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @Test
    void testSendMessage_customerNotFound() {
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(null);
        Message message = new Message(customerId, "sample message text");

        ResponseEntity<String> response = messageController.sendMessage(message);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer not found!", response.getBody());
    }

    @Test
    void testSendMessage_success() {
        int customerId = 1;
        CustomerDto customer = new CustomerDto();
        customer.setId(customerId);
        customer.setName("Bank");
        customer.setPlan(Plan.BASIC);
        when(customerRepository.findById(customerId)).thenReturn(customer);
        when(messageService.sendSMS(any(String.class), any(int.class))).thenReturn("Message sent!");
        Message message = new Message(customerId, "sample message text");

        ResponseEntity<String> response = messageController.sendMessage(message);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Message sent!", response.getBody());
    }

    @Test
    void testGetBillAmountForCurrentMonth_customerNotFound() {
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(null);

        ResponseEntity<?> response = messageController.getBillAmountForCurrentMonth(customerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer not found!", response.getBody());
    }

    @Test
    void testGetBillAmountForCurrentMonth_customerFound() {
        int customerId = 1;
        CustomerDto customer = new CustomerDto();
        customer.setId(customerId);
        customer.setName("Bank");
        customer.setPlan(Plan.BASIC);
        when(customerRepository.findById(customerId)).thenReturn(customer);
        when(messageService.calculateCharge(customer)).thenReturn(BigDecimal.valueOf(200.0));

        ResponseEntity<?> response = messageController.getBillAmountForCurrentMonth(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(BigDecimal.valueOf(200.0), response.getBody());
    }

}