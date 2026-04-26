package ua.edu.lab8;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTask1 = findViewById(R.id.btnTask1);
        Button btnTask2 = findViewById(R.id.btnTask2);

        btnTask1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GuessActivity.class);
            startActivity(intent);
        });

        btnTask2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalcActivity.class);
            startActivity(intent);
        });
    }
}
