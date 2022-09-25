package actorbox;//© A+ Computer Science  -  www.apluscompsci.com
//Name - Diego Rodrigues Rodriguez
//Date - 03/05/22
//Class - Ap CS
//Lab  - Actor Box

import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import java.util.ArrayList;

public class ActorBox
{
	ActorWorld world;
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		new ActorBox(world);
		world.show();
	}

	ActorBox (ActorWorld world) {
		this.world = world;
		this.world.add(new Location(5, 5), new Rock());
		traceBox();
	}

	public void traceBox() {
		int numRows = world.getGrid().getNumRows();
		int numCols = world.getGrid().getNumCols();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (i == 0 || j == 0 ||
					i == numRows-1 || j == numCols-1)
					world.add(new Location(i, j), new Actor());
				else if (j == 0)
					world.add(new Location(i, j), new Actor());
			}
		}
	}
}