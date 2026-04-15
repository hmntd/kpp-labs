import java.util.Scanner;

import Models.CopyReport;
import Services.DirectoryCopyService;
import Validators.DirectoryValidator;

public class Task4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Копіювання файлів з каталогу ===");

            System.out.print("Введіть шлях до КАТАЛОГУ: ");
            String sourcePath = scanner.nextLine();

            System.out.print("Введіть шлях до Каталогу для збереження файлів: ");
            String destPath = scanner.nextLine();

            DirectoryValidator.validateSource(sourcePath);
            DirectoryValidator.validateDestination(destPath, sourcePath);

            System.out.println("\nПочаток копіювання файлів...");
            DirectoryCopyService copyService = new DirectoryCopyService();
            CopyReport report = copyService.copyFiles(sourcePath, destPath);

            printReport(report);

        } catch (Exception e) {
            System.err.println("\nПомилка операції: " + e.getMessage());
        }
    }

    private static void printReport(CopyReport report) {
        System.out.println("-------------------------------------------------");
        System.out.println("Завершено копіювання файлів.");
        System.out.println("Успішно скопійовано: " + report.getSuccessCount() + " файлів.");
        System.out.println("Помилка копіювання: " + report.getFailedCount() + " файлів.");
        System.out.println("-------------------------------------------------");

        if (report.getSuccessCount() > 0) {
            System.out.println("\nУспішно скопійовані файли: ");
            for (String file : report.getSuccessfulCopies()) {
                System.out.println("[OK] " + file);
            }
        }

        if (report.getFailedCount() > 0) {
            System.out.println("\nПомилки копіювання: ");
            for (String file : report.getFailedCopies()) {
                System.err.println("[Помилка] " + file);
            }
        }
    }
}
