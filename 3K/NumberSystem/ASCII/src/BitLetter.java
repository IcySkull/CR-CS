import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BitLetter {
    public static void main(String[] args) {
        String bitMax = "1111111111111111111111111111111";
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE-1));
        System.out.println(Integer.toBinaryString(~1));
        System.out.println(Integer.MAX_VALUE-1);
    }

    static public List<String> getBytesFromWord(String word) {
        return word.chars().mapToObj(c -> Integer.toBinaryString(c)).collect(Collectors.toList());
    }
}
