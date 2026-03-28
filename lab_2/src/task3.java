import java.util.Scanner;

import Services.passwordSecurityService;
import Validator.passwordValidator;

public class task3 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            passwordSecurityService service = new passwordSecurityService();

            System.out.println("=== Password Strength Checker ===");
            System.out.println("Rules:");
            System.out.println("- At least 8 characters long");
            System.out.println("- Contains uppercase and lowercase letters (a-z, A-Z)");
            System.out.println("- Contains at least one digit (0-9)");
            System.out.println("- Contains at least one special symbol (!, *, _)");
            System.out.println("- No other symbols allowed\n");

            System.out.print("Enter a password to test: ");
            String inputPassword = scanner.nextLine();

            passwordValidator.validateInput(inputPassword);

            boolean isSecure = service.isPasswordSecure(inputPassword);

            if (isSecure) {
                System.out.println("\nResult: SUCCESS! The password is secure.");
            } else {
                System.out.println("\nResult: FAILED! The password does not meet the security criteria.");
                service.printSecurityReport(inputPassword);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
