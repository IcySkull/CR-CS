import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BitLetter {
    
    static public String wordToBits(String word) {
        List<String> bytes = new ArrayList<>();
    }

    public int charToBits(char character) {

    }

    public List<String> getBytesFromWord(String word) {
        return word.chars().map(c -> Integer.toBinaryString(c)).collect(Collectors.toList());
    }

    protected String charToBbyte(char character) {
        return Integer.to
    }

}
