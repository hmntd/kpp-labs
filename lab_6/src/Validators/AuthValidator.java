package Validators;

public class AuthValidator {
    public static void validateCredentials(String input, String fieldName) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            throw new Exception("Error: " + fieldName + " cannot be empty.");
        }
        if (input.contains(" ")) {
            throw new Exception("Error: " + fieldName + " cannot contain spaces.");
        }
    }
}
