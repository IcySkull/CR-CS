package actortriangle;//© A+ Computer Science  -  www.apluscompsci.com
//Name - Diego Rodrigues Rodriguez
//Date - 03/05/22
//Class - Ap CS
//Lab  - Actor Triangle


import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;

public class ActorTriangle
{
	public static void main(String[] args)
	{
		BoundedGrid grid = new BoundedGrid(10, 10);
		ActorWorld world = new ActorWorld(grid);
		world.show();
		Grid gr = world.getGrid();
		int cols = gr.getNumCols();
		int rows = gr.getNumRows()-1;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j <cols-cols%3; j++) {
				if ((cols-cols%3)/2 - i == j || (cols-cols%3)/2 + i == j || i == rows/2)
					world.add(new Location(i, j), new Actor());
			}
		}
	}
}