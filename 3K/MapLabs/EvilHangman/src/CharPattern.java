import java.util.*;

public class CharPattern {
    private List<Integer> indexes;
    private char character;

    public CharPattern(char character, int index) {
        this.character = character;
        indexes = new ArrayList();
        indexes.add(index);
    }

    public CharPattern(String word, char character) {
        String out = ""
        for (int i = 0; i < word.length(); i++) {
            char charAt = word.charAt(i);
            if (charAt == character)
                out += character;
            else
                out += "";
        } 
    }

    public void addIndex(int index){
        indexes.add(index);
    }

    public String getPattern(int len) {
        String out = "";
        for (int i = 0; i < len; i++) {
            if (indexes.contains(i))
                out += character;
            else
                out += "-";
        }
        return 
    }
}
