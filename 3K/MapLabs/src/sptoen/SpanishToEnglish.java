package sptoen;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.util.*;

public class SpanishToEnglish
{
	private Map<String,String> pairs;
	private List<String> lines;

	public SpanishToEnglish()
	{
		pairs = new HashMap<>();
		lines = new ArrayList<>();
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public void putEntry(String key, String value)
	{
		pairs.put(key, value);
	}

	public String translate(String sent)
	{
		Scanner chop = new Scanner(sent);
		String output ="";
		while (chop.hasNext()) {
			output += pairs.get(chop.next()) + " ";
		}

		return output;
	}

	public List<String> getTranslatedWords(String )

	public List<String> translateLines() {
		List<String> translated = new ArrayList<>();
		for (String str : lines) {
			Scanner chop = new Scanner(str);
			while (chop.hasNext())

		}
	}

	public String toString()
	{
		return pairs.toString().replaceAll("\\,","\n");
	}
}