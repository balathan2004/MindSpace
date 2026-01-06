package com.example.mindspace;

import android.widget.EditText;

public class InputValidator {

    private EditText input;
    private int minlen = 3;

    public InputValidator(EditText input) {
        this.input = input;
    }

    public InputValidator setMinLength(int minLength) {
        this.minlen = minLength;
        return this;
    }

    String validate() {
        String value = this.input.getText().toString();

        if (value.isEmpty()) {
            input.setError("Input is empty");
            return null;
        }
        if (value.length() < minlen) {
            input.setError("Input should be " + minlen + "chars");
            return null;
        }
        return value;

    }

}
