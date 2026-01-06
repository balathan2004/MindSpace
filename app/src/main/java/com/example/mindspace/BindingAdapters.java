package com.example.mindspace;

import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.mindspace.utils.TimeUtils;
import com.example.mindspace.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BindingAdapters {

    @BindingAdapter({"formattedTime", "timePattern", "prefixText"})
    public static void setFormattedTime(TextView view, String isoTime, String pattern, String prefix) {

        if (isoTime == null || isoTime.isEmpty()) return;


        LocalDateTime date = TimeUtils.IsoDateParser(isoTime);

        String out = date.format(DateTimeFormatter.ofPattern(pattern));

        String text = prefix + out;

        view.setText(text);

    }

    @BindingAdapter({"formattedTime", "prefixText"})
    public static void setFormattedTime(TextView view, String isoTime, String prefix) {

        Log.i("print", "setFormattedTime: " + isoTime);

        LocalDateTime date = TimeUtils.IsoDateParser(isoTime);

        String out = TimeUtils.format(date, "dd MM yyyy hh mm ");

        String text = prefix + " " + out;

        view.setText(text);

    }

}
