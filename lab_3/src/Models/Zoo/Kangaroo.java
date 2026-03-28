package Models.Zoo;

import java.util.List;

import Enum.Food;

public class Kangaroo extends Animal {

    public Kangaroo(String name) {
        super(name, Food.PLANTS, 15.0, true, true, List.of("Utkonos"));
    }

    @Override
    public void makeSound() {
        System.out.println("Chortle!");
    }

    @Override
    public void displayInfo() {
        System.out.println("--- Kangaroo ---");
        super.displayInfo();
    }
}
