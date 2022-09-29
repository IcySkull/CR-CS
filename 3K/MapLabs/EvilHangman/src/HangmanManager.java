import java.util.*;
import java.util.stream.Collectors;

public class HangmanManager {
    private int max;
    private char[] collapsedGuess;
    private Set<String> dictionary;
    private Set<Character> charGuess;

    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) 
            throw new IllegalArgumentException();
        this.max = max;
        this.collapsedGuess = new char[length];
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

    private Set<String> getGreatestWordSet(char character) {
        Set<String> greatestSet = this.dictionary.stream().filter(str -> str.indexOf(character) == -1).collect(Collectors.toSet());
        char[] collapsedCopy = this.collapsedGuess.clone();
        // this for loop is equivalent to iterating through the size of the guess and returning the greatest set with the occurrence of the character
        // note that the first greatest set is returned and this is equal to the set of words that have the argument character at the index of the loop variable: i
        for (int i = 0; i < collapsedCopy.length; i++) {
            Set<String> currSet = getWordsWithCharAt(character, i);
            if (currSet.size() > greatestSet.size()) {
                greatestSet = currSet;
                collapsedCopy = this.collapsedGuess;
                collapsedCopy[i] = character;
            }
            else if (currSet.size() < greatestSet.size())
                continue;
            else { //curr set must be equal in size to the greatest set
                if (greatestSet.size() != 0 && greatestSet.iterator().next().indexOf(character) > 0)
                    collapsedCopy[i] = character;
            }
        }

        return greatestSet;
    }

    protected int getCharOcurrenceInWord(char character, String word) {
        int occur = 0;
        for(int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == character)
                occur++;
        }
        return occur;
    }

    /**
     * From the given character, return all the sets of words that contain the given character.
     * Each set is different in the index where the character is placed.
     * @param character
     * @return
     */
    protected List<Set<String>> getWordSets(char character) {
        List<Set<String>> collectedSets = new ArrayList();
        // this for loop is equivalent to iterating through the size of the guess and returning the greatest set with the occurrence of the character
        // note that the first greatest set is returned and this is equal to the set of words that have the argument character at the index of the loop variable: i
        for (int i = 0; i < collapsedGuess.length; i++) {
            collectedSets.add(getWordsWithCharAt(character, i));
        }
        return collectedSets;
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