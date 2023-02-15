import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve("tests/own2.txt");
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
        out.println(Arrays.toString(data));
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
        ensureHeap(swaps, 0);
    }

    private void ensureHeap(List<Swap> swaps, int parent) {
        if (parent == -1)
            return;
        int leftChild = getLeftChild(parent);
        int rightChild = getRightChild(parent);
        int smallestChild = parent;

        ensureHeap(swaps, leftChild);
        ensureHeap(swaps, rightChild);

        if (leftChild != -1 && data[leftChild] < data[smallestChild])
            smallestChild = leftChild;
        if (rightChild != -1 && data[rightChild] < data[smallestChild])
            smallestChild = rightChild;
        
        if (smallestChild != parent) {
            swap(swaps, parent, smallestChild);
            ensureHeap(swaps, smallestChild);
        }
    }

    private int getLeftChild(int parent) {
        int leftChild = 2 * parent + 1;
        if (leftChild >= data.length) {
            return -1;
        }
        return leftChild;
    }

    private int getRightChild(int parent) {
        int rightChild = 2 * parent + 2;
        if (rightChild >= data.length) {
            return -1;
        }
        return rightChild;
    }

    private void swap(List<Swap> swaps, int aIndex, int bIndex) {
        swaps.add(new Swap(aIndex, bIndex));
        int tmp = data[aIndex];
        data[aIndex] = data[bIndex];
        data[bIndex] = tmp;
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    public void solve(String fileName) throws IOException {
        in = new FastScanner(fileName);
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    public int[] getData() {
        return data;
    }

    public List<Swap> getSwaps() {
        return swaps;
    }

    public static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner(String fileName) throws FileNotFoundException {
            String workingDir = BuildHeap.class.getResource("").getPath();
            System.out.println("Working dir: " + workingDir);
            reader = new BufferedReader(new FileReader(workingDir + "../" + fileName));
            tokenizer = null;
        }

        public FastScanner() throws FileNotFoundException {
            // reader = new BufferedReader(new FileReader("tests/03.txt"));
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
