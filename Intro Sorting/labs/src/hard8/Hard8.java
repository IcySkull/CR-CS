package hard8;

import testcases.TestCases;

import java.util.List;

public class Hard8 extends TestCases {
    public static void main(String[] args) {
        new Hard8(10, 10, false);
    }

    Hard8(int range, int cases, boolean negatives) {
        super(range, cases, negatives);
        System.out.println(this);
    }

    public static <T extends Comparable<? super T>> void Hard8(List<T> list, T target) {
        for (int i = 1; i<list.size(); i++) {
            if (list.get(i).equals(target)) {
                for (int j = i-1; j>=0; j--) {
                    if (!list.get(j).equals(target)) {
                        swap(list, j, j+1);
                    }
                }
            }
        }
    }

    private static <T> void swap(List<T> list, int a, int b) {
        T tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }

    @Override
    public void sortIntegers(List<Integer> list) {
        Hard8(list, 8);
    }
}
