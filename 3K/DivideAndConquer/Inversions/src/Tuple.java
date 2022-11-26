public class Tuple<T, R> {
    private T first;
    private R second;

    public Tuple(T first, R second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public R getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(R second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
