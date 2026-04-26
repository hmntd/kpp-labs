package com.example.lab10.Repositories;

import com.example.lab10.Models.Car;
import com.example.lab10.R;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    public static List<Car> allCars = new ArrayList<>();

    public static void initData() {
        if (allCars.isEmpty()) {
            allCars.add(new Car("Toyota", "Camry", 2018, "Надійний седан", 15000, R.drawable.car1));
            allCars.add(new Car("Ford", "Scorpio", 1990, "Економне авто", 1800, R.drawable.car2));
            allCars.add(new Car("BMW", "X5", 2026, "Преміум позашляховик", 55000, R.drawable.car3));
            allCars.add(new Car("Audi", "RS6", 2020, "Комфорт та швидкість", 42000, R.drawable.car4));
        }
    }
}
