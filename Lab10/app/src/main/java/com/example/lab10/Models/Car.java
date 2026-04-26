package com.example.lab10.Models;

import java.io.Serializable;

public class Car implements Serializable {
    public String brand;
    public String model;
    public int year;
    public String description;
    public int cost;
    public int imageResId;

    public Car(String brand, String model, int year, String description, int cost, int imageResId) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.cost = cost;
        this.imageResId = imageResId;
    }
}
