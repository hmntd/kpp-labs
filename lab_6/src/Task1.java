import java.util.List;
import java.util.Scanner;

import Models.User;
import Services.AuthenticationService;
import Services.FileStorageService;
import Validators.AuthValidator;

public class Task1 {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String FILE_PATH = "storage/users.csv";

    private static FileStorageService fileService;
    private static AuthenticationService authService;

    public static void main(String[] args) {
        fileService = new FileStorageService();
        List<User> initialUsers = fileService.loadUsers(FILE_PATH);
        authService = new AuthenticationService(initialUsers);

        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        addNewUserUI();
                        break;
                    case "2":
                        deleteUserUI();
                        break;
                    case "3":
                        checkUserExistsUI();
                        break;
                    case "4":
                        changeLoginUI();
                        break;
                    case "5":
                        changePasswordUI();
                        break;
                    case "6":
                        printAllUsersUI();
                        break;
                    case "0":
                        running = false;
                        System.out.println("\nSaving changes...");
                        fileService.saveUsers(FILE_PATH, authService.getAllUsers());
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== USER AUTHORIZATION SYSTEM ===");
        System.out.println("1. Add a new user");
        System.out.println("2. Delete an existing user");
        System.out.println("3. Check if a user exists");
        System.out.println("4. Change user's login");
        System.out.println("5. Change user's password");
        System.out.println("6. Display all users (Debug)");
        System.out.println("0. Save & Exit");
        System.out.print("Select an option: ");
    }

    private static void addNewUserUI() throws Exception {
        System.out.print("Enter new login: ");
        String login = scanner.nextLine();
        AuthValidator.validateCredentials(login, "Login");

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        AuthValidator.validateCredentials(password, "Password");

        authService.addUser(new User(login, password));
        System.out.println("Success: User '" + login + "' has been added.");
    }

    private static void deleteUserUI() throws Exception {
        System.out.print("Enter the login of the user to delete: ");
        String login = scanner.nextLine();
        AuthValidator.validateCredentials(login, "Login");

        if (authService.deleteUser(login)) {
            System.out.println("Success: User '" + login + "' has been deleted.");
        } else {
            System.out.println("Failure: User '" + login + "' does not exist.");
        }
    }

    private static void checkUserExistsUI() throws Exception {
        System.out.print("Enter login to check: ");
        String login = scanner.nextLine();
        AuthValidator.validateCredentials(login, "Login");

        if (authService.userExists(login)) {
            System.out.println("Result: User '" + login + "' exists in the system.");
        } else {
            System.out.println("Result: User '" + login + "' NOT found.");
        }
    }

    private static void changeLoginUI() throws Exception {
        System.out.print("Enter current login: ");
        String oldLogin = scanner.nextLine();
        AuthValidator.validateCredentials(oldLogin, "Current Login");

        System.out.print("Enter new login: ");
        String newLogin = scanner.nextLine();
        AuthValidator.validateCredentials(newLogin, "New Login");

        authService.changeLogin(oldLogin, newLogin);
        System.out.println("Success: Login changed from '" + oldLogin + "' to '" + newLogin + "'.");
    }

    private static void changePasswordUI() throws Exception {
        System.out.print("Enter user login: ");
        String login = scanner.nextLine();
        AuthValidator.validateCredentials(login, "Login");

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        AuthValidator.validateCredentials(newPassword, "New Password");

        authService.changePassword(login, newPassword);
        System.out.println("Success: Password for '" + login + "' has been updated.");
    }

    private static void printAllUsersUI() {
        System.out.println("\n--- Registered Users ---");
        if (authService.getAllUsers().isEmpty()) {
            System.out.println("The system has no registered users.");
        } else {
            authService.getAllUsers().forEach(System.out::println);
        }
    }
}
