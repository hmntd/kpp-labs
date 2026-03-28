package Validator;

public class passwordValidator {
    public static void validateInput(String password) throws Exception {
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Error: Password input cannot be empty or null.");
        }
    }
}
