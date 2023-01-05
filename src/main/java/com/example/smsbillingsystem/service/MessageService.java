package com.example.smsbillingsystem.service;

import com.example.smsbillingsystem.dao.MessageRepository;
import com.example.smsbillingsystem.dto.CustomerDto;
import com.example.smsbillingsystem.dto.MessageDto;
import com.example.smsbillingsystem.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String sendSMS(String message, int customerId) {
        MessageDto messageDto = new MessageDto();
        messageDto.setCustomerId(customerId);
        messageDto.setText(message);
        messageRepository.save(messageDto);
        return "Message sent successfully! Cost of message: 0";
    }

    public BigDecimal calculateCharge(CustomerDto customer) {
        Plan plan = customer.getPlan();
        int freeMessages = plan.getFreeMessages();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        int messagesSent = messageRepository.countByCustomerIdAndCreatedAtBetween(customer.getId(), startDate, endDate);

        int numChargedMessages = Math.max(messagesSent - freeMessages, 0);
        return plan.getPricePerMessage().multiply(new BigDecimal(numChargedMessages));
    }
}
