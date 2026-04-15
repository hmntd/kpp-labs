import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Models.FileReport;
import Services.BannedWordsService;
import Validators.PathValidator;

public class Task2 {
    public static void main(String[] args) {
        String projectRoot = System.getProperty("user.dir");
        Path bannedWordsFile = Paths.get(projectRoot, "Data/banned_words.txt");
        BannedWordsService service = new BannedWordsService();

        try (Scanner scanner = new Scanner(System.in)) {
            PathValidator.validateBannedWordsFile(bannedWordsFile);
            List<String> bannedWords = service.loadBannedWords(bannedWordsFile);

            if (bannedWords.isEmpty()) {
                System.out.println("Файл заборонених слів порожній. Роботу завершено.");
                return;
            }

            System.out.print("Введіть шлях до каталогу з текстовими файлами: ");
            String dirPathStr = scanner.nextLine();
            PathValidator.validateDirectory(dirPathStr);

            File[] files = service.getTextFiles(dirPathStr);
            if (files == null || files.length == 0) {
                System.out.println("У вказаному каталозі немає текстових файлів (.txt).");
                return;
            }

            System.out.println("\nПочинаємо сканування...");
            for (File file : files) {
                FileReport report = service.scanFile(file, bannedWords);

                if (report.hasBannedWords()) {
                    printReport(report);

                    System.out.print("Бажаєте виправити цей файл? (так/ні): ");
                    String answer = scanner.nextLine();

                    if (answer.trim().equalsIgnoreCase("так")) {
                        service.sanitizeFile(report, bannedWords);
                        System.out.println("Файл " + file.getName() + " успішно виправлено!\n");
                    } else {
                        System.out.println("Файл " + file.getName() + " залишено без змін.\n");
                    }
                }
            }
            System.out.println("Сканування каталогу завершено.");

        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
        }
    }

    private static void printReport(FileReport report) {
        System.out.println("-------------------------------------------------");
        System.out.println("Файл: " + report.getFile().getName());
        System.out.println("Знайдені заборонені слова:");
        for (Map.Entry<String, Integer> entry : report.getBannedWordCounts().entrySet()) {
            System.out.println("- '" + entry.getKey() + "': " + entry.getValue() + " шт.");
        }
    }
}