import java.util.ArrayList;
import java.util.List;

public class Anagrams {
    List<String> dictionary;

    List<String> prunedDictionary;

    public Anagrams(List<String> dictionary) {
        this.dictionary = dictionary;
        prunedDictionary = new ArrayList<>();
    }


    public void print(String phrase, int max) {
        if (max < 0)
            throw new IllegalStateException();
        pruneDictionary(phrase);
        if (prunedDictionary.size() != 0) {
            if (max == 0) {
                print(new LetterInventory(phrase), Integer.MAX_VALUE, "");
            } else {
                print(new LetterInventory(phrase), max, "");
            }
        }
    }

    public void print(LetterInventory phrase, int max, String out) {
        if (phrase.isEmpty())
            System.out.println(out);
        else if (max != 0) {
            for (String word : prunedDictionary) {
                LetterInventory res = phrase.subtract(new LetterInventory(word));
                if (res != null)
                    print(res, max - 1, out + word + " ");
            }
        }
    }

    public void pruneDictionary(String phrase) {
        LetterInventory phraseIn = new LetterInventory(phrase);
        dictionary.forEach(word -> {
            if (phraseIn.subtract(new LetterInventory(word))!= null) {
                prunedDictionary.add(word);
            }
        });
    }
}
