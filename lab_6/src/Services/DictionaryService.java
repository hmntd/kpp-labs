package Services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Models.WordEntry;

public class DictionaryService {
    private final Map<String, WordEntry> dictionary = new HashMap<>();

    public DictionaryService() {
        seedInitialData();
    }

    private void seedInitialData() {
        addWord("hello", "hola");
        addWord("world", "mundo");
        addWord("apple", "manzana");
        addWord("water", "agua");
        addWord("book", "libro");
        addWord("computer", "computadora", "ordenador");
        addWord("dog", "perro");
        addWord("cat", "gato");
        addWord("house", "casa");
        addWord("car", "coche", "auto");
        addWord("sun", "sol");
        addWord("moon", "luna");

        dictionary.get("hello").setAccessCount(50);
        dictionary.get("world").setAccessCount(45);
        dictionary.get("computer").setAccessCount(5);
        dictionary.get("apple").setAccessCount(2);
    }

    public void addWord(String word, String... translations) {
        String key = word.toLowerCase();
        dictionary.putIfAbsent(key, new WordEntry(key));

        Arrays.stream(translations)
                .forEach(dictionary.get(key)::addTranslation);
    }

    public void deleteWord(String word) throws Exception {
        if (dictionary.remove(word.toLowerCase()) == null) {
            throw new Exception("Error: Word not found.");
        }
    }

    public void replaceWord(String oldWord, String newWord) throws Exception {
        String oldKey = oldWord.toLowerCase();
        String newKey = newWord.toLowerCase();

        if (!dictionary.containsKey(oldKey))
            throw new Exception("Error: Old word not found.");
        if (dictionary.containsKey(newKey))
            throw new Exception("Error: New word already exists.");

        WordEntry entry = dictionary.remove(oldKey);
        entry.setWord(newKey);
        dictionary.put(newKey, entry);
    }

    public WordEntry displayWord(String word) throws Exception {
        String key = word.toLowerCase();
        if (!dictionary.containsKey(key)) {
            throw new Exception("Error: Word not found in dictionary.");
        }

        WordEntry entry = dictionary.get(key);
        entry.incrementAccessCount();
        return entry;
    }

    public void addTranslation(String word, String translation) throws Exception {
        WordEntry entry = getEntryWithoutIncrementing(word);
        entry.addTranslation(translation);
    }

    public void deleteTranslation(String word, String translation) throws Exception {
        WordEntry entry = getEntryWithoutIncrementing(word);
        if (!entry.getTranslations().contains(translation.toLowerCase())) {
            throw new Exception("Error: Translation not found.");
        }
        entry.removeTranslation(translation);
    }

    public void replaceTranslation(String word, String oldTranslation, String newTranslation) throws Exception {
        WordEntry entry = getEntryWithoutIncrementing(word);
        String oldT = oldTranslation.toLowerCase();

        if (!entry.getTranslations().contains(oldT)) {
            throw new Exception("Error: Old translation not found.");
        }
        entry.removeTranslation(oldT);
        entry.addTranslation(newTranslation);
    }

    private WordEntry getEntryWithoutIncrementing(String word) throws Exception {
        String key = word.toLowerCase();
        if (!dictionary.containsKey(key))
            throw new Exception("Error: Word not found.");
        return dictionary.get(key);
    }

    public List<WordEntry> getTop10Popular() {
        return dictionary.values().stream()
                .sorted(Comparator.comparingInt(WordEntry::getAccessCount).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<WordEntry> getTop10Unpopular() {
        return dictionary.values().stream()
                .sorted(Comparator.comparingInt(WordEntry::getAccessCount))
                .limit(10)
                .collect(Collectors.toList());
    }
}
