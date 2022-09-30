import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HangmanManager {
    private int max;
    private int length;
    private char[] collapsedGuess;
    private Set<String> dictionary;
    private Set<Character> charGuess;

    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) 
            throw new IllegalArgumentException();
        this.max = max;
        this.length = length;
        this.collapsedGuess = new char[length];
        this.dictionary = new HashSet<String>(dictionary);
        this.charGuess = new HashSet<Character>();
        retainAllLengthWords();
    }

    /**
     * Remove all the words of the dictionary that are not of the same length that the guess
     */
    private void retainAllLengthWords() {
        dictionary = dictionary.stream().filter(str -> str.length() == this.length).collect(Collectors.toSet());
    }

    public Set<String> getGreatestWordSet(char guess) {
        Map<CharPattern, Set<String>> patternMap = dictionary.stream().collect(
            Collectors.toMap(
                word -> new CharPattern((String)word, guess),
                new HashSet<String>()
            )
        );
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
        String out = "";
        for (char character : collapsedGuess) {
            if (character == 0)
                out += '-';
            else
                out += character;
        }
        return out;
    }

    public int record(char guess) {
        max--; // one guess made
        dictionary = getGreatestWordSet(guess);
        int occurrence = 0;
        boolean flag = false;
        for (Character character : collapsedGuess) {
            if (Character.valueOf(guess).equals(character)) {
                flag = true;
                occurrence++;
            }
        }
        if (flag) max++;
        return occurrence;
    }
}