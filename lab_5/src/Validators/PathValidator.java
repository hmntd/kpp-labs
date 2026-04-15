package Validators;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathValidator {
    public static void validateBannedWordsFile(Path path) throws Exception {
        if (!Files.exists(path)) {
            throw new Exception("Файл заборонених слів не знайдено: " + path.toAbsolutePath() +
                    "\nБудь ласка, створіть цей файл у корені проекту.");
        }
    }

    public static void validateDirectory(String dirPathStr) throws Exception {
        if (dirPathStr == null || dirPathStr.trim().isEmpty()) {
            throw new Exception("Шлях до каталогу не може бути порожнім.");
        }

        File dir = new File(dirPathStr);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new Exception("Вказаний шлях не існує або не є каталогом: " + dirPathStr);
        }
    }
}
