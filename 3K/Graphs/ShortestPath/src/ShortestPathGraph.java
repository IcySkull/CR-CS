//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.*;

public class ShortestPathGraph
{
	private Map<String, String> map;
	private boolean yayOrNay;
	private int shortest;

	public ShortestPathGraph(String line) {
		line = line.trim();
		String reversed = (new StringBuilder(line)).reverse().toString();
		line = line + " " + reversed;
		map = Arrays.stream(line.split(" "))
			.collect(
				Collectors.toMap(
					s -> ""+s.charAt(0),
					s -> ""+s.charAt(1),
					(String a, String b) -> a + b,
					TreeMap::new
				)
		);
		System.out.println(map);
	}

	public boolean contains(String letter)
	{
		return map.containsKey(letter);
	}

	public int check(String first, String second) {
		return check(first, second, 0, new TreeSet<>());
	}

	public int check(String first, String second, int steps, Set<String> visited) {
		if (visited.contains(first))	
			return 0;

		String connections = map.get(first);
		if (connections == null)
			return 0;
		if (connections.contains(second))
			return steps+1;

		visited.add(first);
		return Arrays.stream(connections.split(""))
			.mapToInt(edge -> check(edge, second, steps+1, new TreeSet<>(visited)))
			.filter(steps1 -> steps1 > 0)
			.min()
			.orElse(0);
	}


	public String toString()
	{
		return "";
	}
}