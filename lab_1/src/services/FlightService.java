package src.services;

public class FlightService {
    public double getConsumptionRate(double weight) {
        if (weight <= 500)
            return 1.0;
        if (weight <= 1000)
            return 4.0;
        if (weight <= 1500)
            return 7.0;

        return 9.0;
    }

    public double calculateRequiredFuel(double distance, double weight, double tankCapacity) throws Exception {
        double rate = getConsumptionRate(weight);
        double totalNeeded = distance * rate;

        if (totalNeeded > tankCapacity) {
            throw new Exception("Distance exceeds maximum range with a full tank.");
        }

        return totalNeeded;
    }
}
