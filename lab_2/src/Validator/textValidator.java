package Validator;

public class textValidator {
    public static void validate(String text) throws Exception {
        if (text == null || text.trim().isEmpty()) {
            throw new Exception("Error: Text cannot be empty.");
        }
    }
}
