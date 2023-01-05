package com.example.smsbillingsystem.controller;

import com.example.smsbillingsystem.dao.CustomerRepository;
import com.example.smsbillingsystem.dao.MessageRepository;
import com.example.smsbillingsystem.dto.CustomerDto;
import com.example.smsbillingsystem.model.Message;
import com.example.smsbillingsystem.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private final CustomerRepository customerRepository;

    private final MessageService messageService;

    @Autowired
    public MessageController(CustomerRepository customerRepository, MessageService messageService, MessageRepository messageRepository) {
        this.customerRepository = customerRepository;
        this.messageService = messageService;
    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        CustomerDto customer = customerRepository.findById(message.getCustomerId());
        if(customer == null) {
            return new ResponseEntity<>("Customer not found!", HttpStatus.NOT_FOUND);
        }
        String acknowledgement = messageService.sendSMS(message.getText(), message.getCustomerId());
        return new ResponseEntity<>(acknowledgement, HttpStatus.OK);
    }

    @GetMapping("/bill")
    public ResponseEntity<?> getBillAmountForCurrentMonth(@RequestParam int customer_id) {
        CustomerDto customer = customerRepository.findById(customer_id);
        if(customer == null) {
            return new ResponseEntity<>("Customer not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messageService.calculateCharge(customer), HttpStatus.OK);
    }
}
