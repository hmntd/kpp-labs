package Services;

public class passwordSecurityService {
    public boolean isPasswordSecure(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!*_].*");

        boolean hasOnlyAllowedChars = password.matches("^[a-zA-Z0-9!*_]+$");

        return hasLowercase && hasUppercase && hasDigit && hasSpecialChar && hasOnlyAllowedChars;
    }

    public void printSecurityReport(String password) {
        System.out.println("\n--- Password Security Report ---");
        System.out.println("Length >= 8: " + (password.length() >= 8 ? "PASS" : "FAIL"));
        System.out.println("Contains lowercase (a-z): " + (password.matches(".*[a-z].*") ? "PASS" : "FAIL"));
        System.out.println("Contains uppercase (A-Z): " + (password.matches(".*[A-Z].*") ? "PASS" : "FAIL"));
        System.out.println("Contains digit (0-9): " + (password.matches(".*[0-9].*") ? "PASS" : "FAIL"));
        System.out.println("Contains symbol (!*_): " + (password.matches(".*[!*_].*") ? "PASS" : "FAIL"));
        System.out.println("Contains ONLY allowed chars: " + (password.matches("^[a-zA-Z0-9!*_]+$") ? "PASS" : "FAIL"));
    }
}
