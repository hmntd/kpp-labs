import java.util.List;
import java.util.Scanner;

import Models.MismatchRecord;
import Services.FileComparisonService;
import Validators.FileValidator;

public class Task1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Введіть шлях до першого файлу: ");
            String filePath1 = scanner.nextLine();

            System.out.print("Введіть шлях до другого файлу: ");
            String filePath2 = scanner.nextLine();

            FileValidator.validatePath(filePath1);
            FileValidator.validatePath(filePath2);

            FileComparisonService service = new FileComparisonService();
            List<MismatchRecord> mismatches = service.compareFiles(filePath1, filePath2);

            System.out.println("\n=== Результат порівняння ===");
            if (mismatches.isEmpty()) {
                System.out.println("Файли повністю збігаються.");
            } else {
                for (MismatchRecord record : mismatches) {
                    System.out.println("Розбіжність у рядку " + record.getLineNumber() + ":");
                    System.out.println("Файл 1: " + record.getLineFromFile1());
                    System.out.println("Файл 2: " + record.getLineFromFile2());
                    System.out.println("-------------------------------------------------");
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
