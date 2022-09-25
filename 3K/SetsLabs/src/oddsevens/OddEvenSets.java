package oddsevens;//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.util.*;
import java.util.stream.Collectors;

public class OddEvenSets
{
	private Set<Integer> odds;
	private Set<Integer> evens;

	public OddEvenSets(List<Integer> nums)
	{
		odds = nums.stream().filter(integer -> integer % 2 == 1).collect(Collectors.toSet());
		evens = nums.stream().filter(integer -> integer % 2 == 0).collect(Collectors.toSet());
	}

	public String toString()
	{
		return "ODDS : " + odds + "\nEVENS : " + evens + "\n\n";
	}
}