package Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Enums.SchoolLevel;
import Enums.ShopType;
import Models.Building;
import Models.Buildings;

public class StreetFileService {
    public List<Building> loadStreet(String filePath) {
        Path path = Paths.get(filePath);
        List<Building> loadedBuildings = new ArrayList<>();

        if (!Files.exists(path)) {
            System.out.println("No saved street found. Will generate a new one.");
            return loadedBuildings;
        }

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                try {
                    String[] parts = line.split(";", 3);
                    if (parts.length != 3)
                        return;

                    String type = parts[0];
                    int address = Integer.parseInt(parts[1]);
                    String data = parts[2];

                    Building building;
                    switch (type) {
                        case "RESIDENTIAL":
                            building = new Buildings.Res(address, 0);
                            break;
                        case "SCHOOL":
                            building = new Buildings.Sch(address, SchoolLevel.GENERAL);
                            break;
                        case "HOSPITAL":
                            building = new Buildings.Hos(address, 0);
                            break;
                        case "SHOP":
                            building = new Buildings.Shp(address, ShopType.SMALL_STORE,
                                    new ArrayList<>());
                            break;
                        default:
                            return; // Skip unknown types
                    }

                    building.parseData(data);
                    loadedBuildings.add(building);

                } catch (Exception e) {
                    System.err.println("Skipped corrupted line: " + line);
                }
            });
            System.out.println("Successfully loaded " + loadedBuildings.size() + " buildings from file.");
        } catch (IOException e) {
            System.err.println("Error reading street file: " + e.getMessage());
        }

        return loadedBuildings;
    }

    public void saveStreet(String filePath, List<Building> buildings) {
        Path path = Paths.get(filePath);
        try {
            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            List<String> csvData = buildings.stream()
                    .map(Building::toCsvRow)
                    .collect(Collectors.toList());

            Files.write(path, csvData);

        } catch (IOException e) {
            System.err.println("Error saving street file: " + e.getMessage());
        }
    }
}
