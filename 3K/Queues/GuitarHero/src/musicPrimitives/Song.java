package musicPrimitives;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import music.MusicAdapter;
import music.library.MusicSheet;
import musicPrimitives.instruments.InstrumentString;
import musicPrimitives.instruments.Instruments;

public class Song {
    public final static int MIN_TO_MILIS = 60000;
    private final double bpms;
    private final double bpMeasure;
    private final double msPerMeasure;

    public static Map<Character, InstrumentString> guitar = MusicAdapter.getStrings(Instruments.GUITAR);

    private static Queue<Measure> measures;
    private int index;
    private long startTime;

    public Song(MusicSheet sheet) {
        this.measures = new LinkedList<>();
        this.bpms = getBPMToMilis(sheet.getBPM());
        this.bpMeasure = sheet.getBeatsPerMeasure();
        this.msPerMeasure = bpMeasure / bpms;

        addMeasures(sheet.getMeasures());
        index = 0;
        startTime = System.currentTimeMillis();
        play();
    }

    public void play() {
        while (!measures.isEmpty()) {
            Measure measure = measures.poll();
            measure.play(guitar, msPerMeasure);
        }
    }

    public double getBeatsPerMeasure() {
        return bpMeasure;
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void addMeasures(List<Measure> measures) {
        this.measures.addAll(measures);
    }

    public static double getBPMToMilis(double bpm) {
        return bpm / MIN_TO_MILIS;
    }

    public double durationOfMeasure() {
        return msPerMeasure;
    }

}
