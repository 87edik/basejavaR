package com.urise.webapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainDate {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        Calendar cal = Calendar.getInstance();

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
    }
}
