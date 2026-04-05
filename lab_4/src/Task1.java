import Models.Car;
import Services.ParkingService;
import data.Generators.CarGenerator;

import java.io.IOException;
import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("--- GENERATING CARS ---");
        Car[] cars = CarGenerator.generateCarArray(5);

        System.out.println("\n--- ORIGINAL CARS ---");
        for (Car car : cars) {
            System.out.println(car);
        }

        System.out.println("\n--- SORTED CARS (by timestamp, then brand, then plate) ---");
        Arrays.sort(cars);
        for (Car car : cars) {
            System.out.println(car);
        }

        ParkingService parkingService = new ParkingService();
        String fileName = "cars_data.dat";

        System.out.println("\n--- WRITING TO FILE ---");
        try {
            parkingService.writeCarsToFile(cars, fileName);
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("\n--- READING FROM FILE ---");
        try {
            Car[] loadedCars = parkingService.readCarsFromFile(fileName);
            System.out.println("Data successfully read from " + fileName);
            for (Car car : loadedCars) {
                System.out.println(car);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
