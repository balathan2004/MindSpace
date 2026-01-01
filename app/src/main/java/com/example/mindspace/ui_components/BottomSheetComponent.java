package com.example.mindspace.ui_components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mindspace.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetComponent extends BottomSheetDialogFragment {

    private static final String ARG_LAYOUT = "layout";

    private View contentView;

    public BottomSheetComponent() {
    }

    public static BottomSheetComponent newInstance(View contentView) {
        BottomSheetComponent sheet = new BottomSheetComponent();
        sheet.contentView = contentView;
        return sheet;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup parent, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.bottom_sheet_wrapper, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout container = view.findViewById(R.id.sheet_container);

        if (contentView != null) {
            if (contentView.getParent() != null) {
                ((ViewGroup) contentView.getParent()).removeView(contentView);
            }
            container.addView(contentView);
        }


    }


}
