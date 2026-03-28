package Services;

public class stringBuilderService {
    public String extractSubstring(StringBuilder sb, int start, int end) {
        return sb.substring(start, end);
    }

    public char[] extractChars(StringBuilder sb, int start, int end) {
        char[] destination = new char[end - start];
        sb.getChars(start, end, destination, 0);
        return destination;
    }

    public void appendText(StringBuilder sb, String text) {
        sb.append(text);
    }

    public void insertText(StringBuilder sb, int index, String text) {
        sb.insert(index, text);
    }

    public void deleteText(StringBuilder sb, int start, int end) {
        sb.delete(start, end);
    }

    public void replaceText(StringBuilder sb, int start, int end, String newText) {
        sb.delete(start, end);
        sb.insert(start, newText);
    }
}
