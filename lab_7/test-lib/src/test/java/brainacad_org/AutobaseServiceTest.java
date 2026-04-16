package brainacad_org;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brainacad_org.models.Car;
import brainacad_org.models.Driver;
import brainacad_org.models.Trip;
import brainacad_org.models.TripRequest;
import brainacad_org.services.AutobaseService;
import lombok.var;

public class AutobaseServiceTest {
    private AutobaseService autobase;
    private List<Driver> drivers;
    private List<Car> cars;

    @BeforeEach
    void setUp() {
        drivers = Arrays.asList(
                new Driver("Іван", 5, 0.0, true),
                new Driver("Петро", 15, 0.0, true));

        cars = Arrays.asList(
                new Car("AA0001", 5.0, 2, true, false),
                new Car("BB0002", 20.0, 8, true, false));

        autobase = new AutobaseService(drivers, cars);
    }

    @Test
    void testAssignCarOptimizesWeight() {
        TripRequest request = new TripRequest("Київ", 3.0, "Меблі", 2, 300, 1500);
        Optional<Car> assignedCar = autobase.assignCar(request);

        assertTrue(assignedCar.isPresent());
        assertEquals("AA0001", assignedCar.get().getPlateNumber());
    }

    @Test
    void testAssignDriverChecksExperience() {
        TripRequest request = new TripRequest("Одеса", 18.0, "Паливо", 4, 100, 5000);
        Car heavyCar = cars.get(1);

        Optional<Driver> assignedDriver = autobase.assignDriver(request, heavyCar);

        assertTrue(assignedDriver.isPresent());
        assertEquals("Петро", assignedDriver.get().getName());
    }

    @Test
    void testCompleteTripFreesResourcesAndPaysDriver() {
        TripRequest request = new TripRequest("Львів", 2.0, "Пошта", 1, 500, 2000);
        Trip trip = autobase.dispatch(request);

        assertFalse(trip.getDriver().isFree());
        assertFalse(trip.getCar().isFree());

        autobase.completeTrip(trip);

        assertTrue(trip.isCompleted());
        assertTrue(trip.getDriver().isFree());
        assertTrue(trip.getCar().isFree());
        assertEquals(2000.0, trip.getDriver().getEarnings());
    }

    @Test
    void testStatisticsCollection() {
        TripRequest req1 = new TripRequest("Дніпро", 4.0, "Їжа", 1, 100, 1000);
        TripRequest req2 = new TripRequest("Дніпро", 15.0, "Сталь", 5, 200, 4000);

        Trip trip1 = autobase.dispatch(req1);
        autobase.completeTrip(trip1);

        Trip trip2 = autobase.dispatch(req2);
        autobase.completeTrip(trip2);

        var weightPerDest = autobase.getCargoWeightPerDestination();
        assertEquals(19.0, weightPerDest.get("Дніпро"));

        List<Driver> topEarners = autobase.getTopEarners();
        assertEquals("Петро", topEarners.get(0).getName());
        assertEquals(4000.0, topEarners.get(0).getEarnings());
    }
}
