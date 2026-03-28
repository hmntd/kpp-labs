package Validator;

public class wordValidator {
    public static void validateInput(String text) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Error: Input string cannot be empty or null.");
        }
    }
}
