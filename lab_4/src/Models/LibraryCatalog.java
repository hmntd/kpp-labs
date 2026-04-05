package Models;

import Interfaces.ILibraryItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LibraryCatalog {
    private List<ILibraryItem> items;

    public LibraryCatalog() {
        this.items = new ArrayList<>();
    }

    public void initializeTestData() {
        System.out.println("Ініціалізація тестових даних...");

        addItem(new Book("Дж.Р.Р. Толкін", "Володар Перснів: Братство Персня", "Фентезі", 423));
        addItem(new Book("Анджей Сапковський", "Відьмак: Останнє бажання", "Фентезі", 288));
        addItem(new Book("Дмитро Глуховський", "Метро 2033", "Постапокаліпсис", 500));

        addItem(new Newspaper("The Daily Bugle", LocalDate.of(2023, 10, 5),
                Arrays.asList("Людина-павук: Герой чи Загроза?!", "Мер Озборн відкриває нову лікарню")));
        addItem(new Newspaper("Вісник Гоґвортсу", LocalDate.of(1998, 5, 3),
                Arrays.asList("Той-Кого-Не-Можна-Називати повернувся!", "Матч з квідичу скасовано")));

        List<Book> sciFiCollection = Arrays.asList(
                new Book("Дуглас Адамс", "Автостопом по галактиці", "Комедійна фантастика", 224),
                new Book("Френк Герберт", "Дюна", "Наукова фантастика", 412));
        addItem(new Almanac("Альманах Спортивної Статистики Грея 1950-2000", sciFiCollection));
    }

    public void addItem(ILibraryItem item) {
        items.add(item);
    }

    public void addRandomItem() {
        Random rnd = new Random();
        int type = rnd.nextInt(3); // 0, 1 або 2

        switch (type) {
            case 0:
                addItem(new Book("Невідомий Автор " + rnd.nextInt(100),
                        "Таємнича Книга №" + rnd.nextInt(1000),
                        "Детектив", rnd.nextInt(300) + 100));
                System.out.println("Додано випадкову книгу.");
                break;
            case 1:
                addItem(new Newspaper("Daily Random #" + rnd.nextInt(100),
                        LocalDate.now().minusDays(rnd.nextInt(1000)),
                        Arrays.asList("Шок-контент!", "Погода на завтра")));
                System.out.println("Додано випадкову газету.");
                break;
            case 2:
                List<Book> randomWorks = Arrays.asList(
                        new Book("Автор А", "Коротка історія", "Есе", 50),
                        new Book("Автор Б", "Довга історія", "Роман", 400));
                addItem(new Almanac("Випадковий Альманах Збірок " + rnd.nextInt(100), randomWorks));
                System.out.println("Додано випадковий альманах.");
                break;
        }
    }

    public boolean removeItemByTitle(String title) {
        return items.removeIf(item -> item.getTitle().equalsIgnoreCase(title));
    }

    public void printCatalog() {
        System.out.println("\n--- КАТАЛОГ БІБЛІОТЕКИ ---");
        if (items.isEmpty()) {
            System.out.println("Каталог порожній.");
            return;
        }
        for (ILibraryItem item : items) {
            item.printDetails();
            System.out.println("--------------------------");
        }
    }

    public List<ILibraryItem> searchByTitle(String title) {
        List<ILibraryItem> results = new ArrayList<>();
        for (ILibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    public List<ILibraryItem> searchByAuthor(String author) {
        List<ILibraryItem> results = new ArrayList<>();
        for (ILibraryItem item : items) {
            if (item.hasAuthor(author)) {
                results.add(item);
            }
        }
        return results;
    }
}