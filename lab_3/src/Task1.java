import Models.Car;
import Services.ParkingService;
import data.Generators.CarGenerator;

public class Task1 {
    public static void main(String[] args) {
        Car[] parkingLotDatabase = CarGenerator.generateCarArray(7);
        ParkingService service = new ParkingService();

        try {
            service.printPresentCars(parkingLotDatabase);

            service.printDepartedCars(parkingLotDatabase);

            String testPlateToFind = parkingLotDatabase[2].getLicensePlate();
            service.findCarByLicensePlate(parkingLotDatabase, testPlateToFind);

            service.findCarByLicensePlate(parkingLotDatabase, "XX9999XX");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
