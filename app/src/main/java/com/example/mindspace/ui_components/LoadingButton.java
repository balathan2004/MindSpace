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

public class LoadingButton extends CardView {



    private ProgressBar loader;
    private TextView loading_label;

    private String InitLabel;

    private String LoadingLabel;


    public LoadingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true);

        loader = findViewById(R.id.loader);
        loading_label = findViewById(R.id.loader_label);
        loader.setVisibility(GONE);
    }


    public void setText(String InitText, String LoadingText) {
        this.InitLabel = InitText;
        this.LoadingLabel = LoadingText;
        if (loader.getVisibility() == GONE) {
            loading_label.setText(InitLabel);
        }
    }

    public void onClick(OnClickListener listener) {
        this.setOnClickListener(listener);
    }



    public void showLoading() {
        this.setEnabled(false);
        loader.setClickable(false);
        loading_label.setText(LoadingLabel);
//        loading_label.setVisibility(GONE);
        loader.setVisibility(VISIBLE);
    }

    public void hideLoading() {
        this.setEnabled(true);
        loading_label.setVisibility(VISIBLE);
        loading_label.setText(InitLabel);
        loader.setVisibility(GONE);

    }


}
