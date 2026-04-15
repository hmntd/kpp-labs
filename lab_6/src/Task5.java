import java.util.List;
import java.util.Scanner;

import Enums.SchoolLevel;
import Enums.ShopType;
import Factories.StreetFactory;
import Models.Building;
import Models.Buildings;
import Services.StreetFileService;
import Services.StreetService;

public class Task5 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "storage/street.csv";

    private static StreetService streetService;
    private static StreetFileService fileService;

    public static void main(String[] args) {
        fileService = new StreetFileService();

        List<Building> initialBuildings = fileService.loadStreet(FILE_PATH);

        if (initialBuildings.isEmpty()) {
            System.out.println("Generating test street using Static Factory...");
            initialBuildings = StreetFactory.generateTestStreet();
        }

        streetService = new StreetService(initialBuildings);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        streetService.printStreet();
                        break;
                    case "2":
                        parseAndAddBuildingUI();
                        break;
                    case "3":
                        removeBuildingUI();
                        break;
                    case "4":
                        searchNeighborhoodUI();
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saving street data to " + FILE_PATH + "...");
                        fileService.saveStreet(FILE_PATH, streetService.getBuildings());
                        System.out.println("Exiting...");
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
        System.out.println("\n=== CITY STREET MANAGER ===");
        System.out.println("1. Show all buildings on the street");
        System.out.println("2. Add a new building (Using String Parsing)");
        System.out.println("3. Remove a building by address");
        System.out.println("4. Advanced Search (Find Shop in Neighborhood)");
        System.out.println("0. Save & Exit");
        System.out.print("Select action: ");
    }

    private static void parseAndAddBuildingUI() throws Exception {
        System.out.println("Types: RESIDENTIAL, SCHOOL, HOSPITAL, SHOP");
        System.out.print("Enter building type to add: ");
        String type = scanner.nextLine().toUpperCase();

        System.out.print("Enter building address number: ");
        int address = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter data string to parse (e.g. '150', 'LYCEUM', 'SUPERMARKET,Bakery'): ");
        String data = scanner.nextLine();

        Building newBuilding;

        switch (type) {
            case "RESIDENTIAL":
                newBuilding = new Buildings.Res(address, 0);
                break;
            case "SCHOOL":
                newBuilding = new Buildings.Sch(address, SchoolLevel.GENERAL);
                break;
            case "HOSPITAL":
                newBuilding = new Buildings.Hos(address, 0);
                break;
            case "SHOP":
                newBuilding = new Buildings.Shp(address, ShopType.SMALL_STORE,
                        new java.util.ArrayList<>());
                break;
            default:
                System.out.println("Unsupported type for CLI creation.");
                return;
        }

        newBuilding.parseData(data);
        streetService.addBuilding(newBuilding);
    }

    private static void removeBuildingUI() {
        System.out.print("Enter address number to remove: ");
        int address = Integer.parseInt(scanner.nextLine());
        streetService.removeBuilding(address);
    }

    private static void searchNeighborhoodUI() {
        System.out.print("Enter neighborhood range (e.g., 5 address numbers away): ");
        int range = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter department name to look for (e.g., 'Grocery' or 'Bakery'): ");
        String dept = scanner.nextLine();

        streetService.findShopsInNeighborhood(range, dept);
    }
}
