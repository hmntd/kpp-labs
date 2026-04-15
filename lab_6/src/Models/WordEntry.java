package Models;

import java.util.HashSet;
import java.util.Set;

public class WordEntry {
    private String word;
    private Set<String> translations;
    private int accessCount;

    public WordEntry(String word) {
        this.word = word.toLowerCase();
        this.translations = new HashSet<>();
        this.accessCount = 0;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word.toLowerCase();
    }

    public Set<String> getTranslations() {
        return translations;
    }

    public void addTranslation(String translation) {
        this.translations.add(translation.toLowerCase());
    }

    public void removeTranslation(String translation) {
        this.translations.remove(translation.toLowerCase());
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void incrementAccessCount() {
        this.accessCount++;
    }

    public void setAccessCount(int count) {
        this.accessCount = count;
    }

    @Override
    public String toString() {
        return String.format("'%s' -> %s (Searched: %d times)", word, translations, accessCount);
    }
}
