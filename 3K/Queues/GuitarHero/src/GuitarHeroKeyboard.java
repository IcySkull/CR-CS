import java.util.*;

import music.MusicAdapter;
import musicPrimitives.instruments.InstrumentString;
import musicPrimitives.instruments.Instruments;
import tools.StdAudio;
import tools.StdDraw;

public class GuitarHeroKeyboard {
    private static Map<Character, InstrumentString> strings = MusicAdapter.getStrings(Instruments.GUITAR);

    public static void main(String[] args) {
        play();
    }

    private static void play() {
        while (true) {
            // check if the user has typed a key, and, if so, process itq
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();
                InstrumentString str = strings.get(key);
                if (str != null) {
                    str.pluck();
                }

            }

            for (char key : StdDraw.getKeysDown()) {
                InstrumentString str = strings.get(key);
                if (str != null) {
                    str.tic();
                }
            }
            // compute the superposition of the samples
            StdAudio.play(getSampleOut());

        }

    }

    private static double getSampleOut() {
        return strings.values().stream()
                .mapToDouble(InstrumentString::sample)
                .reduce(InstrumentString::addSamples)
                .orElse(0.0);
    }
}
