package testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCases implements IntegerSorter {
    private int range;
    private int cases;
    private boolean negatives;

    public TestCases() {
        range = 100;
        cases = 10;
        negatives = true;
    }

    public TestCases(int range, int cases, boolean negatives) {
        this.range = range;
        this.cases = cases;
        this.negatives = negatives;
    }

    public List<Integer> intArrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }
        return list;
    }

    public int[] createRandomIntArray(int size) {
        int[] intArray = new int[size];
        for (int i = 0; i < intArray.length; i++) {
            int sign = 1;
            if (negatives) {
                sign = Math.random() > 0.4 ? 1 : -1;
            }
            int randomNumber = (int)(Math.random()*range)*sign;
            intArray[i] = randomNumber;
        }
        return intArray;
    }

    public int getRange() {
        return range;
    }

    public int getCases() {
        return cases;
    }

    public boolean isNegatives() {
        return negatives;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public void setNegatives(boolean negatives) {
        this.negatives = negatives;
    }

    @Override
    public void sortIntegers(List<Integer> list) {

    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int[][] tests = new int[cases][];
        output.append("~~~~~~~~~~~~~~~~~~~~~~\n\tUnsorted Cases:\n");
        for(int i = 0; i < cases; i++) {
            tests[i] = createRandomIntArray(20);
            output.append(Arrays.toString(tests[i])).append("\n");
        }

        output.append("\n~~~~~~~~~~~~~~~~~~~~~\n\tSorted Cases:\n");
        for (int[] test : tests) {
            List<Integer> list = intArrayToList(test);
            sortIntegers(list);
            output.append(Arrays.toString(list.toArray())).append("\n");
        }

        return output.toString();
    }
}
