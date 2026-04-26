package com.example.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import com.example.lab10.Models.Car;
import com.example.lab10.Repositories.CarRepository;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Car> displayedCars = new ArrayList<>();
    private ArrayAdapter<Car> adapter;

    private final ActivityResultLauncher<Intent> searchLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Отримуємо відфільтрований список з SearchActivity
                    List<Car> filtered = (List<Car>) result.getData().getSerializableExtra("FILTERED_CARS");
                    displayedCars.clear();
                    if (filtered != null) displayedCars.addAll(filtered);
                    adapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarRepository.initData();
        displayedCars.addAll(CarRepository.allCars);

        listView = findViewById(R.id.listViewCars);

        adapter = new ArrayAdapter<Car>(this, R.layout.item_car, displayedCars) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_car, parent, false);
                }

                ImageView imgCar = convertView.findViewById(R.id.imgCar);
                TextView tvInfo = convertView.findViewById(R.id.tvCarInfo);

                Car car = displayedCars.get(position);

                imgCar.setImageResource(car.imageResId);

                tvInfo.setText(car.brand + " " + car.model + " (" + car.year + ")\nЦена: $" + car.cost + "\n" + car.description);

                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            searchLauncher.launch(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}