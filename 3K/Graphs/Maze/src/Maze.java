//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.*;

public class Maze
{
   private int[][] maze;

	public Maze()
	{
	}

	public Maze(int[][] m)
	{
		maze = m;
	}

	public boolean checkForExitPath(Location start)
	{
		return checkConnected(start, new HashSet<>());
	}

	public boolean checkConnected(Location start, Set<Location> visited) {
		if (visited.contains(start))
			return false;
		if (start.col == maze[0].length-1)
			return true;
		visited.add(start);
		List<Location> adj = getAdjacent(start);
		return adj.stream().anyMatch( loc -> checkConnected(loc, visited));
	}

	public List<Location> getAdjacent(Location loc) {
		List<Location> list = new ArrayList<>();
		Location down = getDown(loc);
		Location up = getUp(loc);
		Location left = getLeft(loc);
		Location right = getRight(loc);

		if (isValidLoc(down))
			list.add(down);		
		if (isValidLoc(up))
			list.add(up);
		if (isValidLoc(left))
			list.add(left);
		if (isValidLoc(right))
			list.add(right);
		
		return list;
	}

	public Location getDown(Location loc) {
		return new Location(loc.row+1, loc.col);
	}

	public Location getUp(Location loc) {
		return new Location(loc.row-1, loc.col);
	}

	public Location getLeft(Location loc) {
		return new Location(loc.row, loc.col-1);
	}

	public Location getRight(Location loc) {
		return new Location(loc.row, loc.col+1);
	}

	public boolean isValidLoc(Location loc) {
		return loc.row < maze.length && loc.row >= 0
			&& loc.col < maze[0].length && loc.col >= 0
			&& maze[loc.row][loc.col] == 1;
	}

	public String toString()
	{
		String output="";
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				output += maze[i][j] + " ";
			}
			if (i != maze.length-1)
				output += "\n";
		}
		return output;
	}
}