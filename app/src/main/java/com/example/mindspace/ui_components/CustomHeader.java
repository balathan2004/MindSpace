package com.example.mindspace.ui_components;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mindspace.R;

import java.util.ArrayList;
import java.util.List;

public class CustomHeader extends LinearLayout {


    private TextView headerTitle;
    private ImageView headerLeft;
    private LinearLayout headerRight;

    Context context;

    public CustomHeader(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context = context;
        init(attrs);

    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.custom_header, this, true);
        headerTitle = findViewById(R.id.headerTitle);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomHeader);
            String title = a.getString(R.styleable.CustomHeader_headerTitle);
            a.recycle();
            if (title != null) {
                headerTitle.setText(title);
            }
        }

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerLeft = findViewById(R.id.back_arrow);
        headerRight = findViewById(R.id.headerRight);
        boolean canShowBack = !((Activity) context).isTaskRoot();
        headerLeft.setVisibility(canShowBack ? VISIBLE : GONE);
    }

    public void setTitle(String text) {
        if (headerTitle != null) headerTitle.setText(text);
    }

    public void showBack(boolean show) {
        if (headerLeft == null) return;

        if (!show) {
            headerLeft.setVisibility(GONE);
        }
    }

    public LinearLayout getHeaderRight() {
        return headerRight;
    }

    public void addRight(View view) {
        headerRight.addView(view);
    }
}
