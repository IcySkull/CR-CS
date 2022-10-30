package music;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import dataStructures.KeyTuple;
import musicPrimitives.instruments.*;

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

    public static KeyTuple getKeyFrequency(int character) {
        int index = KEYBOARD.indexOf((char) character);
        return new KeyTuple((char) character, 440 * Math.pow(1.05956, index - 24));
    }
}
