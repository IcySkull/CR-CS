package testcases;

import java.util.ArrayList;
import java.util.List;

public class TestCases {

    public static List<Integer> intArrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }
        return list;
    }
}
