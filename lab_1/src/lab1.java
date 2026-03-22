package src;

import java.util.Map;

import src.models.Plane;
import src.services.FileManager;
import src.services.FlightService;
import src.validation.DistanceValidator;
import src.validation.PlaneValidator;

public class lab1 {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        FlightService flightService = new FlightService();

        try {
            System.out.println("Loading Tank Configuration");
            Map<String, Double> tankData = fileManager.readJson("data/plane.json");
            PlaneValidator.validate(tankData);

            double tankCapacity = tankData.get("tank");
            Plane myPlane = new Plane(tankCapacity);
            System.out.println("Tank capacity initialized: " + tankCapacity + "L");

            System.out.println("\nLoading Flight Plan");
            Map<String, Double> flightData = fileManager.readJson("data/distance.json");
            DistanceValidator.validate(flightData);

            double distAB = flightData.get("distAB");
            double distBC = flightData.get("distBC");
            double weight = flightData.get("weight");

            System.out.println("Flight Path: A -> B (" + distAB + "km) -> C (" + distBC + "km)");
            System.out.println("Cargo Weight: " + weight + "kg");

            double fuelNeededAB = flightService.calculateRequiredFuel(distAB, weight, tankCapacity);

            myPlane.setCurrentFuel(myPlane.getTankCapacity() - fuelNeededAB);
            System.out.println("Arrived at Point B. Fuel remaining: " + myPlane.getCurrentFuel() + "L");

            double fuelNeededBC = flightService.calculateRequiredFuel(distBC, weight, tankCapacity);

            double refuelAmount = 0;
            if (myPlane.getCurrentFuel() < fuelNeededBC) {
                refuelAmount = fuelNeededBC - myPlane.getCurrentFuel();
            }

            System.out.println("\n--- Result ---");
            System.out.println("Minimum fuel to add at Point B: " + refuelAmount + "L");
            System.out.println("Flight to Point C is possible.");

        } catch (Exception e) {
            System.err.println("\n[FLIGHT ABORTED]");
            System.err.println("Reason: " + e.getMessage());
        }
    }
}
