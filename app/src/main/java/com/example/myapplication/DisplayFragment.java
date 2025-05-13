package com.example.myapplication; // ZASTĄP WŁAŚCIWYM PAKIETEM

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    private static final String ARG_DATA_TO_DISPLAY = "arg_data_to_display";
    private String receivedData;

    public DisplayFragment() { /* Wymagany pusty konstruktor */ }

    // Metoda fabryczna (factory method) do tworzenia instancji fragmentu
    // i bezpiecznego przekazywania argumentów.
    public static DisplayFragment newInstance(String data) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATA_TO_DISPLAY, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            receivedData = getArguments().getString(ARG_DATA_TO_DISPLAY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        TextView textViewDisplay = view.findViewById(R.id.textView_display_data);

        if (receivedData != null) {
            textViewDisplay.setText(receivedData);
        } else {
            textViewDisplay.setText("Brak danych do wyświetlenia.");
        }
        return view;
    }
}