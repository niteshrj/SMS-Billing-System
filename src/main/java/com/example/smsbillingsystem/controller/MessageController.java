package com.example.smsbillingsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/customers")
    public ResponseEntity<String> message() {
        return new ResponseEntity("Message sent successfully!", HttpStatus.OK);
    }
}
