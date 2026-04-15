package Validators;

public class InputValidator {
    public static void validateString(String input, String fieldName) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            throw new Exception("Помилка: " + fieldName + " не може бути порожнім.");
        }
    }

    public static void validateAge(int age) throws Exception {
        if (age < 18 || age > 100) {
            throw new Exception("Помилка: Вік повинен бути від 18 та 100.");
        }
    }
}
