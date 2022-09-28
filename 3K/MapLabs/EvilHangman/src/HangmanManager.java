import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.css.Size;

public class HangmanManager {
    private int max;
    private Character[] collapsedGuess;
    private Set<String> dictionary;
    private Set<Character> charGuess;

    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) 
            throw new IllegalArgumentException();
        this.max = max;
        this.collapsedGuess = new Character[length];
        this.dictionary = new TreeSet<String>(dictionary);
        this.charGuess = new TreeSet<Character>();
        retainAllLengthWords();
    }

    /**
     * Remove all the words of the dictionary that are not of the same length of guess
     */
    private void retainAllLengthWords() {
        dictionary = dictionary.stream().filter(str -> str.length() == collapsedGuess.length).collect(Collectors.toSet());
    }

    /**
     * Uses a collection stream to return a set that filters all the words that do NOT have the given character at the given index
     */
    private Set<String> getWordsWithCharAt(char character, int index) {
        return dictionary.stream().filter(str -> str.charAt(index) == character).collect(Collectors.toSet());
    }

    /**
     * From all sets with the character at different indexes, returns the greatest set in size
     * and collapsedGuess is updated in respect to the greatest set found
     * @param character
     * @return
     */
    private Set<String> getGreatestWordSet(char character) {
        Set<String> greatestSet = dictionary.stream().filter(str -> str.indexOf(character) == -1).collect(Collectors.toSet());
        // the guess of the player is assumed to be small so is safe to copy the collapsed guess
        Character[] collapsedCopy = collapsedGuess.clone();
        for (int i = 0; i < collapsedGuess.length; i++) {
            // this for loop is equivalent to iterating through the size of the guess and returning the greatest set with the occurrence of the character
            // note that the first greatest set is returned and this is equal to the set of words that have the argument character at the index of the loop variable: i
            Set<String> currentSet = getWordsWithCharAt(character, i);
            if (currentSet.size() > greatestSet.size()) {
                collapsedCopy = collapsedGuess.clone(); // clear the guess
                greatestSet = currentSet;
                collapsedCopy[i] = character;
            }
            // greatestSet.size() != 0 must be checked for the greatest set being different than unity
            else if (greatestSet.size() != 0 && currentSet.size() == greatestSet.size())
                collapsedCopy[i] = character;
        }
        collapsedGuess = collapsedCopy; // notice that collapsedGuess remains the same if a greater set with the given character is NOT found
        return greatestSet;
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
        for (Character character : collapsedGuess) {
            if (character == null)
                out += '-';
            else
                out += String.valueOf(character);
        }
        return out;
    }

    public int record(char guess) {
        max--; // one guess made
        dictionary = getGreatestWordSet(guess);
        int occurrence = 0;
        for (Character character : collapsedGuess) {
            if (Character.valueOf(guess).equals(character))
                occurrence++;
        }
        return occurrence;
    }
}