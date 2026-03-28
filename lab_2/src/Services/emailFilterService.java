package Services;

public class emailFilterService {
    public String removeRuEmails(String text) {
        // Regex Breakdown:
        // \b -> Word boundary (start of the email)
        // [A-Za-z0-9._%+-]+ -> Email username (letters, numbers, and allowed symbols)
        // @ -> The 'at' symbol
        // [A-Za-z0-9.-]+ -> Domain name before the top-level domain
        // \.ru -> The exact literal string ".ru" (escaped with backslash)
        // \b -> Word boundary (end of the email)
        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.ru\\b";

        String cleanedText = text.replaceAll(regex, "");

        cleanedText = cleanedText.replaceAll("\\s{2,}", " ").trim();

        return cleanedText;
    }
}
