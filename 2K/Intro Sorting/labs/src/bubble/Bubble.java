package bubble;

import java.io.*;
import java.util.*;

public class Bubble {
    public static void main(String[] args) throws IOException {
        Bubble test = new Bubble();

        File inputFile = new File(
                "C:\\Users\\diejo\\Documents\\Estudios\\Cypress Ranch High School\\Computer Science\\Java CRHS\\Intro Sorting\\labs\\resources\\presidents.txt"
        );
        List<String> presidents = new ArrayList<>();
        try {
            presidents = readNamesFromFile(inputFile);
        }
        catch (IOException e) {
            System.out.println("File not found");
        }

        bubbleSort(presidents);
        writeOutNames(presidents);
    }

    public static <T extends Comparable<? super T>> void bubbleSort(List<T> list) {
        boolean isSorted = false;
        int max = 0;
        while (!isSorted) {
            isSorted = true;
            max++;
            for(int i = 0; i < list.size() - max; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    isSorted = false;
                    swap(list, i, i+1);
                }
            }
        }
    }

    private static <T> void swap(List<T> list, int a, int b) {
        T tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }

    private static List<String> readNamesFromFile(File inputFile) throws IOException {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\tUnordered names:");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<String> presidents = new ArrayList<>();
        String line;
        do {
            line = reader.readLine();
            System.out.println(line);
            presidents.add(line);
        } while (reader.readLine() != null);
        return presidents;
    }

    public static void writeOutNames(List<String> names) throws IOException {
        File output = new File(
"C:\\Users\\diejo\\Documents\\Estudios\\Cypress Ranch High School\\Computer Science\\Java CRHS\\Intro Sorting\\labs\\src\\bubble\\out\\ordered names.txt"
        );
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\tOrdered names:");
        FileWriter outputStream = new FileWriter(output);
        names.forEach((n) -> {
            try {
                String out = n;
                outputStream.write(out);
                System.out.println(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        outputStream.close();
    }
}