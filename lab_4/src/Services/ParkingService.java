package Services;

import Interfaces.DataAccess;
import Models.Car;
import java.io.*;

public class ParkingService implements DataAccess {
    @Override
    public void writeCarsToFile(Car[] cars, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(cars);
        }
    }

    @Override
    public Car[] readCarsFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Car[]) ois.readObject();
        }
    }

    public void printPresentCars(Car[] cars) {
        System.out.println("\n--- CARS CURRENTLY ON THE LOT ---");
        boolean found = false;
        for (Car c : cars) {
            if (c.isPresent()) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found)
            System.out.println("The parking lot is completely empty.");
    }

    public void printDepartedCars(Car[] cars) {
        System.out.println("\n--- CARS THAT HAVE LEFT ---");
        boolean found = false;
        for (Car c : cars) {
            if (!c.isPresent()) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found)
            System.out.println("No cars have left today.");
    }

    public void findCarByLicensePlate(Car[] cars, String searchPlate) {
        System.out.println("\n--- SEARCH RESULT FOR PLATE: " + searchPlate + " ---");
        boolean found = false;

        Car searchDummy = new Car();
        searchDummy.setLicensePlate(searchPlate);

        for (Car c : cars) {
            if (c.equals(searchDummy)) {
                System.out.println("[FOUND] " + c.toString());
                System.out.println("   Address: " + c.getOwnerAddress());
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("Car with license plate " + searchPlate + " not found.");
    }
}
