package selection;

import testcases.SortingCases;
import testcases.TestCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selection extends SortingCases {
    TestCases cases;
    final int RANGE_INTEGERS = 100;
    final int ARRAYS_SIZE = 20;
    final boolean NEGATIVE_INCLUDED = true;

    public static void main(String[] args) {
        new Selection(10);
    }

    Selection(int cases) {
        createTestCases(cases);
    }

    public static <T extends Comparable<? super T>> void selectionSort(List<T> list) {
        for (int i = 0; i<list.size(); i++) {
            int minIndex = i;
            for (int j = 1 + i; j<list.size(); j++) {
                if (list.get(j).compareTo(list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            swap(list, minIndex, i);
        }
    }

    private static <T> void swap(List<T> list, int a, int b) {
        T tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }

    public void createTestCases(int cases) {
        int[][] tests = new int[cases][];
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~\n\tUnsorted Cases:");
        for(int i = 0; i < cases; i++) {
            tests[i] = randomIntArray(20);
            System.out.println(Arrays.toString(tests[i]));
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~\n\tSorted Cases:");
        for (int i = 0; i < tests.length; i++) {
            List<Integer> list = intArrayToList(tests[i]);
            selectionSort(list);
            System.out.println(Arrays.toString(list.toArray()));
        }
    }


    private int[] randomIntArray(int size) {
        int[] intArray = new int[size];
        for (int i = 0; i < intArray.length; i++) {
            int sign = 1;
            if (NEGATIVE_INCLUDED) {
                sign = Math.random() > 0.4 ? 1 : -1;
            }
            int randomNumber = (int)(Math.random()*RANGE_INTEGERS)*sign;
            intArray[i] = randomNumber;
        }
        return intArray;
    }
}
