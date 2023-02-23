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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Scanner;
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
		return check(first, second, new TreeMap<>(map));
	}

	public int check(String first, String second, Map<String, String> currMap) {
		String connections = currMap.remove(first);
		if (connections == null)
			return 0;
		if (connections.contains(second))
			return 1;
		int min = Arrays.stream(connections.split(""))
			.mapToInt(s -> {
				return check(s, second, new TreeMap<>(currMap));
			})
			.filter(i -> i > 0)
			.min()
			.orElse(0);
		return 1 + min;
	}


	public String toString()
	{
		return "";
	}
}