package Services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Models.Building;
import Models.Residential;
import Models.Shop;

public class StreetService {
    private final List<Building> buildings;

    public StreetService(List<Building> initialBuildings) {
        this.buildings = initialBuildings;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
        System.out.println("Building added to address: " + building.getAddressNumber());
    }

    public void removeBuilding(int addressNumber) {
        boolean removed = buildings.removeIf(b -> b.getAddressNumber() == addressNumber);
        if (removed)
            System.out.println("Building at address " + addressNumber + " removed.");
        else
            System.out.println("Building not found.");
    }

    public void printStreet() {
        System.out.println("\n=== CURRENT STREET STATUS ===");
        if (buildings.isEmpty())
            System.out.println("The street is empty.");
        buildings.forEach(System.out::println);
        System.out.println("=============================\n");
    }

    public void findShopsInNeighborhood(int neighborhoodRange, String targetDepartment) {
        List<Residential> residentials = buildings.stream()
                .filter(b -> b instanceof Residential)
                .map(b -> (Residential) b)
                .collect(Collectors.toList());

        if (residentials.isEmpty()) {
            System.out.println("No residential buildings on this street to base the search on.");
            return;
        }

        Residential randomHouse = residentials.get(new Random().nextInt(residentials.size()));
        System.out.println("Randomly selected House at Address: " + randomHouse.getAddressNumber());
        System.out.println("Searching within +/- " + neighborhoodRange + " address numbers for a '" + targetDepartment
                + "' department...");

        List<Shop> foundShops = buildings.stream()
                .filter(b -> b instanceof Shop)
                .map(b -> (Shop) b)
                .filter(shop -> Math.abs(shop.getAddressNumber() - randomHouse.getAddressNumber()) <= neighborhoodRange)
                .filter(shop -> shop.hasDepartment(targetDepartment))
                .collect(Collectors.toList());

        if (foundShops.isEmpty()) {
            System.out.println("No shops found in this neighborhood with that department.");
        } else {
            foundShops.forEach(shop -> System.out.println("  -> Found: " + shop.toString()));
        }
    }

    public List<Building> getBuildings() {
        return buildings;
    }
}
