package Models;

import Interfaces.ILibraryItem;

public class Book implements ILibraryItem {
    private String author;
    private String title;
    private String genre;
    private int pageCount;

    public Book(String author, String title, String genre, int pageCount) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void printDetails() {
        System.out.printf("[Книга] '%s' | Автор: %s | Жанр: %s | Сторінок: %d\n",
                title, author, genre, pageCount);
    }

    @Override
    public boolean hasAuthor(String searchAuthor) {
        if (this.author == null)
            return false;
        return this.author.toLowerCase().contains(searchAuthor.toLowerCase());
    }
}