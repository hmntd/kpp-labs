package Validators;

public class DictionaryValidator {
    public static void validateInput(String input, String fieldName) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            throw new Exception("Error: " + fieldName + " cannot be empty.");
        }
    }
}
