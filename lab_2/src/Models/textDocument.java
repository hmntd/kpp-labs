package Models;

public class textDocument {
    private String text;
    private String[] forbiddenWords;

    public textDocument(String text, String[] forbiddenWords) {
        this.text = text;
        this.forbiddenWords = forbiddenWords;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getForbiddenWords() {
        return forbiddenWords;
    }
}
