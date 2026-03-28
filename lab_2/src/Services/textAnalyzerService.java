package Services;

import java.util.regex.Pattern;

public class textAnalyzerService {
    public int countWords(String text) {
        if (text.trim().isEmpty())
            return 0;
        return text.trim().split("\\s+").length;
    }

    public int countSentences(String text) {
        if (text.trim().isEmpty())
            return 0;
        String[] sentences = text.split("[.!?]+");
        int count = 0;
        for (String s : sentences) {
            if (!s.trim().isEmpty())
                count++;
        }
        return count;
    }

    public String duplicateLongestLine(String text) {
        String[] lines = text.split("\n");
        String longest = "";

        for (String line : lines) {
            if (line.length() > longest.length()) {
                longest = line;
            }
        }

        if (longest.isEmpty())
            return text;

        return text.replaceFirst(Pattern.quote(longest), longest + "\n" + longest);
    }

    public String censorText(String text, String[] forbiddenWords) {
        String censored = text;

        for (String word : forbiddenWords) {
            StringBuilder starsBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                starsBuilder.append("*");
            }
            String stars = starsBuilder.toString();

            String regex = "(?i)\\b" + Pattern.quote(word) + "\\b";
            censored = censored.replaceAll(regex, stars);
        }

        return censored;
    }
}
