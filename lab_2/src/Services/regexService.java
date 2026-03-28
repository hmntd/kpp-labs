package Services;

import java.util.regex.Pattern;

public class regexService {
    // Regex Breakdown:
    // ^ -> Start of the string (word)
    // [A-Z] -> Exactly one uppercase English letter
    // [a-zA-Z]{1,8} -> Between 1 and 8 English letters (upper or lower)
    // tion -> The exact literal string "tion"
    // [.,!:;] -> Exactly one punctuation mark from the allowed list
    // $ -> End of the string (word)
    private static final String WORD_PATTERN = "^[A-Z][a-zA-Z]{1,8}tion[.,!:;]$";

    public boolean isWordValid(String word) {
        return Pattern.matches(WORD_PATTERN, word);
    }
}
