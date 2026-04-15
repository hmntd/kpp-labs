package Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Models.Television;

public class TelevisionFileService {
    public List<Television> loadData(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(line -> {
                String[] p = line.split(",");
                return new Television(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]), Double.parseDouble(p[3]),
                        p[4]);
            }).collect(Collectors.toList());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveData(String filePath, List<Television> tvs) {
        Path path = Paths.get(filePath);
        try {
            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            List<String> csvData = tvs.stream()
                    .map(Television::toCsvRow)
                    .collect(Collectors.toList());
            Files.write(path, csvData);

        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}
