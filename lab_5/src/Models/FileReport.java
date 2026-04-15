package Models;

import java.io.File;
import java.util.Map;

public class FileReport {
    private final File file;
    private final String content;
    private final Map<String, Integer> bannedWordCounts;

    public FileReport(File file, String content, Map<String, Integer> bannedWordCounts) {
        this.file = file;
        this.content = content;
        this.bannedWordCounts = bannedWordCounts;
    }

    public File getFile() {
        return file;
    }

    public String getContent() {
        return content;
    }

    public Map<String, Integer> getBannedWordCounts() {
        return bannedWordCounts;
    }

    public boolean hasBannedWords() {
        return !bannedWordCounts.isEmpty();
    }
}
