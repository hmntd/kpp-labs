package Validators;

public class TelevisionValidator {
    public static void validateString(String input, String fieldName) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            throw new Exception("Error: " + fieldName + " cannot be empty.");
        }
        if (input.contains(",")) {
            throw new Exception("Error: " + fieldName + " cannot contain commas.");
        }
    }

    public static void validatePositiveNumber(double value, String fieldName) throws Exception {
        if (value <= 0) {
            throw new Exception("Error: " + fieldName + " must be greater than zero.");
        }
    }
}
