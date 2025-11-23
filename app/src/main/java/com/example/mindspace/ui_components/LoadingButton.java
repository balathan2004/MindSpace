package com.example.mindspace.ui_components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.mindspace.R;

public class LoadingButton extends LinearLayout {


    private LinearLayout button;
    private ProgressBar loader;
    private TextView loading_label;


    public LoadingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true);
        button = findViewById(R.id.loader_button);
        loader = findViewById(R.id.loader);
        loading_label = findViewById(R.id.loader_label);
    }


    public void setText(String text) {
        loading_label.setText(text);
    }

    public void onClick(OnClickListener listener) {
        button.setOnClickListener(listener);
    }

    public void showLoading() {
        button.setEnabled(false);
        loader.setClickable(false);
        loading_label.setVisibility(GONE);
        loader.setVisibility(VISIBLE);
    }

    public void hideLoading() {
        button.setEnabled(true);
        loading_label.setVisibility(VISIBLE);
        loader.setVisibility(GONE);
    }


}
