package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    private City current;
    private int index;

    public void setCurrent(City current) {
        this.current = current;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public City getCurrent() {
        return current;
    }

    public int getIndex() {
        return index;
    }

    interface EditCityDialogListener {
        void editCity(City city, int index);
    }
    private EditCityDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        editCityName.setText(current.getName());
        editProvinceName.setText(current.getProvince());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view).setTitle("Change a city").setNegativeButton("Cancel", null).setPositiveButton("Add", (dialog, which) -> {
            String cityName = editCityName.getText().toString();
            String provinceName = editProvinceName.getText().toString();
            listener.editCity(new City(cityName, provinceName), this.index);
        }).create();
    }
}
