package com.example.myapplication; // ZASTĄP WŁAŚCIWYM PAKIETEM

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewInActivityDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInActivityDisplay = findViewById(R.id.textView_in_activity);

        // Załaduj fragmenty tylko przy pierwszym tworzeniu Aktywności,
        // aby uniknąć ich duplikacji po zmianach konfiguracji (np. obrót ekranu).
        if (savedInstanceState == null) {
            // Ładowanie InputFragment do odpowiedniego kontenera
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_input, new InputFragment())
                    .commit();

            // Ładowanie DisplayFragment z komunikatem początkowym
            DisplayFragment initialDisplay = DisplayFragment.newInstance("Oczekiwanie na dane z InputFragment...");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_display, initialDisplay)
                    .commit();
        }
    }

    /**
     * Publiczna metoda wywoływana przez InputFragment w celu przekazania danych.
     * @param data Tekst otrzymany z InputFragment.
     */
    public void handleDataFromInputFragment(String data) {
        // 1. Wyświetl otrzymane dane w TextView Aktywności
        textViewInActivityDisplay.setText("Aktywność otrzymała: " + data);

        // 2. Przekaż dane do DisplayFragment poprzez utworzenie nowej instancji
        //    i zastąpienie nią poprzedniej.
        DisplayFragment updatedDisplayFragment = DisplayFragment.newInstance("Dane z InputFragment: " + data);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_display, updatedDisplayFragment)
                // Opcjonalnie: .addToBackStack(null) dla możliwości cofania tej transakcji
                .commit();
    }
}