package com.example.mindspace.ui_components;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mindspace.R;

import java.util.List;

public class LabelValue {

    TextView labelText;
    TextView valueText;

    LinearLayout valueContainer;
    private final Context context;

    public LabelValue(Context context) {
        this.context = context;
    }

    public void AttachLabelValue(LinearLayout parent, String label, String value) {

        View row = LayoutInflater.from(context).inflate(R.layout.label_value, parent, false);
        valueContainer = row.findViewById(R.id.valueContainer);
        labelText = row.findViewById(R.id.labelText);
        valueText = row.findViewById(R.id.valueText);

        labelText.setText(label);
        valueText.setText(value);

        parent.addView(row);

    }

    public void AttachLabelValue(LinearLayout parent, String label, List<String> value) {
        View row = LayoutInflater.from(context).inflate(R.layout.label_value, parent, false);
        valueContainer = row.findViewById(R.id.valueContainer);
        labelText = row.findViewById(R.id.labelText);


        labelText.setText(label);

        valueContainer.removeAllViews();

        for (String text : value) {
            TextView valueWrapper = (TextView) LayoutInflater.from(context).inflate(R.layout.label_value_value_text, parent, false);
            valueWrapper.setText(text);
            valueContainer.addView(valueWrapper);
        }


        parent.addView(row);
    }


}

