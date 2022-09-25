package recursion_intro;

import java.util.*;

import static java.lang.System.*;

public class Permutation {
    private String orig;
    private ArrayList<String> list;

    public Permutation(String word) {
        orig = word;
        list = new ArrayList<>();
        permutation();
    }

    public void permutation() {
        out.println("\nPERMUTATION OF WORD :: " + orig);
        permutation(orig, "");
    }

    private void permutation(String orig, String tail) {
        for (int i = 0; i < orig.length(); i++) {
            String res = orig.substring(0, i) + orig.substring(i + 1);
            tail += orig.substring(i, i + 1);
            String permuted = permutate(res, tail);
            if (permuted != null) {
                list.add(permuted);
            }
            tail = tail.substring(0, tail.length() - 1);
        }
    }

    private String permutate(String orig, String tail) {
        if (orig.length() == 1)
            return tail + orig;
        permutation(orig, tail);
        return null;
    }

    public String toString() {
        String out = "";
        for (String item : list) {
            out += item + "\n";
        }
        return out;
    }
}
