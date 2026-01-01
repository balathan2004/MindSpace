package com.example.mindspace.ui_components;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mindspace.R;

public class LabelValue {


    private final Context context;

    public LabelValue(Context context) {
        this.context = context;
    }

    public void AttachLabelValue(LinearLayout parent, String label, String value) {

        View row = LayoutInflater.from(context).inflate(R.layout.label_value, parent, false);

        TextView labelText = row.findViewById(R.id.labelText);
        TextView valueText = row.findViewById(R.id.valueText);

        labelText.setText(label);
        valueText.setText(value);

        parent.addView(row);

    }
}

