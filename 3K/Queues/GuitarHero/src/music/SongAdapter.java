package music;

import java.util.List;
import java.util.Map;

import javax.sound.midi.Instrument;

import musicPrimitives.Song;
import musicPrimitives.instruments.InstrumentString;
import musicPrimitives.instruments.Instruments;
import musicPrimitives.threePulse.Interval;

public class SongAdapter extends Song {
    Map<Character, InstrumentString> strings = MusicAdapter.getStrings(Instruments.GUITAR);

    public SongAdapter(List<Interval> intervals, int bpm) {
        super(intervals, bpm);
        //TODO Auto-generated constructor stub
    }
    
}
