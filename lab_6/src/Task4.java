import java.util.Scanner;

import Models.Television;
import Services.TelevisionFileService;
import Services.TelevisionService;
import Validators.TelevisionValidator;

public class Task4 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "storage/televisions.csv";

    public static void main(String[] args) {
        TelevisionFileService fileService = new TelevisionFileService();
        TelevisionService tvService = new TelevisionService(fileService.loadData(FILE_PATH));

        if (tvService.getAllTvs().isEmpty()) {
            System.out.println("Initializing sample data...");
            tvService.addTelevision(new Television("OLED55", 2023, 1200.0, 55.0, "LG"));
            tvService.addTelevision(new Television("QLED40", 2022, 500.0, 40.0, "Samsung"));
            tvService.addTelevision(new Television("Bravia", 2024, 600.0, 40.0, "Sony"));
            tvService.addTelevision(new Television("BraviaPro", 2024, 600.0, 40.0, "Sony"));
            tvService.addTelevision(new Television("CheapTV", 2026, 150.0, 24.0, "TCL"));
        }

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            System.out.println();

            try {
                switch (choice) {
                    case "1":
                        tvService.printAll();
                        break;
                    case "2":
                        System.out.print("Enter diagonal: ");
                        tvService.printByDiagonal(Double.parseDouble(scanner.nextLine()));
                        break;
                    case "3":
                        System.out.print("Enter manufacturer: ");
                        tvService.printByManufacturer(scanner.nextLine());
                        break;
                    case "4":
                        System.out.print("Enter minimum price: ");
                        tvService.printCurrentYearSmallScreenMinPrice(Double.parseDouble(scanner.nextLine()));
                        break;
                    case "5":
                        System.out.print("Enter price threshold: ");
                        tvService.printMoreExpensiveThan(Double.parseDouble(scanner.nextLine()));
                        break;
                    case "6":
                        tvService.printSortedByPriceAsc();
                        break;
                    case "7":
                        tvService.printSortedByDiagonalDesc();
                        break;
                    case "8":
                        tvService.printGroupedByManufacturer();
                        break;
                    case "9":
                        tvService.printTop5Expensive();
                        break;
                    case "10":
                        tvService.printTop3SmallestDiagonal();
                        break;
                    case "11":
                        tvService.printLastMostExpensive40Inch();
                        break;
                    case "12":
                        addNewTvUI(tvService);
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saving database...");
                        fileService.saveData(FILE_PATH, tvService.getAllTvs());
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== TELEVISION STORE SYSTEM ===");
        System.out.println("1. Show all TVs");
        System.out.println("2. Show TVs by diagonal");
        System.out.println("3. Show TVs by manufacturer");
        System.out.println("4. Show current year TVs (<= 30\", >= your price)");
        System.out.println("5. Show TVs more expensive than...");
        System.out.println("6. Show all TVs sorted by price (Ascending)");
        System.out.println("7. Show all TVs sorted by diagonal (Descending)");
        System.out.println("8. Show grouped by country/manufacturer");
        System.out.println("9. Show Top 5 most expensive TVs");
        System.out.println("10. Show 3 TVs with the smallest diagonal");
        System.out.println("11. Show the LAST most expensive 40\" TV");
        System.out.println("12. [ADMIN] Add a new TV to database");
        System.out.println("0. Save & Exit");
        System.out.print("Select action: ");
    }

    private static void addNewTvUI(TelevisionService tvService) throws Exception {
        System.out.print("Model Name: ");
        String model = scanner.nextLine();
        TelevisionValidator.validateString(model, "Model");

        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        TelevisionValidator.validatePositiveNumber(price, "Price");

        System.out.print("Diagonal: ");
        double diag = Double.parseDouble(scanner.nextLine());
        TelevisionValidator.validatePositiveNumber(diag, "Diagonal");

        System.out.print("Manufacturer: ");
        String mfg = scanner.nextLine();
        TelevisionValidator.validateString(mfg, "Manufacturer");

        tvService.addTelevision(new Television(model, year, price, diag, mfg));
        System.out.println("TV added successfully!");
    }
}
