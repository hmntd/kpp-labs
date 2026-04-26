package com.example.lab9;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etAge;
    private TextView tvSalaryLabel, tvResult;
    private SeekBar sbSalary;
    private CheckBox cbExperience, cbSkills, cbTravel;
    private RadioGroup rgQ1, rgQ2, rgQ3, rgQ4, rgQ5;
    private Button btnSubmit;

    private int currentSalary = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        tvSalaryLabel = findViewById(R.id.tvSalaryLabel);
        tvResult = findViewById(R.id.tvResult);
        sbSalary = findViewById(R.id.sbSalary);

        cbExperience = findViewById(R.id.cbExperience);
        cbSkills = findViewById(R.id.cbSkills);
        cbTravel = findViewById(R.id.cbTravel);

        rgQ1 = findViewById(R.id.rgQ1);
        rgQ2 = findViewById(R.id.rgQ2);
        rgQ3 = findViewById(R.id.rgQ3);
        rgQ4 = findViewById(R.id.rgQ4);
        rgQ5 = findViewById(R.id.rgQ5);

        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupListeners() {
        sbSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentSalary = 500 + (progress * 100);
                tvSalaryLabel.setText("Бажана ЗП: " + currentSalary + " USD");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isNameFilled = !etName.getText().toString().trim().isEmpty();
                boolean isAgeFilled = !etAge.getText().toString().trim().isEmpty();
                btnSubmit.setEnabled(isNameFilled && isAgeFilled);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etName.addTextChangedListener(inputWatcher);
        etAge.addTextChangedListener(inputWatcher);

        btnSubmit.setOnClickListener(v -> processInterview());
    }

    private void processInterview() {
        tvResult.setVisibility(View.VISIBLE);

        int age = Integer.parseInt(etAge.getText().toString());
        if (age < 21 || age > 40) {
            tvResult.setText("Відмова: Кандидат не підходить за віком. Допустимий вік: 21-40 років.");
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            return;
        }

        if (currentSalary > 3000) {
            tvResult.setText("Відмова: Очікування по зарплаті (" + currentSalary + " USD) не відповідають бюджету компанії (1000-3000 USD).");
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            return;
        }

        int score = 0;

        if (rgQ1.getCheckedRadioButtonId() == R.id.rbQ1_correct) score += 2;
        if (rgQ2.getCheckedRadioButtonId() == R.id.rbQ2_correct) score += 2;
        if (rgQ3.getCheckedRadioButtonId() == R.id.rbQ3_correct) score += 2;
        if (rgQ4.getCheckedRadioButtonId() == R.id.rbQ4_correct) score += 2;
        if (rgQ5.getCheckedRadioButtonId() == R.id.rbQ5_correct) score += 2;

        if (cbExperience.isChecked()) score += 2;
        if (cbSkills.isChecked()) score += 1;
        if (cbTravel.isChecked()) score += 1;

        if (score >= 10) {
            tvResult.setText("Вітаємо, " + etName.getText().toString() + "! Ви успішно пройшли тест.\nВаш бал: " + score + "\nКонтакти HR: hr@itcompany.com, +380991234567");
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            return;
        }

        tvResult.setText("На жаль, ви не набрали достатньо балів.\nВаш бал: " + score + " з необхідних 10.");
        tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
    }
}