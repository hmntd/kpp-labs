package src.services;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
    public Map<String, Double> readJson(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new Exception("File not found: " + fileName);
        }

        Map<String, Double> data = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine())
                content.append(scanner.nextLine());

            String clean = content.toString().replaceAll("[{}\"\\s]", "");
            String[] pairs = clean.split(",");

            for (String pair : pairs) {
                String[] kv = pair.split(":");
                data.put(kv[0], Double.parseDouble(kv[1]));
            }
        }

        return data;
    }
}
