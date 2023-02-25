//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.*;

public class ShortestPathMaze
{
   private int[][] maze;

	public ShortestPathMaze()
	{
	}

	public ShortestPathMaze(int[][] m)
	{
		maze = m;
	}

	public int shortest(Location start)
	{
		List<Location> path = getShortestPath(start, new HashSet<>());
		for (Location loc : path) {
			maze[loc.row][loc.col] = 9;
		}
		return path.size();
	}
	
	public List<Location> getShortestPathSlow(Location start)
	{
		return getShortestPath(start, new HashSet<>());
	}

	public List<Location> getShortestPath(Location start, HashSet<Location> visited) {
		if (visited.contains(start))
			return new ArrayList<>();
		if (exitReached(start))
			return new ArrayList<>(Arrays.asList(start));

		visited.add(start);
		List<Location> adj = getAdjacent(start);

		List<Location> path = adj.stream()
			.map(loc -> {
				HashSet<Location> visitedCopy = new HashSet<>(visited);
				return getShortestPath(loc, visitedCopy);
			})
			.filter(paths -> paths.size() > 0)
			.min(Comparator.comparingInt(List::size))
			.orElse(new ArrayList<>());
		if (path.size() > 0)
			path.add(0, start);
		return path;
		
	}

	public boolean exitReached(Location loc) {
		return loc.col == maze[0].length-1;
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