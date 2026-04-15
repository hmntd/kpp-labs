package Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Models.Customer;

public class CafeFileService {
    public void loadState(String filePath, Collection<Customer> seated, Collection<Customer> reservedQ,
            Collection<Customer> regularQ) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path))
            return;

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String status = parts[0];
                    Customer customer = new Customer(parts[1], Boolean.parseBoolean(parts[2]));

                    switch (status) {
                        case "SEATED":
                            seated.add(customer);
                            break;
                        case "QUEUED_RES":
                            reservedQ.add(customer);
                            break;
                        case "QUEUED_REG":
                            regularQ.add(customer);
                            break;
                    }
                }
            });
            System.out.println("Cafe state successfully loaded from previous session.");
        } catch (IOException e) {
            System.err.println("Error loading cafe state: " + e.getMessage());
        }
    }

    public void saveState(String filePath, Collection<Customer> seated, Collection<Customer> reservedQ,
            Collection<Customer> regularQ) {
        Path path = Paths.get(filePath);

        try {
            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            List<String> allRecords = new ArrayList<>();

            allRecords.addAll(seated.stream().map(c -> c.toCsvRow("SEATED")).collect(Collectors.toList()));
            allRecords.addAll(reservedQ.stream().map(c -> c.toCsvRow("QUEUED_RES")).collect(Collectors.toList()));
            allRecords.addAll(regularQ.stream().map(c -> c.toCsvRow("QUEUED_REG")).collect(Collectors.toList()));

            Files.write(path, allRecords);

        } catch (IOException e) {
            System.err.println("Error saving cafe state: " + e.getMessage());
        }
    }
}
