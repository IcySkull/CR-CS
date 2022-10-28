import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuitarHero {
    private final static String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private List<GuitarString> strings;

    public static void main(String[] args) {
        play();
    }
    
    private static Map<Character, GuitarString> getStrings() {
        return KEYBOARD.chars()
            .map(KEYBOARD::indexOf)
            .mapToDouble(GuitarHero::getKeyFrequency)
            .mapToObj(GuitarString::new)
            .collect(Collectors.toMap(, null));
    }

    private static void play() {
        Map<Character, GuitarString> strings = getStrings();
        while (true) {
            
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (key == 'a') 
                    stringA.pluck();
                else if (key == 'c') 
                    stringC.pluck();
            }

            // compute the superposition of the samples
            double sample = stringA.sample() + stringC.sample();

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            stringA.tic();
            stringC.tic();
        }
        
    }

    private static double getKeyFrequency(int i) {
        return 440 * Math.pow(1.05956, i - 24);
    }
}
