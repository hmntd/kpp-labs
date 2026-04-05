package Models;

import Interfaces.ILibraryItem;
import java.time.LocalDate;
import java.util.List;

public class Newspaper implements ILibraryItem {
    private String title;
    private LocalDate publicationDate;
    private List<String> headlines;

    public Newspaper(String title, LocalDate publicationDate, List<String> headlines) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.headlines = headlines;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void printDetails() {
        System.out.printf("[Газета] '%s' | Дата виходу: %s\n", title, publicationDate.toString());
        System.out.println("   Головні заголовки: " + String.join(", ", headlines));
    }

    @Override
    public boolean hasAuthor(String author) {
        // Газета має декілька авторів, тому повертаємо false
        return false;
    }
}