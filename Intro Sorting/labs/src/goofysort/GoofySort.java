package goofysort;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GoofySort {
    public static void main(String[] args) {
        GoofySort app = new GoofySort();
        String line = app.getLine();
        while (!line.equalsIgnoreCase("no")) {
            System.out.println(app.goofySort(line));
            line = app.getLine();
        }
        System.exit(0);
    }

    public String getLine() {
        Scanner read = new Scanner(System.in);
        System.out.println(
                "Enter a sentence to \"sort\". Enter \"NO\" if you want to exit"
        );
        return read.nextLine();
    }

    public String goofySort(String line) {
        StringBuilder out = new StringBuilder();
        String[] words = line.split(" ");

        boolean lineIsSorted = false;
        int j = 0;
        while (!lineIsSorted) {
            lineIsSorted = true;
            for (int i = words.length-1; i>=0; i--) { // read the array of words in reverse
                char[] chars = words[i].toCharArray();
                if (j < chars.length) { // if the position is lower than the size then there is a character that needs to be appended
                    lineIsSorted = false;
                    out.append(chars[chars.length-j-1]); // read the characters in reverse
                } else { // if the size of the word is greater, then add a space to avoid ArrayOutOfBounds
                    out.append(" ");
                }
            }
            j++; // jump to the next characters which is a new line
            out.append("\n");
        }

        return out.toString();
    }
}