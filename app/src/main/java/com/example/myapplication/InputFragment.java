package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {

    private EditText editTextDataInput;

    public InputFragment() { /* Wymagany pusty konstruktor */ }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        editTextDataInput = view.findViewById(R.id.editText_data);
        Button buttonSendData = view.findViewById(R.id.button_send);

        buttonSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataToSend = editTextDataInput.getText().toString();

                // Komunikacja z Aktywnością
                // Sprawdzenie, czy Aktywność-host istnieje i jest właściwego typu
                if (getActivity() != null && getActivity() instanceof MainActivity) {
                    // Rzutowanie na typ MainActivity i wywołanie publicznej metody
                    ((MainActivity) getActivity()).handleDataFromInputFragment(dataToSend);
                }
            }
        });
        return view;
    }
}