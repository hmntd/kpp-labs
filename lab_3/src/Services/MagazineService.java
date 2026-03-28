package Services;

import Models.Article;
import Models.Magazine;

public class MagazineService {
    public void findAndPrintLongestArticles(Magazine[] magazines) {
        if (magazines == null || magazines.length == 0) {
            System.out.println("No magazines provided.");
            return;
        }

        int maxPages = 0;
        for (Magazine mag : magazines) {
            for (Article art : mag.getArticles()) {
                if (art.getPages() > maxPages) {
                    maxPages = art.getPages();
                }
            }
        }

        System.out.println("\n--- ARTICLES WITH MAXIMUM PAGES (" + maxPages + " pages) ---");
        boolean found = false;

        for (Magazine mag : magazines) {
            for (Article art : mag.getArticles()) {
                if (art.getPages() == maxPages) {
                    System.out.println("Published in: " + mag.getTitle());
                    System.out.println("   " + art.toString());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No articles found.");
        }
    }
}
