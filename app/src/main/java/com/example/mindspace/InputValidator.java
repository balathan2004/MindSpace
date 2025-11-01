package com.example.mindspace;

import android.widget.EditText;

public class InputValidator {

    private EditText input;
    private int MinLen=6;

    public InputValidator(EditText input) {
        this.input = input;
    }


    String validate() {
        String value = this.input.getText().toString();

        if (value.isEmpty()) {
            input.setError("Input is empty");
            return null;
        }
        if (value.length() < MinLen) {
            input.setError("Input should be " + MinLen + "chars");
            return null;
        }
        return value;

    }

}
