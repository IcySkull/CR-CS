//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Graph
{
	private Map<String, String> map;
	private boolean yahOrNay;

	public Graph(String line)
	{ 
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
	}

	public boolean contains(String letter)
	{
	   return map.containsKey(letter);
	}

	public boolean check(String first, String second)
	{
		String connections = map.remove(first);
		if (connections == null) return false;
		return connections.contains(second) || 
			connections.chars()
				.anyMatch(node -> check(""+(char)node, second));
	}

	public String toString()
	{
		return "";
	}
}