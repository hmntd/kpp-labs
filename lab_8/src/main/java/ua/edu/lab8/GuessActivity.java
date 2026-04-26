package ua.edu.lab8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class GuessActivity extends AppCompatActivity {

    private int targetNumber;
    private TextView tvStatus;
    private EditText etGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        tvStatus = findViewById(R.id.tvGameStatus);
        etGuess = findViewById(R.id.etUserGuess);
        Button btnCheck = findViewById(R.id.btnCheckGuess);
        Button btnRestart = findViewById(R.id.btnRestartGame);

        generateNewNumber();

        btnCheck.setOnClickListener(v -> {
            String input = etGuess.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Введіть число!", Toast.LENGTH_SHORT).show();
                return;
            }

            int userGuess = Integer.parseInt(input);
            if (userGuess == targetNumber) {
                tvStatus.setText("Вітаю! Ти вгадав число: " + targetNumber);
            } else if (userGuess < targetNumber) {
                tvStatus.setText("Моє число БІЛЬШЕ за " + userGuess);
            } else {
                tvStatus.setText("Моє число МЕНШЕ за " + userGuess);
            }
            etGuess.setText("");
        });

        btnRestart.setOnClickListener(v -> generateNewNumber());
    }

    private void generateNewNumber() {
        targetNumber = new Random().nextInt(100) + 1;
        tvStatus.setText("Я загадав нове число від 1 до 100. Спробуй вгадати!");
        etGuess.setText("");
    }
}
