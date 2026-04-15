package Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Models.FileReport;

public class BannedWordsService {
    public List<String> loadBannedWords(Path filePath) throws IOException {
        List<String> words = Files.readAllLines(filePath);
        words.removeIf(String::isBlank);
        return words;
    }

    public File[] getTextFiles(String dirPath) {
        File dir = new File(dirPath);
        return dir.listFiles((d, name) -> name.endsWith(".txt"));
    }

    public FileReport scanFile(File file, List<String> bannedWords) throws IOException {
        String content = Files.readString(file.toPath());
        Map<String, Integer> wordCounts = new HashMap<>();

        for (String word : bannedWords) {
            Pattern pattern = Pattern.compile("(?i)\\b" + Pattern.quote(word) + "\\b");
            Matcher matcher = pattern.matcher(content);

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            if (count > 0) {
                wordCounts.put(word, count);
            }
        }

        return new FileReport(file, content, wordCounts);
    }

    public void sanitizeFile(FileReport report, List<String> bannedWords) throws IOException {
        String newContent = report.getContent();

        for (String word : bannedWords) {
            if (report.getBannedWordCounts().containsKey(word)) {
                String replacement = "*".repeat(word.length());
                newContent = newContent.replaceAll("(?i)\\b" + Pattern.quote(word) + "\\b", replacement);
            }
        }

        Files.writeString(report.getFile().toPath(), newContent);
    }
}
