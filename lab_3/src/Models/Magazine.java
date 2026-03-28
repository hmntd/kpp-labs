package Models;

import java.time.LocalDate;
import java.util.Objects;

import Enum.Frequency;

public class Magazine {
    private String title;
    private Frequency frequency;
    private LocalDate releaseDate;
    private int circulation;
    private Article[] articles;

    public Magazine() {
        this.title = "Unknown";
        this.frequency = Frequency.WEEKLY;
        this.releaseDate = LocalDate.now();
        this.circulation = 0;
        this.articles = new Article[0];
    }

    public Magazine(String title, Frequency frequency, LocalDate releaseDate, int circulation, Article[] articles) {
        this.title = title;
        this.frequency = frequency;
        this.releaseDate = releaseDate;
        this.circulation = circulation;
        this.articles = articles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    @Override
    public String toString() {
        return String.format("Magazine: %s | Frequency: %s | Circulation: %d",
                title, frequency, circulation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Magazine magazine = (Magazine) o;
        return Objects.equals(title, magazine.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
