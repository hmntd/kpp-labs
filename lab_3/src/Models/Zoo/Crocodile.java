package Models.Zoo;

import java.util.List;

import Enum.Food;

public class Crocodile extends Animal {

    public Crocodile(String name) {
        super(name, Food.FISH, 7.0, true, true, List.of("Turtles"));
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }

    @Override
    public void displayInfo() {
        System.out.println("--- Crocodile ---");
        super.displayInfo();
    }
}
