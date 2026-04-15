import java.util.Scanner;

import Services.DictionaryService;
import Validators.DictionaryValidator;

public class Task2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DictionaryService service = new DictionaryService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        displayWordUI();
                        break;
                    case "2":
                        addWordUI();
                        break;
                    case "3":
                        replaceWordUI();
                        break;
                    case "4":
                        deleteWordUI();
                        break;
                    case "5":
                        addTranslationUI();
                        break;
                    case "6":
                        replaceTranslationUI();
                        break;
                    case "7":
                        deleteTranslationUI();
                        break;
                    case "8":
                        displayTop10PopularUI();
                        break;
                    case "9":
                        displayTop10UnpopularUI();
                        break;
                    case "0":
                        running = false;
                        System.out.println("Exiting dictionary...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== ENGLISH-SPANISH DICTIONARY ===");
        System.out.println("1. Search & Display a word");
        System.out.println("2. Add a new word");
        System.out.println("3. Replace a word");
        System.out.println("4. Delete a word");
        System.out.println("5. Add a translation");
        System.out.println("6. Replace a translation");
        System.out.println("7. Delete a translation");
        System.out.println("8. Show Top-10 POPULAR words");
        System.out.println("9. Show Top-10 UNPOPULAR words");
        System.out.println("0. Exit");
        System.out.print("Select: ");
    }

    private static void displayWordUI() throws Exception {
        System.out.print("Enter English word: ");
        String word = scanner.nextLine();
        DictionaryValidator.validateInput(word, "Word");

        System.out.println("\nResult: " + service.displayWord(word));
    }

    private static void addWordUI() throws Exception {
        System.out.print("Enter new English word: ");
        String word = scanner.nextLine();
        DictionaryValidator.validateInput(word, "Word");

        System.out.print("Enter Spanish translation: ");
        String translation = scanner.nextLine();
        DictionaryValidator.validateInput(translation, "Translation");

        service.addWord(word, translation);
        System.out.println("Word added successfully!");
    }

    private static void replaceWordUI() throws Exception {
        System.out.print("Enter OLD English word: ");
        String oldWord = scanner.nextLine();
        System.out.print("Enter NEW English word: ");
        String newWord = scanner.nextLine();

        service.replaceWord(oldWord, newWord);
        System.out.println("Word replaced successfully!");
    }

    private static void deleteWordUI() throws Exception {
        System.out.print("Enter English word to delete: ");
        String word = scanner.nextLine();
        service.deleteWord(word);
        System.out.println("Word deleted successfully!");
    }

    private static void addTranslationUI() throws Exception {
        System.out.print("Enter the English word: ");
        String word = scanner.nextLine();
        System.out.print("Enter NEW Spanish translation to add: ");
        String trans = scanner.nextLine();

        service.addTranslation(word, trans);
        System.out.println("Translation added!");
    }

    private static void replaceTranslationUI() throws Exception {
        System.out.print("Enter the English word: ");
        String word = scanner.nextLine();
        System.out.print("Enter OLD Spanish translation: ");
        String oldT = scanner.nextLine();
        System.out.print("Enter NEW Spanish translation: ");
        String newT = scanner.nextLine();

        service.replaceTranslation(word, oldT, newT);
        System.out.println("Translation replaced!");
    }

    private static void deleteTranslationUI() throws Exception {
        System.out.print("Enter the English word: ");
        String word = scanner.nextLine();
        System.out.print("Enter Spanish translation to remove: ");
        String trans = scanner.nextLine();

        service.deleteTranslation(word, trans);
        System.out.println("Translation removed!");
    }

    private static void displayTop10PopularUI() {
        System.out.println("\n--- TOP 10 MOST POPULAR WORDS ---");
        service.getTop10Popular().forEach(System.out::println);
    }

    private static void displayTop10UnpopularUI() {
        System.out.println("\n--- TOP 10 LEAST POPULAR WORDS ---");
        service.getTop10Unpopular().forEach(System.out::println);
    }
}
