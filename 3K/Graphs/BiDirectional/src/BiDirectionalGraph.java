//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class BiDirectionalGraph
{
	private TreeMap<String, TreeSet<String>> map;

	public BiDirectionalGraph(String line)
	{
		line = line.trim();
		map = new TreeMap<>();
		for (String[] edge : getEdges(line.split(" ")))
		{
			map.merge(edge[0], new TreeSet<>(Arrays.asList(edge[1])), (a, b) -> {a.addAll(b); return a;});
			map.merge(edge[1], new TreeSet<>(Arrays.asList(edge[0])), (a, b) -> {a.addAll(b); return a;});
		}
	}

	public List<String[]> getEdges(String[] names)
	{
		List<String[]> edges = new ArrayList<>();
		for (int i = 0; i < names.length; i+=2)
		{
			edges.add(new String[] {names[i], names[i+1]});
		}
		return edges;
	}

	public boolean contains(String name)
	{
		return map.containsKey(name);
	}

	public boolean check(String first, String second, TreeSet<String> placedUsed)
	{
		if (placedUsed.contains(first))
			return false;
		placedUsed.add(first);
		TreeSet<String> edges = map.get(first);
		return edges != null 
			&& (edges.contains(second) || edges.stream().anyMatch(s -> check(s, second, placedUsed)));
	}

	public String toString()
	{
		return "";
	}
}