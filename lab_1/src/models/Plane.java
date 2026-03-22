package src.models;

public class Plane {
    private double tankCapacity;
    private double currentFuel;

    public Plane(double tankCapacity) {
        this.tankCapacity = tankCapacity;
        this.currentFuel = tankCapacity;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double fuel) {
        if (fuel < 0) {
            this.currentFuel = 0;
            return;
        }

        if (fuel > tankCapacity) {
            this.currentFuel = tankCapacity;
            return;
        }

        this.currentFuel = fuel;
    }
}