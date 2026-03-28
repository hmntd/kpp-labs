import Services.stringBuilderService;

public class task2 {
    public static void main(String[] args) {
        stringBuilderService service = new stringBuilderService();

        StringBuilder sb = new StringBuilder("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        System.out.println("--- Start State ---");
        System.out.println("Line: " + sb.toString());
        System.out.println("Lenght: " + sb.length());

        System.out.println("\n--- 1. Retrieve Substring ---");

        String sub = service.extractSubstring(sb, 10, 16);
        System.out.println("Using substring(10, 16): " + sub);

        char[] chars = service.extractChars(sb, 0, 4);
        System.out.println("Using getChars(0, 4): " + new String(chars));

        System.out.println("\n--- 2. Adding Substring ---");

        service.appendText(sb, " Proin ut pulvinar neque.");
        System.out.println("After append() in the end:\n" + sb.toString());

        service.insertText(sb, 9, "дійсно ");
        System.out.println("After insert(9, ...) in the middle:\n" + sb.toString());

        System.out.println("\n--- 3. Delete and Replace ---");

        service.deleteText(sb, 16, 23);
        System.out.println("After delete(16, 23):\n" + sb.toString());

        service.replaceText(sb, 0, 4, "TEST");
        System.out.println("After replace through delete() and insert():\n" + sb.toString());
    }
}
