public interface Heapable {
    int getSize();
    int getLevel(int n);
    int limitNodes(int level);
    int get(int index);
}