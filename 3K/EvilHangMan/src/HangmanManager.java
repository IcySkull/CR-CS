public class HangmanManager {
    private int max;
    private int length;
    private Set<String> dictionary

    public HangmanManger(List<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) 
            throw new IllegalArgumentException();
        this.max = max;
        this.length = length;
        this.dictionary = new HashSet(dictionary);
    }

    public Set<String> words() {
        return this.dictionary;
    }

    public int guessesLeft() {
        return max;
    }

    public Set<Character> guesses() {

    }

    public String patter() {
    
    }

    public int record(char guess) {

    }
}