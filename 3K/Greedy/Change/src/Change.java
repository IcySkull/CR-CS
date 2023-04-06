import java.util.Scanner;

public class Change {
    public static int getChange(int m) {
        int count = 0;
        
        int tens = m / Money.TEN.value;
        count += tens;
        m -= tens * Money.TEN.value;

        int fives = m / Money.FIVE.value;
        count += fives;
        m -= fives * Money.FIVE.value;

        int ones = m / Money.ONE.value;
        count += ones;
        m -= ones * Money.ONE.value;

        return count;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }

    enum Money {
        ONE(1), FIVE(5), TEN(10);

        int value;

        Money (int value) {
            this.value = value;
        }
    }

}

