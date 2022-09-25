package uniondiff;//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -  

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class MathSet
{
	private Set<Integer> one;
	private Set<Integer> two;

	public MathSet()
	{
	}

	public MathSet(List<Integer> ls1, List<Integer> ls2)
	{
		one = new TreeSet<>(ls1);
		two = new TreeSet<>(ls2);
	}

	public MathSet(Set<Integer> one, Set<Integer> two) {
		this.one = one;
		this.two = two;
	}

	public Set<Integer> union()
	{
		Set<Integer> outSet = new TreeSet<>(one);
		outSet.addAll(two);
		return outSet;
	}

	public Set<Integer> intersection()
	{
		return one.stream().filter(integer -> two.contains(integer)).collect(Collectors.toSet());
	}

	public Set<Integer> differenceAMinusB()
	{
		return one.stream().filter(integer -> !two.contains(integer)).collect(Collectors.toSet());
	}

	public Set<Integer> differenceBMinusA()
	{
		return two.stream().filter(integer -> !one.contains(integer)).collect(Collectors.toSet());
	}
	
	public Set<Integer> symmetricDifference()
	{
		Set<Integer> minus = intersection();
		Set<Integer> union = union();
		MathSet outSet = new MathSet(union, minus);
		return outSet.differenceAMinusB();
	}

	public Set<Integer> getOne() {
		return one;
	}

	public Set<Integer> getTwo() {
		return two;
	}

	public String toString()
	{
		return "Set one " + one + "\n" +	"Set two " + two +  "\n";
	}
}