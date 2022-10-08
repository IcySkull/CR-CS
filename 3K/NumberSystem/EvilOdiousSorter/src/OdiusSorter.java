import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class OdiusSorter {
    public static void main(String[] args) throws IOException {
        List<EvilNumber> list = Files.lines(Paths.get("numbers.dat"))
            .map(Integer::parseInt)
            .map(EvilNumber::new)
            .sorted()
            .collect(Collectors.toList());
            
        System.out.println(list);
    }
}
