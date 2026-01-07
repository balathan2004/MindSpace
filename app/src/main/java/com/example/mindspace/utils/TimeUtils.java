package com.example.mindspace.utils;

import android.util.Log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public static String getISOString() {
        Instant now = Instant.now();
        return now.toString();
    }


    public static String format(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatFromNow(LocalDateTime date) {
        if(isSameWeek(date))return "This "+ date.getDayOfWeek();
        if (isLastWeek(date)) return "Last Week";
        if (isLastMonth(date)) return "Last Month";
        if (isLastYear(date)) return "Last Year";
        return "unknown";
    }

    public static LocalDateTime IsoDateParser(String isoTime) {

        LocalDateTime finalDate = now();

        if (isoTime == null) return finalDate;

        try {
            OffsetDateTime odt = OffsetDateTime.parse(isoTime);
            // Convert to LocalDateTime in system default timezone
            return odt.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

        } catch (Exception e) {
            Log.i("print", "IsoDateParser: ", e);
        }
        return finalDate;
    }


    public static LocalDateTime now() {
        return LocalDateTime.now();
    }


    public static boolean isSameWeek(LocalDateTime t){
        return t.isBefore(now().minusWeeks(1)) && t.isAfter(now());
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
