package com.example.mindspace.ui_components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mindspace.R;

public class CustomLoader extends LinearLayout {

    LinearLayout loader;
    TextView loaderTitle;

    ProgressBar progressBar;


    public CustomLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_loader, this, true);
        loader = findViewById(R.id.loader);
        progressBar = findViewById(R.id.progressBar);
        loaderTitle = findViewById(R.id.loader_title);
    }

    public void setLoaderTitle(String message) {
        loaderTitle.setText(message);
    }

    public void setVisibility(boolean state) {
        loader.setVisibility(state ? VISIBLE : GONE);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }


}
