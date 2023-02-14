public interface Heapable {
    int getSize();
    int getLevel(int n);
    int maxNodes(int level);
    int get(int index);
}