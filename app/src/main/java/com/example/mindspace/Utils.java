package com.example.mindspace;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Utils {


    public static String getISOString() {

        Instant now = Instant.now();

        return now.toString();

    }


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
