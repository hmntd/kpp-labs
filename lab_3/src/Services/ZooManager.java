package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Enum.Food;
import Models.Zoo.Animal;

public class ZooManager {
    private List<Animal> animals;

    public ZooManager() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void displayAllAnimalInfo() {
        System.out.println("--- All Animals in the Zoo ---");
        for (Animal animal : animals) {
            animal.displayInfo();
            animal.makeSound();
            System.out.println();
        }
    }

    public void countAnimalsByType() {
        System.out.println("--- Animal Count by Type ---");
        Map<String, Integer> animalCounts = new HashMap<>();
        for (Animal animal : animals) {
            String animalType = animal.getClass().getSimpleName();
            animalCounts.put(animalType, animalCounts.getOrDefault(animalType, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : animalCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
    }

    public void calculateMonthlyFoodNeeds() {
        System.out.println("--- Monthly Food Needs (30 days) ---");
        Map<Food, Double> foodNeeds = new HashMap<>();
        for (Animal animal : animals) {
            Food foodType = animal.getFoodType();
            double dailyAmount = animal.getFoodAmount();
            foodNeeds.put(foodType, foodNeeds.getOrDefault(foodType, 0.0) + dailyAmount);
        }

        for (Map.Entry<Food, Double> entry : foodNeeds.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() * 30) + " kg");
        }
        System.out.println();
    }
}
