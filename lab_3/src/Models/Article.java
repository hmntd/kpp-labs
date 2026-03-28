package Models;

import java.util.Objects;
import java.util.UUID;

public class Article {
    public UUID id = UUID.randomUUID();
    private Person author;
    private String title;
    private double rating;
    private int pages;

    public Article(Person author, String title, double rating, int pages) {
        this.author = author;
        this.title = title;
        this.rating = rating;
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("Article: '%s' | Author: %s | Rating: %.1f | Pages: %d",
                title, author.toString(), rating, pages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Article article = (Article) o;
        return id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
