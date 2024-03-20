package com.example.unitconvertionapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
import android.widget.Spinner;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinnerSourceUnit = findViewById(R.id.source_unit_spinner);
        Spinner spinnerDestinationUnit = findViewById(R.id.destination_unit_spinner);
        EditText valueInput = findViewById(R.id.value_input);
        Button convertButton = findViewById(R.id.convert_button);
        TextView resultOutput = findViewById(R.id.result_output);

        convertButton.setOnClickListener(v ->
        {
            String sourceUnit = spinnerSourceUnit.getSelectedItem().toString();
            String destinationUnit = spinnerDestinationUnit.getSelectedItem().toString();
            double inputValue = Double.parseDouble(valueInput.getText().toString());
            double result = UnitConverter.convertLength(inputValue, sourceUnit, destinationUnit);
            resultOutput.setText(String.format(Locale.getDefault(), "%.2f", result));
        });
    }

    static class UnitConverter {
        public static double convertLength(double value, String sourceUnit, String destinationUnit) {
            double valueInMeters;

            switch (sourceUnit) {
                case "Inches":
                    valueInMeters = value * 0.0254;
                    break;
                case "Feet":
                    valueInMeters = value * 0.3048;
                    break;
                case "Yards":
                    valueInMeters = value * 0.9144;
                    break;
                case "Miles":
                    valueInMeters = value * 1609.34;
                    break;
                case "Centimeters":
                    valueInMeters = value * 0.01;
                    break;
                case "Meters":
                    valueInMeters = value;
                    break;
                case "Kilometers":
                    valueInMeters = value * 1000;
                    break;
                default:
                    return -1;
            }

            switch (destinationUnit) {
                case "Inches":
                    return valueInMeters / 0.0254;
                case "Feet":
                    return valueInMeters / 0.3048;
                case "Yards":
                    return valueInMeters / 0.9144;
                case "Miles":
                    return valueInMeters / 1609.34;
                case "Centimeters":
                    return valueInMeters / 0.01;
                case "Meters":
                    return valueInMeters;
                case "Kilometers":
                    return valueInMeters / 1000;
                default:
                    return -1;
            }
        }
    }
}