package selection;

import testcases.TestCases;

import java.util.Arrays;
import java.util.List;

public class Selection extends TestCases {
    TestCases cases;
    final int ARRAYS_SIZE = 20;

    public static void main(String[] args) {
        new Selection(10);
    }

    public Selection(int cases) {
        super();
        setCases(cases);
        System.out.println(this);
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

    @Override
    public void sortIntegers(List<Integer> list) {
        selectionSort(list);
    }
}
