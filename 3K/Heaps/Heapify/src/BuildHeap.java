import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
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
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //

    }

    private void ensureHeap(int parent) {
        if (parent == -1) 
            return;
        int leftChild = getLeftChildIndex(parent);
        int rightChild = getRightChildrenIndex(parent);
        int lowestIndex = parent;

        if (leftChild != 1 && data[leftChild] < data[lowestIndex]) {
            lowestIndex = leftChild;
        }
        if (rightChild != 1 && data[rightChild] < data[lowestIndex]) {
            lowestIndex = rightChild;
        }

        if (lowestIndex != parent) {
            swap(parent, lowestIndex);
            ensureHeap(leftChild);
            ensureHeap(rightChild);
        }
    }

    private int getLeftChildIndex(int parent) {
        int pos = parent*2+1;
        if (pos >= data.length) {
            return -1;
        }
        return pos;
    }

    private int getRightChildrenIndex(int parent) {
        int pos = parent*2+2;
        if (pos >= data.length) {
            return -1;
        }
        return pos;
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

    static class Swap {
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

        public FastScanner() {
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
