package Interfaces;

public interface ILibraryItem {
    String getTitle();

    void printDetails();

    boolean hasAuthor(String author);
}