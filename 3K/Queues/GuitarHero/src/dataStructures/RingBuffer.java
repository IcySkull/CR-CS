package dataStructures;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RingBuffer implements Iterable<Double> {
    private double[] ary;
    private int size;
    private int first;
    private int last;
    
    public RingBuffer(int capacity) {
        ary = new double[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == ary.length;
    }

    public void enqueue(double x) {
        if (isFull())
            throw new IllegalStateException();
        ary[last] = x;
        size++;
        incrementLast();
    }

    public double dequeue() {
        if (size() == 0)
            throw new NoSuchElementException();
        incrementFirst();
        size--;
        return ary[decrementIndex(first)];
    }

    public double peek() {
        if (size() == 0)
            throw new NoSuchElementException();
        return ary[first];
    }

    public int incrementIndex(int index) {
        return (index + 1) % ary.length;
    }

    public int decrementIndex(int index) {
        return (index - 1 + ary.length) % ary.length;
    }

    private void incrementLast() {
        last = incrementIndex(last);
    }

    private void decrementLast() {
        last = decrementIndex(last);
    }

    private void incrementFirst() {
        first = incrementIndex(first);
    }

    private void decrementFirst() {
        first = decrementIndex(first);
    }

    public Stream<Double> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public String toString() {
        if (size() == 0)
            return "[]";
        Iterator<Double> it = iterator();
        StringBuilder sb = new StringBuilder("[");
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append(", ");
            else    
                sb.append("]");
        }
        return sb.toString();
    }

    @Override
    public Iterator<Double> iterator() {
        return new RingBufferIterator(first);
    }

private class RingBufferIterator implements Iterator<Double> {
        private int index;
        private int count;

        public RingBufferIterator(int index) {
            this.index = index;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size();
        }

        @Override
        public Double next() {
            if (!hasNext())
                throw new NoSuchElementException();
            double out = ary[index];
            index = incrementIndex(index);
            count++;
            return out;
        }
    }
}
