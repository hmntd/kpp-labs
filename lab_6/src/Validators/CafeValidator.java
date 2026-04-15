package Validators;

public class CafeValidator {
    public static void validateName(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Error: Customer name cannot be empty.");
        }
        if (name.contains(",")) {
            throw new Exception("Error: Name cannot contain commas (breaks CSV format).");
        }
    }
}
