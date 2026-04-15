package Validators;

import java.io.File;

public class DirectoryValidator {
    public static void validateSource(String path) throws Exception {
        if (path == null || path.trim().isEmpty()) {
            throw new Exception("Помилка: Шлях до каталогу не може бути порожнім.");
        }

        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new Exception("Помилка: Вказаний шлях не існує або не є каталогом: " + path);
        }
    }

    public static void validateDestination(String destPath, String sourcePath) throws Exception {
        if (destPath == null || destPath.trim().isEmpty()) {
            throw new Exception("Помилка: Шлях до каталогу не може бути порожнім.");
        }

        if (destPath.equalsIgnoreCase(sourcePath)) {
            throw new Exception("Помилка: Каталог назначення повинен бути різним від каталогу збереження.");
        }
    }
}
