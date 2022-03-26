package rockdroppingactor;

import flyingactorleft.FlyingActor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.*;


class RockDroppingActor extends FlyingActor {
    Location loc;
    ActorWorld world;

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new RockDroppingActor(45, world));
        world.show();
    }

    RockDroppingActor(int direction, ActorWorld world) {
        super(direction);
        this.world = world;
        this.setColor(Color.green);
    }

    @Override
    public void act() {
        if (notOutBound()) {
            super.act();
            Location loc = getLocation();
            int dx = getDisplacement()[0];
            int dy = getDisplacement()[1];
            Location previusLoc = new Location(loc.getRow() - dy, loc.getCol() - dx);
            Rock rock = new Rock();
            rock.setColor(Color.black);
            world.add(rock);
            rock.moveTo(previusLoc);
        }
    }
}
