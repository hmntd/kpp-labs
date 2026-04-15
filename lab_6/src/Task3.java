import java.util.Scanner;

import Models.Customer;
import Services.CafeFileService;
import Services.CafeService;
import Validators.CafeValidator;

public class Task3 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "storage/cafe_state.csv";
    private static CafeFileService fileService;
    private static CafeService cafeService;

    public static void main(String[] args) {
        fileService = new CafeFileService();
        cafeService = new CafeService(fileService, FILE_PATH);

        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        handleNewCustomer(false);
                        break;
                    case "2":
                        handleNewCustomer(true);
                        break;
                    case "3":
                        handleCustomerLeaving();
                        break;
                    case "4":
                        cafeService.displayStatus();
                        break;
                    case "0":
                        running = false;
                        System.out.println("\nSaving cafe state...");
                        fileService.saveState(FILE_PATH,
                                cafeService.getSeatedCustomers(),
                                cafeService.getReservedQueue(),
                                cafeService.getRegularQueue());
                        System.out.println("Cafe is now closed. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== CAFE QUEUE MANAGER ===");
        System.out.println("1. New Walk-in Customer Arrives");
        System.out.println("2. New Reserved Customer Arrives");
        System.out.println("3. Customer Leaves (Free a table)");
        System.out.println("4. View Cafe Status");
        System.out.println("0. Save & Close Cafe");
        System.out.print("Select action: ");
    }

    private static void handleNewCustomer(boolean hasReservation) throws Exception {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        CafeValidator.validateName(name);

        cafeService.addCustomer(new Customer(name, hasReservation));
    }

    private static void handleCustomerLeaving() throws Exception {
        System.out.print("Enter the name of the seated customer who is leaving: ");
        String name = scanner.nextLine();
        CafeValidator.validateName(name);

        cafeService.freeTable(name);
    }
}
