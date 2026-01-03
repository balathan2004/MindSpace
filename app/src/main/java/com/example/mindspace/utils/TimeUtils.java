package com.example.mindspace.utils;

import android.util.Log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public static String getISOString() {
        Instant now = Instant.now();
        return now.toString();
    }


    public static String format(long time, String pattern) {
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatFromNow(LocalDateTime date) {
        if (isLastWeek(date)) return "Last Week";
        if (isLastMonth(date)) return "Last Month";
        if (isLastYear(date)) return "Last Year";
        return "unknown";
    }

    public static LocalDateTime IsoDateParser(String isoTime) {

        LocalDateTime finalDate = LocalDateTime.now();

        if (isoTime == null) return finalDate;

        try {
            finalDate = LocalDateTime.parse(isoTime);


        } catch (Exception e) {
            Log.i("console", "IsoDateParser: ", e);
        }
        return finalDate;
    }


    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static boolean isLastWeek(LocalDateTime t) {
        return t.isAfter(now().minusWeeks(1));
    }

    public static boolean isLastMonth(LocalDateTime t) {
        return t.isAfter(now().minusMonths(1));
    }

    public static boolean isLastYear(LocalDateTime t) {
        return t.isAfter(now().minusYears(1));
    }


}
