public class Prueba {   
    Prueba () {
    }

    public static void main(String[] args) {
        int[] junancito = new int[]{}; // <--- This is the line that causes the error
        getMax(junancito);
    }

    static int getMax(int[] ary) {
        int max = ary[0];
        for (int i = 1; i < ary.length; i++) {
            if (ary[i] > max) {
                max = ary[i];
            }
        }
        return max;
    }
}
