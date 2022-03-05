package actorutils;

import info.gridworld.actor.Actor;

public class ActorUtils {
    static int[] getDisplacement(Actor actor) {
        int dlor = actor.getDirection() / 90;
        if (dlor == 0)
            return new int[]{0, 1};
        else if (dlor == 45)
            return new int[]{1, 1};
        else if (dlor == 180)
            return new int[]{0, -1};
        else if (dlor == 270)
            return new int[]{-1, 0};
        return new int[]{0,0};
    }
}
