package brainacad_org.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import brainacad_org.models.Car;
import brainacad_org.models.Driver;
import brainacad_org.models.Trip;
import brainacad_org.models.TripRequest;

public class AutobaseService {
    private List<Driver> drivers = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<Trip> tripHistory = new ArrayList<>();
    private final String LOG_FILE = "storage/logs/trip_logs.txt";

    public AutobaseService(List<Driver> drivers, List<Car> cars) {
        this.drivers = drivers;
        this.cars = cars;
    }

    public Optional<Car> assignCar(TripRequest request) {
        return cars.stream()
                .filter(car -> car.isFree() && !car.isBroken() && car.getCapacity() >= request.getWeight())
                .min(Comparator.comparingDouble(car -> car.getCapacity() - request.getWeight()));
    }

    public Optional<Driver> assignDriver(TripRequest request, Car car) {
        int requiredTotalExp = request.getRequiredExperience() + car.getComplexity()
                + (int) (request.getDistance() / 500);
        return drivers.stream()
                .filter(Driver::isFree)
                .filter(driver -> driver.getExperience() >= requiredTotalExp)
                .findFirst();
    }

    public Trip dispatch(TripRequest request) {
        Car car = assignCar(request).orElseThrow(() -> new RuntimeException("Немає вільного або підходящого авто!"));
        Driver driver = assignDriver(request, car).orElseThrow(() -> new RuntimeException("Немає підходящого водія!"));

        car.setFree(false);
        driver.setFree(false);

        Trip trip = new Trip(request, driver, car, false);
        logToFile("Заявку розподілено: " + request.getDestination() + " -> Водій: " + driver.getName() + " | Авто: "
                + car.getPlateNumber());
        return trip;
    }

    public void reportBreakdown(Trip trip) {
        trip.getCar().setBroken(true);
        logToFile("АВАРІЯ! Авто " + trip.getCar().getPlateNumber() + " зламалося в дорозі. Заявка на ремонт.");
    }

    public void completeTrip(Trip trip) {
        if (trip.getCar().isBroken()) {
            logToFile("Авто " + trip.getCar().getPlateNumber() + " відремонтовано.");
            trip.getCar().repair();
        }

        trip.setCompleted(true);
        trip.getDriver().setEarnings(trip.getDriver().getEarnings() + trip.getRequest().getPayout());

        trip.getDriver().setFree(true);
        trip.getCar().setFree(true);
        tripHistory.add(trip);

        logToFile("Рейс до " + trip.getRequest().getDestination() + " успішно завершено. Виплата водію: "
                + trip.getRequest().getPayout());
    }

    private void logToFile(String message) {
        try {
            Path path = Paths.get(LOG_FILE);
            String logEntry = new Date() + " : " + message + System.lineSeparator();
            Files.write(path, logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Double> getCargoWeightPerDriver() {
        return tripHistory.stream()
                .filter(Trip::isCompleted)
                .collect(Collectors.groupingBy(
                        t -> t.getDriver().getName(),
                        Collectors.summingDouble(t -> t.getRequest().getWeight())));
    }

    public Map<String, Double> getCargoWeightPerDestination() {
        return tripHistory.stream()
                .filter(Trip::isCompleted)
                .collect(Collectors.groupingBy(
                        t -> t.getRequest().getDestination(),
                        Collectors.summingDouble(t -> t.getRequest().getWeight())));
    }

    public List<Driver> getTopEarners() {
        return drivers.stream()
                .sorted(Comparator.comparingDouble(Driver::getEarnings).reversed())
                .collect(Collectors.toList());
    }
}
