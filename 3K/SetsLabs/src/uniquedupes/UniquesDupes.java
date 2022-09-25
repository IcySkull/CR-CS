package uniquedupes;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -  

import java.util.*;
import java.util.stream.Collectors;

public class UniquesDupes
{

	public static Set<String> getUniques(List<String> inputList) {
		return new TreeSet<>(inputList);
	}
	public static Set<String> getUniques(String input)
	{
		return getUniques(Arrays.stream(input.split(" ")).toList());
	}

	public static Set<String> getDupes(String input) {
		List<String> inputAry = List.of(input.split(" "));
		Set<String> uniques = getUniques(input);

		return inputAry.stream().filter(s -> {
			if (uniques.contains(s)) {
				uniques.remove(s);
				return false;
			}
			return true;
		}).collect(Collectors.toSet());
	}

	public static int countOcurrences(String word, String[] input) {
		int occur = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i].equals(word))
				occur++;
		}
		return occur;
	}
}