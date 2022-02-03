import java.math.*;

public class CreditCards {

    // this method calculates the balance of a cc after only paying the min payment for a year
    public static double minMonthlyPayment(double balance, double annualInterestRate, double monthlyPaymentRate) {
        double monthlyInterestRate = annualInterestRate/12.0;
        double minMonthPay;
        double unPaidBalance;
        for (int i = 1; i<=12; i++) {
            minMonthPay = balance * monthlyPaymentRate;
            unPaidBalance = balance - minMonthPay;
            balance = unPaidBalance + (monthlyInterestRate * unPaidBalance);
        }
        return roundDouble(balance, 2);
    }

    private static double roundDouble(double number, int places) {
        BigDecimal round = new BigDecimal(Double.toString(number));
        round = round.setScale(places, RoundingMode.HALF_UP);
        return round.doubleValue();
    }

    // this method used exhaustive enumeration to ascertain
    public static int minFixedMonthlyPayment(double balance, double annualInterestRate) {
        double monthlyInterestRate = annualInterestRate / 12.0;
        double debt;
        int fixedPay = 0;

        do {
            debt = balance;
            fixedPay += 10;
            for (int i = 0; i<12; i++) {
                debt = debt - fixedPay;
                debt = debt + (monthlyInterestRate * debt);
            }
        } while (!(debt < 0));

        return fixedPay;
    }

    // method calculates the min amount required to pay off a cc in a year by using bisection search
    public static double bisectionMonthlyAmount(double balance, double annualInterestRate) {
        double monthlyIR = annualInterestRate / 12.0;
        double lower = balance / 12.0;
        double upper = (balance * Math.pow(1 + monthlyIR, 12)) / 12.0;
        return bisectionMonthlyAmount(balance, monthlyIR, lower, upper);
        }

    // recursive solution
    public static double bisectionMonthlyAmount(double balance, double monthlyIR, double lower, double upper) {
        double debt = balance;
        double guess = (upper + lower) * 0.5;

        for (int i = 0; i<12; i++) {
            debt = debt - guess;
            debt = debt + (monthlyIR * debt);
        }
        if (roundDouble(debt, 3) == 0) {
            return roundDouble(guess, 2);
        }
        else if (debt > 0) {
            return bisectionMonthlyAmount(balance, monthlyIR, guess, upper);
        }

        return bisectionMonthlyAmount(balance, monthlyIR, lower, guess);
    }

    public static void main(String[] args) {
        // testing part A
        System.out.println(minMonthlyPayment(42, .2, .04));
        System.out.println(minMonthlyPayment(484, .2, .04));
        System.out.println(minMonthlyPayment(151, .2, .06));
        System.out.println(minMonthlyPayment(412, .18, .08));
        System.out.println(minMonthlyPayment(416, .18, .06));
        System.out.println(minMonthlyPayment(430, .15, .04));
        System.out.println();

        // testing part B
        System.out.println(minFixedMonthlyPayment(3329, .2));
        System.out.println(minFixedMonthlyPayment(4773, .2));
        System.out.println(minFixedMonthlyPayment(3926, .2));
        System.out.println(minFixedMonthlyPayment(70, .25));
        System.out.println(minFixedMonthlyPayment(938, .2));
        System.out.println(minFixedMonthlyPayment(793, .2));
        System.out.println(minFixedMonthlyPayment(835, .18));
        System.out.println(minFixedMonthlyPayment(4778, .15));
        System.out.println(minFixedMonthlyPayment(4510, .04));
        System.out.println(minFixedMonthlyPayment(4919, .04));
        System.out.println(minFixedMonthlyPayment(3603, .18));
        System.out.println(minFixedMonthlyPayment(4800, .2));
        System.out.println(minFixedMonthlyPayment(4053, .15));
        System.out.println(minFixedMonthlyPayment(4019, .15));
        System.out.println(minFixedMonthlyPayment(3618, .15));
        System.out.println();

        // testing part C - add test cases

        System.out.println(bisectionMonthlyAmount(320000, 0.2));
        System.out.println(bisectionMonthlyAmount(999999,0.18));
        System.out.println(bisectionMonthlyAmount(44681, 0.2));
        System.out.println(bisectionMonthlyAmount(282651, 0.15));
        System.out.println(bisectionMonthlyAmount(297119, 0.15));
        System.out.println(bisectionMonthlyAmount(289655, 0.21));
        System.out.println(bisectionMonthlyAmount(98461, 0.18));
        System.out.println(bisectionMonthlyAmount(374521, 0.22));
        System.out.println(bisectionMonthlyAmount(191204, 0.15));
//        System.out.println(bisectionMonthlyAmount(2147483000, 0.15));
        System.out.println(bisectionMonthlyAmount(351742, 0.15));
        System.out.println(bisectionMonthlyAmount(277620, 0.18));
        System.out.println(bisectionMonthlyAmount(497628, 0.15));
    }
}
