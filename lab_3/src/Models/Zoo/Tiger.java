package Models.Zoo;

import java.util.List;

import Enum.Food;

public class Tiger extends Animal {

    public Tiger(String name) {
        super(name, Food.MEAT, 10.0, true, false, List.of("None"));
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }

    @Override
    public void displayInfo() {
        System.out.println("--- Tiger ---");
        super.displayInfo();
    }
}
