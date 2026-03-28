package Validator;

public class emailValidator {
    public static void validateInput(String text) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Error: Text input cannot be empty or null.");
        }
    }
}
