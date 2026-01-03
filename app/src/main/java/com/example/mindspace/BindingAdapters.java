package com.example.mindspace;


import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class BindingAdapters {

    @BindingAdapter({"formattedTime", "timePattern", "prefixText"})
    public static void setFormattedTime(TextView view, String isoTime, String pattern, String prefix) {

        if (isoTime == null || isoTime.isEmpty()) return;

        try {
            SimpleDateFormat iso =
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            iso.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date = iso.parse(isoTime);

            SimpleDateFormat out =
                    new SimpleDateFormat(pattern, Locale.getDefault());

            String text = prefix + out.format(date);

            view.setText(text);

        } catch (Exception e) {
            String text = prefix + isoTime;
            view.setText(text); // fallback
        }
    }

}
