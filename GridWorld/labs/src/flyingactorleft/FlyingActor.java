package flyingactorleft;
//Name - Diego Rodrigues Rodriguez
//Date - 03/05/22
//Class - Ap CS
//Lab  - Move Left Actor

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

public class FlyingActor extends Actor {
    Color color;
    int x, y, dx, dy;

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        FlyingActor actor = new FlyingActor(315);
        world.add(actor);
        world.show();
    }

    public FlyingActor(int direction) {
        color = new Color(232, 164, 0);
        setColor(color);
        setDirection(direction);
    }

    @Override
    public void act() {
        int dx = getDisplacement()[0];
        int dy = getDisplacement()[1];
        int x = this.getLocation().getCol();
        int y = this.getLocation().getRow();
        if (notOutBound()) {
            this.moveTo(new Location(y+dy, x+dx));
        }
    }

    public boolean notOutBound() {
        int dx = getDisplacement()[0];
        int dy = getDisplacement()[1];
        Grid gr = getGrid();
        int x = this.getLocation().getCol();
        int y = this.getLocation().getRow();
        return x+dx >= 0 && x+dx < gr.getNumCols() && y+dy >= 0 && y+dy < gr.getNumRows();
    }

    public int[] getDisplacement() {
        int dlor = this.getDirection();
        if (dlor == 0)
            return new int[]{0, -1};
        else if (dlor == 45)
            return new int[]{1, -1};
        else if (dlor == 90)
            return new int[]{1, 0};
        else if (dlor == 135)
            return new int[]{1, 1};
        else if (dlor == 180)
            return new int[]{0, 1};
        else if (dlor == 225)
            return new int[]{-1, 1};
        else if (dlor == 270)
            return new int[]{-1, 0};
        else if (dlor == 315)
            return new int[]{-1, -1};
        return new int[]{0,0};
    }
}
