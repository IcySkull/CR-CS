import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.Test;

public class MergeInversionsTest {
    @Test
    public void testCountInversions1() {
        List<Integer> list1 = Arrays.asList(90, 40, 20, 30, 10, 67);
        long count1 = MergeInversions.countInversions(list1);
        assert isSorted(list1);
    }

    @Test
    public void testCountInversions2() {
        List<Integer> list2 = Arrays.asList(3, 15, 61, 11, 7, 9, 2);
        long count2 = MergeInversions.countInversions(list2);
        assert isSorted(list2);
    }

    @Test
    public void testCountInversions3() throws FileNotFoundException {
        File file = getFile("IntegerArray.txt");
        Scanner scanner = new Scanner(file);
        List<Integer> list3 = scanner.tokens().map(Integer::parseInt).collect(Collectors.toList());
        long count3 = MergeInversions.countInversions(list3);
        assert isSorted(list3);
    }

    private File getFile(String string) {
        URL url = getClass().getResource(string);
        return new File(url.getFile());
    }

    public boolean isSorted(List<Integer> list) {
        list.stream().reduce((a, b) -> {
            assert a <= b;
            return b;
        });
        return true;
    }
}
