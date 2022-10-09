public class Nibble {
    static private final int RIGHT = 0B0000_1111;
    static private final int LEFT = 0B1111_0000;

    public static void main(String[] args) throws Exception {
        Integer[] n = {100, 59, 33, 122};
        for (int num : n) {
            System.out.println(swapNibbles(num));
        }
    }

    static public int swapNibbles(int val) {
        int right = val & RIGHT;
        int left = val & LEFT;   
        return (right << 4) + (left >> 4);
    }
}
