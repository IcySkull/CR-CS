import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.*;

public class SwitchesSignal {
    private List<String> switches;
    private int signal;
    private final int BITS = 8;

    public SwitchesSignal(int signal) {
        this.signal = signal;
        switches = IntStream.range(0, BITS)
            .mapToObj(this::getSignalStatus)
            .collect(Collectors.toList());
    }

    public String getSignalStatus(int lever) {
        int value = maskSignal(lever);
        if (value > 0)
            return "ON";
        return "OFF";
    }

    public int maskSignal(int position) {
        return signal & getMaskOfBit(position);
    }

    public int getMaskOfBit(int position) {
        return (int)Math.pow(2, position);
    }
    
    @Override
    public String toString() {
        String out = "";
        int sw = 1;
        for (String status : switches) {
            out += "Switch " + sw + " is " + status + "\n";
            sw++;
        }
        return out;
    }
}
