package com.example.smsbillingsystem.utils;


import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.tuple.Pair;

public class DateHelper {
    public Pair<Date, Date> getCurrentMonthFirstAndLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();
        return Pair.of(startDate, endDate);
    }
}
