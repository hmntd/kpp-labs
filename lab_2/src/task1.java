import Models.textDocument;
import Services.textAnalyzerService;
import Validator.textValidator;

public class task1 {
    public static void main(String[] args) {
        String rawText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis id elit tempus leo gravida pulvinar. Vivamus eu justo volutpat, efficitur metus ut, venenatis est. Nam lobortis pharetra nisl tristique vestibulum. Nulla lacinia semper dui, et iaculis elit ultricies vitae. Ut nec gravida lacus, a vulputate nisl. Suspendisse odio lorem, pretium non commodo aliquam, mollis quis leo. Vestibulum malesuada eleifend odio, in laoreet orci venenatis sed. Sed auctor nisi tortor, efficitur condimentum velit aliquet eu. Phasellus eu scelerisque erat. Phasellus fringilla fermentum gravida. Praesent faucibus, eros sed malesuada congue, felis eros ullamcorper libero, a sollicitudin tortor mauris et turpis. Proin volutpat orci nunc, id dapibus purus vulputate sed. Nulla velit neque, porta eget malesuada nec, ornare quis enim. Duis consequat venenatis pretium. Nullam hendrerit ante lorem.\n"
                +
                "\n" +
                "In hac habitasse platea dictumst. Donec at felis eu urna pretium venenatis. Etiam pellentesque gravida nunc. Nullam convallis enim quam, eget elementum lectus feugiat fermentum. Vivamus varius tortor nec erat condimentum fringilla et vel diam. Nulla facilisi. Donec vehicula non ex congue tincidunt. Suspendisse ligula libero, faucibus bibendum ornare et, pellentesque in metus. Vivamus in fermentum orci. Fusce ultricies sodales mauris, vitae pulvinar neque aliquam eget.";

        String[] badWords = { "habitasse", "dictumst" };

        textDocument document = new textDocument(rawText, badWords);
        textAnalyzerService service = new textAnalyzerService();

        try {
            textValidator.validate(document.getText());

            System.out.println("--- Text Stats ---");
            int words = service.countWords(document.getText());
            int sentences = service.countSentences(document.getText());
            System.out.println("Word count: " + words);
            System.out.println("Sentence count: " + sentences);

            System.out.println("\n--- Text after duplicating longest line ---");
            String duplicatedText = service.duplicateLongestLine(document.getText());
            System.out.println(duplicatedText);

            System.out.println("\n--- Text after censoring---");
            String finalText = service.censorText(duplicatedText, document.getForbiddenWords());
            System.out.println(finalText);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
