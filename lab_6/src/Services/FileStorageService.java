package Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Models.User;

public class FileStorageService {
    public List<User> loadUsers(String filePath) {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            System.out.println("File not found. Starting with an empty user list.");
            return new ArrayList<>();
        }

        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(line -> {
                String[] parts = line.split(",");
                return new User(parts[0], parts[1]);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveUsers(String filePath, List<User> users) {
        Path path = Paths.get(filePath);

        try {
            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            List<String> csvLines = users.stream()
                    .map(User::toCsvRow)
                    .collect(Collectors.toList());

            Files.write(path, csvLines);
            System.out.println("Successfully saved to: " + filePath);

        } catch (IOException e) {
            System.err.println("Error saving the file: " + e.getMessage());
        }
    }
}
