package com.webcoder.vaccine.api.commons;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtil {

    public String getDate(int day) {
        Calendar today = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
        today.add(Calendar.DATE, day);

        SimpleDateFormat sd = new SimpleDateFormat(Constants.SLOTS_DATE_PATTERN);
        return sd.format(new Date(today.getTimeInMillis()));
    }
}
