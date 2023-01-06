package com.example.smsbillingsystem.service;

import com.example.smsbillingsystem.dao.MessageRepository;
import com.example.smsbillingsystem.dto.CustomerDto;
import com.example.smsbillingsystem.dto.MessageDto;
import com.example.smsbillingsystem.model.Plan;
import com.example.smsbillingsystem.utils.DateHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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
        return "Message sent successfully!";
    }

    public BigDecimal calculateBillAmount(CustomerDto customer) {
        Plan plan = customer.getPlan();
        int freeMessages = plan.getFreeMessages();

        Pair<Date, Date> currentMonthFirstAndLastDay = new DateHelper().getCurrentMonthFirstAndLastDay();
        Date startDate = currentMonthFirstAndLastDay.getLeft();
        Date endDate = currentMonthFirstAndLastDay.getRight();

        int messagesSent = messageRepository.countByCustomerIdAndCreatedAtBetween(customer.getId(), startDate, endDate);

        int billableNumOfMessages = Math.max(messagesSent - freeMessages, 0);
        return plan.getPricePerMessage().multiply(new BigDecimal(billableNumOfMessages));
    }
}
