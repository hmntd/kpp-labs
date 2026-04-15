package Validators;

import java.io.File;

public class FileValidator {
    public static void validatePath(String path) throws Exception {
        if (path == null || path.trim().isEmpty()) {
            throw new Exception("Помилка: Шлях до файлу не може бути порожнім.");
        }

        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("Помилка: Файл не знайдено за шляхом: " + path);
        }

        if (!file.canRead()) {
            throw new Exception("Помилка: Немає прав для читання файлу: " + path);
        }
    }
}
