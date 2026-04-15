import java.util.List;
import java.util.Scanner;

import Models.Employee;
import Services.CorporationService;
import Services.FileStorageService;
import Validators.InputValidator;

public class Task3 {
    private static final Scanner scanner = new Scanner(System.in);
    private static FileStorageService fileService = new FileStorageService();
    private static CorporationService corpService;
    private static String mainDbFilePath;

    public static void main(String[] args) {
        System.out.print("Введіть шлях до файлу з даними працівників: ");
        mainDbFilePath = scanner.nextLine();

        List<Employee> initialData = fileService.loadEmployees(mainDbFilePath);
        corpService = new CorporationService(initialData);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        addEmployeeUI();
                        break;
                    case "2":
                        editEmployeeUI();
                        break;
                    case "3":
                        deleteEmployeeUI();
                        break;
                    case "4":
                        searchByLastNameUI();
                        break;
                    case "5":
                        displaySortedByAgeUI();
                        break;
                    case "6":
                        displayByLetterUI();
                        break;
                    case "7":
                        manualSaveUI();
                        break;
                    case "0":
                        running = false;
                        exitProgram();
                        break;
                    default:
                        System.out.println("Неккоректний вибір. Спробуйте ще раз.");
                }
            } catch (Exception e) {
                System.err.println("Помилка дії: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Корпоративна система ===");
        System.out.println("1. Додати нового працівника");
        System.out.println("2. Редагувати інформацію про працівника");
        System.out.println("3. Видалити працівника");
        System.out.println("4. Пошук працівників за прізвищем");
        System.out.println("5. Показати працівників за віком");
        System.out.println("6. Показати працівників за першою літерою прізвища");
        System.out.println("7. Ручне збереження даних");
        System.out.println("0. Вихід");
        System.out.print("Ваш вибір: ");
    }

    private static void addEmployeeUI() throws Exception {
        System.out.print("Ім'я: ");
        String fn = scanner.nextLine();
        InputValidator.validateString(fn, "Ім'я");

        System.out.print("Прізвище: ");
        String ln = scanner.nextLine();
        InputValidator.validateString(ln, "Прізвище");

        System.out.print("Вік: ");
        int age = Integer.parseInt(scanner.nextLine());
        InputValidator.validateAge(age);

        System.out.print("Посада: ");
        String pos = scanner.nextLine();
        InputValidator.validateString(pos, "Посада");

        corpService.addEmployee(new Employee(fn, ln, age, pos));
        System.out.println("Працівника додано");
    }

    private static void editEmployeeUI() throws Exception {
        System.out.print("Введіть ID працівника для редагування: ");
        String id = scanner.nextLine();
        Employee emp = corpService.findById(id);

        if (emp == null) {
            System.out.println("Працівник не знайдено.");
            return;
        }

        System.out.println("Редагування працівника: " + emp);

        System.out.print("Нове ім'я (або натисніть Enter для залишення без змін): ");
        String newFn = scanner.nextLine();
        if (!newFn.trim().isEmpty())
            emp.setLastName(newFn);

        System.out.print("Нове прізвище (або натисніть Enter для залишення без змін): ");
        String newLn = scanner.nextLine();
        if (!newLn.trim().isEmpty())
            emp.setLastName(newLn);

        System.out.print("Нова посада (або натисніть Enter для залишення без змін)): ");
        String newPos = scanner.nextLine();
        if (!newPos.trim().isEmpty())
            emp.setPosition(newPos);

        corpService.markAsModified();
        System.out.println("Працівника оновлено.");
    }

    private static void deleteEmployeeUI() {
        System.out.print("Введіть ID працівника для видалення: ");
        String id = scanner.nextLine();
        if (corpService.deleteEmployee(id)) {
            System.out.println("Працівника видалено.");
        } else {
            System.out.println("Працівника не знайдено.");
        }
    }

    private static void searchByLastNameUI() {
        System.out.print("Введіть прізвище працівника для пошуку: ");
        String ln = scanner.nextLine();
        List<Employee> results = corpService.searchByLastName(ln);

        if (results.isEmpty()) {
            System.out.println("Працівників не знайдено.");
            return;
        }

        results.forEach(System.out::println);

        System.out.print("\nЧи хочете ви зберегти результат в файлe? (так/ні): ");
        if (scanner.nextLine().equalsIgnoreCase("так")) {
            System.out.print("Введіть назву для файлу здебережених результатів: ");
            fileService.saveReport(scanner.nextLine(), results);
        }
    }

    private static void displaySortedByAgeUI() {
        System.out.println("\n--- Працівники за віком ---");
        corpService.getSortedByAge().forEach(System.out::println);
    }

    private static void displayByLetterUI() {
        System.out.print("Введіть першу літеру прізвища працівника для пошуку: ");
        String letter = scanner.nextLine();
        List<Employee> results = corpService.filterByLastNameLetter(letter);

        if (results.isEmpty())
            System.out.println("Нема співпадінь.");
        else
            results.forEach(System.out::println);
    }

    private static void manualSaveUI() {
        fileService.saveEmployees(mainDbFilePath, corpService.getAllEmployees());
        corpService.resetModifiedFlag();
    }

    private static void exitProgram() {
        if (corpService.isModified()) {
            System.out.println("\nЗбереження змін...");
            fileService.saveEmployees(mainDbFilePath, corpService.getAllEmployees());
        } else {
            System.out.println("\nЗмін не було, вихід без збереження.");
        }
        System.out.println("До побачення!");
    }
}
