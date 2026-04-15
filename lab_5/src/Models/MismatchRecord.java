package Models;

public class MismatchRecord {
    private int lineNumber;
    private String lineFromFile1;
    private String lineFromFile2;

    public MismatchRecord(int lineNumber, String lineFromFile1, String lineFromFile2) {
        this.lineNumber = lineNumber;
        this.lineFromFile1 = lineFromFile1;
        this.lineFromFile2 = lineFromFile2;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLineFromFile1() {
        return lineFromFile1 != null ? lineFromFile1 : "[Кінець файлу]";
    }

    public String getLineFromFile2() {
        return lineFromFile2 != null ? lineFromFile2 : "[Кінець файлу]";
    }
}
