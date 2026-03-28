package Models.Zoo;

import java.util.List;

import Enum.Food;

public abstract class Animal {
    private String name;
    private Food foodType;
    private double foodAmount;
    private boolean canReproduceInCaptivity;
    private boolean canShareEnclosureWithOwnKind;
    private List<String> compatibleSpecies;

    public Animal(String name, Food foodType, double foodAmount, boolean canReproduceInCaptivity, boolean canShareEnclosureWithOwnKind, List<String> compatibleSpecies) {
        this.name = name;
        this.foodType = foodType;
        this.foodAmount = foodAmount;
        this.canReproduceInCaptivity = canReproduceInCaptivity;
        this.canShareEnclosureWithOwnKind = canShareEnclosureWithOwnKind;
        this.compatibleSpecies = compatibleSpecies;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Food Type: " + foodType);
        System.out.println("Food Amount: " + foodAmount + " kg");
        System.out.println("Can reproduce in captivity: " + (canReproduceInCaptivity ? "Yes" : "No"));
        System.out.println("Can share enclosure with own kind: " + (canShareEnclosureWithOwnKind ? "Yes" : "No"));
        System.out.println("Compatible with: " + compatibleSpecies);
    }

    public abstract void makeSound();

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Food getFoodType() {
        return foodType;
    }

    public void setFoodType(Food foodType) {
        this.foodType = foodType;
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public boolean isCanReproduceInCaptivity() {
        return canReproduceInCaptivity;
    }

    public void setCanReproduceInCaptivity(boolean canReproduceInCaptivity) {
        this.canReproduceInCaptivity = canReproduceInCaptivity;
    }

    public boolean isCanShareEnclosureWithOwnKind() {
        return canShareEnclosureWithOwnKind;
    }

    public void setCanShareEnclosureWithOwnKind(boolean canShareEnclosureWithOwnKind) {
        this.canShareEnclosureWithOwnKind = canShareEnclosureWithOwnKind;
    }

    public List<String> getCompatibleSpecies() {
        return compatibleSpecies;
    }

    public void setCompatibleSpecies(List<String> compatibleSpecies) {
        this.compatibleSpecies = compatibleSpecies;
    }
}
