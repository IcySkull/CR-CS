public class App {
    public static void main(String[] args) throws Exception {
        char[] chars = {'a', 'b', 'c', 'd', 'z', 'S', 'T', 'V', 'z'};

        for (char c : chars) {
            System.out.println(changeCase(c));
        }
    }

    static public char changeCase(char character) {
        return (char) (character < 90 ? (character + 0x20) : (character - 0x20));
    }
}
