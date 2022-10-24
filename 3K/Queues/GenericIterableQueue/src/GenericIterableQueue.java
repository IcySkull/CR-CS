import java.util.*;

public class GenericIterableQueue<T> implements Iterable<T> {
    private final List<T> NEUTRO = new ArrayList<T>();
    private List<T> list;

    public GenericIterableQueue() {
        list = new ArrayList<T>();
    }

    public void add(T item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");
    }

    public T remove() {
        if (list.isEmpty())
            throw new NoSuchElementException("Queue is empty");
        return list.remove(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public T peek() {
        if (isEmpty())
            return null;
        return list.get(0);
    }

    public T poll() {
        if (isEmpty())
            return null;
        return remove();
    }

    public String toString() {
        return list.toString();
    }

    public ListIterator<T> listIterator() {
        return (ListIterator) iterator();
    }

    public Iterator<T> iterator() {
        return new ListIterator<T>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                return index >= -1 && index < list.size();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return list.get(++index);
            }

            @Override
            public boolean hasPrevious() {
                return index > 0 && index <= list.size();
            }

            @Override
            public T previous() {
                if (!hasPrevious())
                    throw new NoSuchElementException();
                return list.get(--index);
            }

            @Override
            public int nextIndex() {
                if (!hasNext())
                    return index;
                return index+1;
            }

            @Override
            public int previousIndex() {
                if (!hasPrevious())
                    return index;
                return index-1;
            }

            @Override
            public void remove() {
                if (index < 0 || index >= list.size())
                    throw new IllegalStateException();
                list.remove(index);
            }

            @Override
            public void set(T e) {
                if (index < 0 || index >= list.size())
                    throw new IllegalStateException();
                else if (e == null)
                    throw new NullPointerException("Item cannot be null");
                list.set(index, e);
            }

            @Override
            public void add(T e) {
                if (e == null)
                    throw new NullPointerException("Item cannot be null");
                list.add(++index, e);
            }
        };
    }

}