
import java.util.*;

public class MyArrayList<E> implements Iterable<E> {
    private int size;       // the number of elements stored
    E[] ary;                // access modifier is package protected for testing purposes
    static final int THRESHOLD = 10;

    public MyArrayList() {    // start with a threshold/capacity of 10
        ary = (E[]) new Object[THRESHOLD];
        size = 0;
    }

    public boolean isEmpty() {    // is the list empty?
        return size == 0;
    }

    public int size() {         // the number of elements in the list
        return size;
    }

    // add the item to the end unless it's null and throw a NoSuchElementException
    public void add(E item) {
        if (item == null)
            throw new NoSuchElementException();
        size++;
        if (size == ary.length) {
            E[] tmpAry = (E[]) new Object[ary.length * 2];
            for (int i = 0; i < size - 1; i++) { // size is -1 since the ary array doesnt has  the new item to be added
                tmpAry[i] = ary[i];
            }
            ary = tmpAry;
            // finish of copy
        }
        ary[size - 1] = item;
    }

    // add the item at the specified index
    // throw a NoSuchElementException if item is null
    // throw an IndexOutOfBounds exception if the index is invalid
    public void add(E item, int index) {
        if (item == null)
            throw new NoSuchElementException();
        else if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
        else {
            size++; // increment at first to cover the following case
            // case where adding the element makes the ArrayList reach the capacity
            if (size == ary.length) {
                E[] tmpAry = (E[]) new Object[ary.length * 2];
                System.arraycopy(ary, 0, tmpAry, 0, index);
                System.arraycopy(ary, index + 1, tmpAry, index + 1, size - index - 1);
                ary = tmpAry;
                ary[index] = item;
                // finish of copy
            } else {
                if (index + 1 == size)
                    ary[index] = item;
                else {
                    int i = 2;
                    while (size - i != index - 1) { // swap all the values (to the right) to make the space to add the item
                        ary[size - i + 1] = ary[size - i];
                        i++;
                    }
                    ary[index] = item;
                }
            }
        }
    }

    // remove and return the item at the index
    // throw an IndexOutOfBounds exception if the index is invalid
    public E remove(int index) {
        Objects.checkIndex(index, size);
        size--;
        E r = ary[index];
        if (ary.length > THRESHOLD && size <= ary.length * 0.25) {
            E[] tmpAry = (E[]) new Object[(int) (ary.length * 0.5)];
            System.arraycopy(ary, 0, tmpAry, 0, index);
            System.arraycopy(ary, index + 1, tmpAry, index + 1, size - index);
            ary = tmpAry;
        } else {
            if (index == size)
                ary[index] = null;
            else {
                while (index + 1 != size && ary[index] != null) {
                    ary[index] = ary[index + 1];
                    index++;
                }
                ary[index] = ary[index + 1];
                ary[index + 1] = null;
            }
        }
        return r;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return ary[index];
    }

    public void clear() {
        ary = (E[]) new Object[THRESHOLD];
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {         // return an iterator over items in order
        return new Itr(this);
    }

// note: the index out of bounds is never checked because ary will always double its size if it reaches its capacity.
private class Itr implements Iterator<E> {
    int index = -1;
    boolean removed;
    MyArrayList<E> list;

    private Itr(MyArrayList<E> list) {
        this.list = list;
        removed = true;
    }

    @Override
    public boolean hasNext() {
        return index+1 < list.size();
    }

    @Override
    public void remove() {
        if (removed)
            throw new IllegalStateException();
        else {
            list.remove(index);
            index--;
            removed = true;
        }
    }

    @Override
    public E next() {
        if (index >= size)
            throw new NoSuchElementException();
        else {
            removed = false;
            index++;
            return ary[index];
        }
    }
}

    // basic test cases
    // try adding your own thru JUnit
    public static void main(String[] args) {
        MyArrayList<String> test = new MyArrayList<>();
//        ArrayList<String> test = new ArrayList<>();
        test.add("Love");
        test.add("I", 0);
        test.add("Computer");
        test.add("Science");
        System.out.println(test.size());
        for (String item : test) {
            System.out.println(item);
        }
        test.remove(test.size() - 1);
        test.remove(2);
        test.remove(0);
        for (String item : test) {
            System.out.println(item);
        }

        test.remove(test.size() - 1);
        test.add("Iterators");
        test.add("Rock");
        for (String item : test) {
            System.out.println(item);
        }

        test.clear();

        System.out.println("After clearing, size is: " + test.size());
        for (int i = 0; i < 10; i++) {
            test.add("" + i);
        }

        Iterator<String> it = test.iterator();
        for (; it.hasNext(); ) {
            System.out.print(it.next() + " ");
        }

        try {
            it.next();
        } catch (Exception e) {
            System.out.println("\nNo more elements to iterate");
        }

        test.clear();
        it = test.iterator();
        for (int i = 0; i < 10; i++) {
            test.add("" + i);
        }
        it.next();
        it.remove();
        it.next();
        it.next();
        it.next();
        it.remove();
        System.out.println("" + java.util.Arrays.toString(test.ary));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 2621440; i++) {
            test.add("how fast?");
        }
        for (int i = 0; i < 1310730; i++) {
            test.remove(test.size() - 1);
        }
        for (int j = 0; j < 1E8; j++) {
            for (int i = 0; i < 10; i++) {
                test.add("how fast?");
            }
            for (int i = 0; i < 10; i++) {
                test.remove(test.size() - 1);
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("My ArrayList: " + (stop - start) / 1000.0);

        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 2621440; i++) {
            list.add("how fast?");
        }
        for (int i = 0; i < 1310730; i++) {
            list.remove(list.size() - 1);
        }
        for (int j = 0; j < 1E8; j++) {
            for (int i = 0; i < 10; i++) {
                list.add("how fast?");
            }
            for (int i = 0; i < 10; i++) {
                list.remove(list.size() - 1);
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Java's ArrayList: " + (stop - start) / 1000.0);
    }
}
