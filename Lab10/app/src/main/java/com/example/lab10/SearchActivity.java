package com.example.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab10.Models.Car;
import com.example.lab10.Repositories.CarRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    private AutoCompleteTextView acBrand, acModel;
    private Spinner spCostFrom, spCostTo;
    private EditText etYearFrom, etYearTo;
    private com.google.android.material.slider.RangeSlider yearSlider;
    private Button btnMatches, btnReset;
    private List<Car> matchedCars = new ArrayList<>();

    private static String savedBrand = "";
    private static String savedModel = "";
    private static float savedYearFrom = 1980f;
    private static float savedYearTo = 2026f;
    private static int savedCostFromPos = 0;
    private static int savedCostToPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });

        acBrand = findViewById(R.id.acBrand);
        acModel = findViewById(R.id.acModel);
        etYearFrom = findViewById(R.id.etYearFrom);
        etYearTo = findViewById(R.id.etYearTo);
        yearSlider = findViewById(R.id.yearSlider);
        spCostFrom = findViewById(R.id.spCostFrom);
        spCostTo = findViewById(R.id.spCostTo);
        btnMatches = findViewById(R.id.btnMatches);
        btnReset = findViewById(R.id.btnReset);

        setupAutoCompleteAndSpinners();

        yearSlider.setValues(savedYearFrom, savedYearTo);
        etYearFrom.setText(String.valueOf((int)savedYearFrom));
        etYearTo.setText(String.valueOf((int)savedYearTo));

        restorePreviousState();
        setupListeners();

        yearSlider.addOnChangeListener((slider, value, fromUser) -> {
            if (fromUser) {
                List<Float> values = slider.getValues();
                etYearFrom.setText(String.valueOf(values.get(0).intValue()));
                etYearTo.setText(String.valueOf(values.get(1).intValue()));
                performSearch();
            }
        });

        TextWatcher yearTextWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    String sFrom = etYearFrom.getText().toString();
                    String sTo = etYearTo.getText().toString();
                    if (!sFrom.isEmpty() && !sTo.isEmpty()) {
                        float valFrom = Float.parseFloat(sFrom);
                        float valTo = Float.parseFloat(sTo);

                        if (valFrom >= yearSlider.getValueFrom() && valTo <= yearSlider.getValueTo() && valFrom <= valTo) {
                            yearSlider.setValues(valFrom, valTo);
                            performSearch();
                        }
                    }
                } catch (Exception e) {}
            }
            @Override public void afterTextChanged(Editable s) {}
        };

        etYearFrom.addTextChangedListener(yearTextWatcher);
        etYearTo.addTextChangedListener(yearTextWatcher);

        btnMatches.setOnClickListener(v -> {
            saveCurrentState();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("FILTERED_CARS", (Serializable) matchedCars);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnReset.setOnClickListener(v -> {
            resetFilters();
        });
    }

    private void setupAutoCompleteAndSpinners() {
        Set<String> brands = new HashSet<>();
        Set<String> models = new HashSet<>();

        for (Car car : CarRepository.allCars) {
            brands.add(car.brand);
            models.add(car.model);
        }

        acBrand.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>(brands)));
        acModel.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>(models)));

        String[] costs = {"Будь-яка", "1000", "10000", "100000"};
        ArrayAdapter<String> costAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, costs);
        spCostFrom.setAdapter(costAdapter);
        spCostTo.setAdapter(costAdapter);
    }

    private void restorePreviousState() {
        acBrand.setText(savedBrand, false);
        acModel.setText(savedModel, false);

        yearSlider.setValues(savedYearFrom, savedYearTo);

        spCostFrom.setSelection(savedCostFromPos);
        spCostTo.setSelection(savedCostToPos);

        performSearch();
    }

    private void saveCurrentState() {
        savedBrand = acBrand.getText().toString();
        savedModel = acModel.getText().toString();

        List<Float> values = yearSlider.getValues();
        savedYearFrom = values.get(0);
        savedYearTo = values.get(1);

        savedCostFromPos = spCostFrom.getSelectedItemPosition();
        savedCostToPos = spCostTo.getSelectedItemPosition();
    }

    private void resetFilters() {
        acBrand.setText("", false);
        acModel.setText("", false);
        spCostFrom.setSelection(0);
        spCostTo.setSelection(0);

        savedBrand = "";
        savedModel = "";

        savedYearFrom = 1990f;
        savedYearTo = 2026f;
        yearSlider.setValues(savedYearFrom, savedYearTo);

        etYearFrom.setText(String.valueOf((int)savedYearFrom));
        etYearTo.setText(String.valueOf((int)savedYearTo));

        savedCostFromPos = 0;
        savedCostToPos = 0;

        performSearch();
    }

    private void setupListeners() {
        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { performSearch(); }
            @Override public void afterTextChanged(Editable s) {}
        };
        acBrand.addTextChangedListener(watcher);
        acModel.addTextChangedListener(watcher);

        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { performSearch(); }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        };
        spCostFrom.setOnItemSelectedListener(spinnerListener);
        spCostTo.setOnItemSelectedListener(spinnerListener);
    }

    private void performSearch() {
        String inputBrand = acBrand.getText().toString().trim().toLowerCase();
        String inputModel = acModel.getText().toString().trim().toLowerCase();

        List<Float> yearValues = yearSlider.getValues();

        if (yearValues.size() < 2) return;

        float yFrom = yearValues.get(0);
        float yTo = yearValues.get(1);

        String cFromStr = spCostFrom.getSelectedItem().toString();
        String cToStr = spCostTo.getSelectedItem().toString();

        matchedCars.clear();

        boolean isAnyFieldFilled = !inputBrand.isEmpty() || !inputModel.isEmpty() ||
                !cFromStr.equals("Будь-яка") || !cToStr.equals("Будь-яка") ||
                yFrom > 1990f || yTo < 2026f;

        if (!isAnyFieldFilled) {
            updateButton(0);
            return;
        }

        for (Car car : CarRepository.allCars) {
            boolean matchBrand = inputBrand.isEmpty() || car.brand.toLowerCase().startsWith(inputBrand);
            boolean matchModel = inputModel.isEmpty() || car.model.toLowerCase().startsWith(inputModel);
            boolean matchYear = car.year >= yFrom && car.year <= yTo;

            int minCost = cFromStr.equals("Будь-яка") ? 0 : Integer.parseInt(cFromStr);
            int maxCost = cToStr.equals("Будь-яка") ? Integer.MAX_VALUE : Integer.parseInt(cToStr);
            boolean matchCost = car.cost >= minCost && car.cost <= maxCost;

            if (matchBrand && matchModel && matchYear && matchCost) {
                matchedCars.add(car);
            }
        }

        updateButton(matchedCars.size());
    }

    private void updateButton(int count) {
        btnMatches.setText("Matches: " + count);
        btnMatches.setEnabled(count > 0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}