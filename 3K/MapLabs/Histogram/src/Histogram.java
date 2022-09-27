package histogram;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.*;
import static java.lang.System.*;

public class Histogram
{
	private Map<String,Integer> histogram;

	public Histogram()
	{

	}

	public Histogram(File data)
	{
		BufferedReader bf = new BufferedReader(new FileReader(data));
		histogram = new HashMap();
	}

	public void setSentence(String sent)
	{

	}

	public String toString()
	{
		String output="";
		String allStars="";
		return output+"\n\n";
	}
}