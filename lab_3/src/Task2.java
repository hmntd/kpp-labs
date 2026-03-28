import java.time.LocalDate;

import Enum.Frequency;
import Models.Article;
import Models.Magazine;
import Models.Person;
import Services.MagazineService;

public class Task2 {
    public static void main(String[] args) {
        Person author1 = new Person("Alan", "Turing", LocalDate.of(1912, 6, 23));
        Person author2 = new Person("Ada", "Lovelace", LocalDate.of(1815, 12, 10));
        Person author3 = new Person("Grace", "Hopper", LocalDate.of(1906, 12, 9));

        Article a1 = new Article(author1, "The Enigma Machine", 4.9, 15);
        Article a2 = new Article(author2, "First Algorithm", 5.0, 22);
        Article a3 = new Article(author3, "Compilers and Bugs", 4.8, 12);
        Article a4 = new Article(author1, "AI Basics", 4.5, 8);
        Article a5 = new Article(author3, "COBOL Standards", 4.7, 22);

        Magazine techWeekly = new Magazine(
                "Tech Weekly",
                Frequency.WEEKLY,
                LocalDate.of(2026, 3, 25),
                10000,
                new Article[] { a1, a4 });

        Magazine codeMonthly = new Magazine(
                "Code Monthly",
                Frequency.MONTHLY,
                LocalDate.of(2026, 3, 1),
                50000,
                new Article[] { a2, a3, a5 });

        Magazine[] myMagazines = { techWeekly, codeMonthly };

        MagazineService service = new MagazineService();
        service.findAndPrintLongestArticles(myMagazines);
    }
}
