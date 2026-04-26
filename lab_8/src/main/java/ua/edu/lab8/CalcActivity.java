package ua.edu.lab8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalcActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double valueOne = Double.NaN;
    private double valueTwo;
    private char currentAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        tvDisplay = findViewById(R.id.tvDisplay);

        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        
        View.OnClickListener numberClickListener = v -> {
            Button b = (Button) v;
            if (tvDisplay.getText().toString().equals("0")) {
                tvDisplay.setText(b.getText().toString());
            } else {
                tvDisplay.setText(tvDisplay.getText().toString() + b.getText().toString());
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        findViewById(R.id.btnAdd).setOnClickListener(v -> setAction('+'));
        findViewById(R.id.btnSub).setOnClickListener(v -> setAction('-'));
        findViewById(R.id.btnMul).setOnClickListener(v -> setAction('*'));
        findViewById(R.id.btnDiv).setOnClickListener(v -> setAction('/'));

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            valueOne = Double.NaN;
            valueTwo = Double.NaN;
            tvDisplay.setText("0");
        });

        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            calculate();
            currentAction = '=';
            tvDisplay.setText(String.valueOf(valueOne));
            valueOne = Double.NaN;
        });
    }

    private void setAction(char action) {
        if (!Double.isNaN(valueOne)) {
            calculate();
        } else {
            valueOne = Double.parseDouble(tvDisplay.getText().toString());
        }
        currentAction = action;
        tvDisplay.setText("0");
    }

    private void calculate() {
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(tvDisplay.getText().toString());
            switch (currentAction) {
                case '+': valueOne = valueOne + valueTwo; break;
                case '-': valueOne = valueOne - valueTwo; break;
                case '*': valueOne = valueOne * valueTwo; break;
                case '/': valueOne = valueOne / valueTwo; break;
            }
        }
    }
}