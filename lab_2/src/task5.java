import Services.emailFilterService;
import Validator.emailValidator;

public class task5 {
    public static void main(String[] args) {
        emailFilterService service = new emailFilterService();

        String inputLine = "Please send the report to admin@company.com and copy spammer@mail.ru. " +
                "Do not reply to fake-news@yandex.ru! Direct all questions to support@ukr.net.";

        try {
            emailValidator.validateInput(inputLine);

            System.out.println("=== .RU Email Filter ===");
            System.out.println("Original Text:\n" + inputLine + "\n");

            String resultText = service.removeRuEmails(inputLine);

            System.out.println("Cleaned Text:\n" + resultText);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
