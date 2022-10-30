package musicPrimitives.instruments;
import java.util.ArrayList;
import java.util.List;

public enum Instruments {
    GUITAR(new double[]{
        1, 0.5, 0.3, 0.2, 0, 0.1, 0.3, 0.5, 0.9, 0.7, 0.2, 0.3, 0.3, 0.2, 0, 0.3, 0.7, 0.1,
    }),
    XYLOPHONE(new double[]{
        0.1, 0, 0, 0, 0, 0, 0, 0, 0.2, 1.9, 0.2, 0, 0, 0, 0, 0, 0
    }),
    ELECTRIC_PIANO(new double[]{
         0.5, 1, 0.5, 0, 0, 0, 0.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.2, 0.5
    }),
    STRING(new double[]{3, 0, 0, 0, 0, 2, 1});

    private double[] harmonics;

    Instruments(double[] harmonicsAmpList) {
        this.harmonics = harmonicsAmpList;
    }
    
    Instruments() {
        this.harmonics = new double[]{1};
    }

    public double[] getHarmonicsAmpList() {
        return harmonics;
    }
}
