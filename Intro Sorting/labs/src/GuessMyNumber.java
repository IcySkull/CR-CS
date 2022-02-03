import java.util.Scanner;

public class GuessMyNumber {
    Scanner reader;
    public static void main(String[] args) {
        new GuessMyNumber();
    }

    GuessMyNumber() {
        reader = new Scanner(System.in);
        newUserMessage();
        startGame();
    }

    public void newUserMessage() {
        System.out.println(
                """
                        Piensa en un número del 0 al 100\s
                        Lo adivinaré en 7 intentos o incluso menos ha! ha! ha!\s
                        'h' indica que el número es mayor\s
                        'l' indica que el número es menor\s
                        'c' indica que adiviné el número""" + "\n"
        );
    }

    public void startGame() {
        if (startGame(0, 100)) {
            System.out.println("Ha! Ha! adiviné tu número");
            System.out.println("¿Quieres comenzar de nuevo?");
            if (reader.nextLine().equalsIgnoreCase("si")) {
                startGame(0, 100);
            }
        }
    }

    public boolean startGame(int lower, int upper) {
        int guess = (lower + upper) / 2;
        System.out.println("¿Es tu número " + guess + "?");
        String response = reader.nextLine();

        if (response.equalsIgnoreCase("c")) {
            return true;
        }
        else if (response.equalsIgnoreCase("h")) {
            return startGame(guess+1, upper);
        }
        else if (response.equalsIgnoreCase("l")) {
            return startGame(lower, guess-1);
        }

        System.out.println("Introduce una letra correcta establecida");
        return startGame(upper, lower);
    }

}
