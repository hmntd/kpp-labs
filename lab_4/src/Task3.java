import Models.LibraryCatalog;
import Interfaces.ILibraryItem;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        System.out.println("--- TASK 3: LIBRARY CATALOG ---");

        LibraryCatalog catalog = new LibraryCatalog();

        catalog.initializeTestData();

        System.out.println("\nГенеруємо 2 випадкових об'єкти...");
        catalog.addRandomItem();
        catalog.addRandomItem();

        catalog.printCatalog();

        String searchTitle = "Відьмак";
        System.out.println("\n--- РЕЗУЛЬТАТИ ПОШУКУ ЗА НАЗВОЮ: '" + searchTitle + "' ---");
        List<ILibraryItem> titleResults = catalog.searchByTitle(searchTitle);
        for (ILibraryItem item : titleResults) {
            item.printDetails();
        }

        String searchAuthor = "Адамс";
        System.out.println("\n--- РЕЗУЛЬТАТИ ПОШУКУ ЗА АВТОРОМ: '" + searchAuthor + "' ---");
        List<ILibraryItem> authorResults = catalog.searchByAuthor(searchAuthor);
        for (ILibraryItem item : authorResults) {
            item.printDetails();
        }

        String titleToRemove = "Метро 2033";
        System.out.println("\nВидалення об'єкта з назвою '" + titleToRemove + "'...");
        boolean removed = catalog.removeItemByTitle(titleToRemove);
        if (removed) {
            System.out.println("Об'єкт успішно видалено.");
        } else {
            System.out.println("Об'єкт не знайдено.");
        }

        catalog.printCatalog();
    }
}