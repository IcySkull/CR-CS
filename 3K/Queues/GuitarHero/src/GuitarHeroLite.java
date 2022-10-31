import musicPrimitives.instruments.InstrumentString;
import musicPrimitives.instruments.Instruments;
import tools.StdAudio;
import tools.StdDraw;

/*****************************************************************************
 *  Compilation:  javac GuitarHeroLite.java
 *  Execution:    java  GuitarHeroLite
 *  Dependencies: StdAudio.java StdDraw.java GuitarString.java
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/

public class GuitarHeroLite {

    public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        InstrumentString stringA = new InstrumentString(CONCERT_A, 'a', Instruments.GUITAR);
        InstrumentString stringC = new InstrumentString(CONCERT_C, 'c', Instruments.STRING);

        final double TEXT_POS_X = .2;
        final double TEXT_POS_Y = .5;
        
        StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type 'a' or 'c' to play a note!");
        
        play(stringA, stringC);
    }
    
    private static void play(InstrumentString stringA, InstrumentString stringC) {        // the main input loop
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
            double sample = InstrumentString.evenSamples(stringA.sample(), stringC.sample());

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            stringA.tic();
            stringC.tic();
        }
        
    }

}
