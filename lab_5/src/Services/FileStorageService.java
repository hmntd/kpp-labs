package Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Employee;

public class FileStorageService {
    public List<Employee> loadEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Файл не знайдено. Починаємо створення нового файлу.");
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    employees.add(new Employee(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]));
                }
            }
            System.out.println("Успішно завантажено файл з " + employees.size() + " працівниками.");
        } catch (Exception e) {
            System.err.println("Помилка завантаження файлу: " + e.getMessage());
        }
        return employees;
    }

    public void saveEmployees(String filePath, List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee emp : employees) {
                writer.write(emp.toCsvRow());
                writer.newLine();
            }
            System.out.println("Дані успішно збережено в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Помилка збереження файлу: " + e.getMessage());
        }
    }

    public void saveReport(String filePath, List<Employee> reportData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("--- Корпоративний звіт---\n");
            for (Employee emp : reportData) {
                writer.write(emp.toString());
                writer.newLine();
            }
            System.out.println("Звіт успішно збережено в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Помилка збереження файлу: " + e.getMessage());
        }
    }
}
