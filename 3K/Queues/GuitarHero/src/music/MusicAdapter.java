package music;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import dataStructures.KeyTuple;
import musicPrimitives.instruments.*;
import tools.StdAudio;

public final class MusicAdapter {
    private final static String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static Map<Character, InstrumentString> getStrings(Instruments instrument) {
        return KEYBOARD.chars()
                .mapToObj(MusicAdapter::getKeyFrequency)
                .map(tuple -> new InstrumentString(tuple.getFrequency(), tuple.getKey(), instrument))
                .collect(
                        Collectors.toMap(
                                InstrumentString::getKey,
                                Function.identity()));
    }

    public static KeyTuple getKeyFrequency(int c) {
        char key = (char) c;
        int index = getIndex(key);
        return new KeyTuple(key, 440 * Math.pow(1.05956, index - 24));
    }

    public static int getIndex(char key) {
        return KEYBOARD.indexOf(key);
    }

    public static void outAudio(Map<Character, InstrumentString> instrument) {
        StdAudio.play(getSampleOut(instrument));
    }

    public static double getSampleOut(Map<Character, InstrumentString> instrument) {
        return instrument.values().stream()
                .mapToDouble(InstrumentString::sample)
                .reduce(InstrumentString::addSamples)
                .orElse(0.0);
    }
}
