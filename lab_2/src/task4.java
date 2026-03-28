import Services.regexService;
import Validator.wordValidator;

public class task4 {
    public static void main(String[] args) {
        regexService service = new regexService();

        String inputLine = "Situation: Motivation, Action! Obligation. nation. STATION! WrongWord, Action!!";

        try {
            wordValidator.validateInput(inputLine);

            String[] words = inputLine.split("\\s+");

            System.out.println("=== Regex Pattern Matcher ===");
            System.out.println("Target: [Uppercase] + [1-8 letters] + 'tion' + [,.!:;]\n");

            for (String word : words) {
                boolean isValid = service.isWordValid(word);
                
                System.out.printf("Word: %-15s | Matches Pattern: %s%n", word, (isValid ? "YES" : "NO"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
