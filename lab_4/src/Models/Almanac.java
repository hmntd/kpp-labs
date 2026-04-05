package Models;

import Interfaces.ILibraryItem;
import java.util.List;

public class Almanac implements ILibraryItem {
    private String title;
    private List<Book> works;

    public Almanac(String title, List<Book> works) {
        this.title = title;
        this.works = works;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void printDetails() {
        System.out.printf("[Альманах] '%s' | Кількість творів: %d\n", title, works.size());
        System.out.println("   Зміст альманаху:");
        for (Book book : works) {
            System.out.println("    - " + book.getTitle() + " (Автор: " + book.getAuthor() + ")");
        }
    }

    @Override
    public boolean hasAuthor(String searchAuthor) {
        for (Book book : works) {
            if (book.hasAuthor(searchAuthor)) {
                return true;
            }
        }
        return false;
    }
}