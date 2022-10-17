import java.util.*;
import java.awt.image.BufferedImage;

public class BufferedImageStack {
    private BufferedImage[] ary;
    private int index;

    public BufferedImageStack() {
        ary = new BufferedImage[2];
        index = -1;
    }

    public void push(BufferedImage action) {
        if (index+1 == ary.length)
            ary = allocateFull(ary);
        ary[++index] = action;
    }

    public BufferedImage pop() {
        if (index == -1)
            throw new EmptyStackException();
        BufferedImage displaced = ary[index];
        ary[index--] = null;
        return displaced;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public int getSize() {
        return index+1;
    }

    public int getArraySize() {
        return ary.length;
    }

    protected BufferedImage[] allocateFull(BufferedImage[] old) {
        BufferedImage[] newAry = new BufferedImage[old.length*2];
        for (int i = 0; i < old.length; i++) {
            newAry[i] = old[i];
        }
        return newAry;
    }
}
