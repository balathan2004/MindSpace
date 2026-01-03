package com.example.mindspace.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Utils {


    public static String generateShortUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    public static void ShowToast(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void Navigate(Context from, Class to, Boolean clearStack) {

        Intent page = new Intent(from, to);
        if (clearStack) {
            page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            from.startActivity(page);
            return;
        }
        from.startActivity(page);
    }
}
