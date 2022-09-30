import java.util.*;
import java.util.stream.Collectors;

public class HangmanManager {
    private int max;
    private final int length;
    private char[] collapsedGuess;
    private Set<String> dictionary;
    private Set<Character> charGuess;

    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0)
            throw new IllegalArgumentException();
        this.max = max;
        this.length = length;
        this.collapsedGuess = new char[length];
        this.dictionary = new HashSet<>(dictionary);
        this.charGuess = new HashSet<>();
        retainAllLengthWords();
    }

    /**
     * Remove all the words of the dictionary that are not of the same length that the guess
     */
    private void retainAllLengthWords() {
        dictionary = dictionary.stream().filter(str -> str.length() == this.length).collect(Collectors.toSet());
    }

    public String getGreatestPatternSet(Map<String, Set<String>> patternMap) {
        String greatestPattern = getEmptyPattern();
        for (String patternSet : patternMap.keySet()) {
            Set<String> wordSet = patternMap.get(patternSet);
            if (patternMap.get(greatestPattern) == null)
                greatestPattern = patternSet;
            if (wordSet.size() > patternMap.get(greatestPattern).size())
                greatestPattern = patternSet;
        }
        return greatestPattern;
    }

    public Map<String, Set<String>> getPatternMap(char guess) {
        return dictionary.stream().collect(
                Collectors.toMap(
                        word -> getPatternWord(word, guess),
                        word -> {
                            HashSet<String> initWordSet = new HashSet<>();
                            initWordSet.add(word);
                            return initWordSet;
                        },
                        (a, b) -> {
                            a.addAll(b);
                            return a;
                        }
                )
        );
    }

    public String getEmptyPattern() {
        return "-".repeat(length);
    }

    public String getPatternWord(String word, char character) {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == character)
                pattern.append(character);
            else
                pattern.append('-');
        }
        return pattern.toString();
    }

    public int collapseGuess(String pattern, char guess) {
        max--; // bias of bad guess
        charGuess.add(guess);
        if (pattern.equals(getEmptyPattern()))
            return 0;
        int occurrences = 0;
        for(int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == guess) {
                occurrences++;
                collapsedGuess[i] = guess;
            }
        }
        if (occurrences > 0) // correct guess
            max++;
        return occurrences;
    }

    public Set<String> words() {
        return this.dictionary;
    }

    public int guessesLeft() {
        return max;
    }

    public Set<Character> guesses() {
        return charGuess;
    }

    public String pattern() {
        StringBuilder out = new StringBuilder();
        for (char character : collapsedGuess) {
            if (character == 0)
                out.append('-');
            else
                out.append(character);
        }
        return out.toString();
    }

    public int record(char guess) {
        if (max < 1 || dictionary.isEmpty() || charGuess.contains(guess))
            throw new IllegalArgumentException();
        Map<String, Set<String>> patternMap = getPatternMap(guess);
        String pattern = getGreatestPatternSet(patternMap);
        dictionary = patternMap.get(pattern);
        return collapseGuess(pattern, guess);
    }
}