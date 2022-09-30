import java.util.*;

public class CharPattern {
    private List<Integer> indexes;
    private char character;

    public CharPattern(int index, char character) {
        this.character = character;
        indexes = new ArrayList<>();
        indexes.add(index);
    }

    public CharPattern(String word, char character) {
        indexes = new ArrayList<>();
        this.character = character;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == this.character)
                indexes.add(i);
        }
    }

    public void addIndex(int index){
        indexes.add(index);
    }

    public boolean containsPattern(String word) {
        for (Integer index : this.indexes) {
            if (word.charAt(index) == this.character)
                return true;
        }
        return false;
    }

    public String getPattern(int len) {
        String pattern = "";
        for (int i = 0; i < len; i++) {
            if (indexes.contains(i)) {
                pattern += character;
            }
            else
                pattern += "-";
        }
        return pattern;
    }
}
