package sptoen;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class SpanishToEnglish
{
	private Map<String,String> pairs;
	private List<List<String>> lines;

	public SpanishToEnglish()
	{
		pairs = new HashMap<>();
		lines = new ArrayList<>();
	}

	public void setLines(List<List<String>> lines) {
		this.lines = lines;
	}

	public List<List<String>> getLines() {
		return lines;
	}

	public String getTranslated(String key) {
		return pairs.get(key);
	}

	public void putEntry(String key, String value)
	{
		pairs.put(key, value);
	}

	public List<List<String>> translateLines() {
		List<List<String>> translated = new ArrayList<>();

		translated = lines.stream().map(nLines -> {
			return nLines.stream().map(
				word -> {
					return getTranslated(word);
				}
			).collect(Collectors.toList());
		}).collect(Collectors.toList());

		return translated;
	}

	public String toString()
	{
		return pairs.toString().replaceAll("\\,","\n");
	}

	public static SpanishToEnglish createDictionary(Scanner in) {
		SpanishToEnglish dictionary = new SpanishToEnglish();
		int numWords = Integer.parseInt(in.nextLine());
		while (numWords != 0){
			String[] line = in.nextLine().split(" ");
			dictionary.putEntry(line[0], line[1]);
			numWords--;
		}

		List<List<String>> lines = new ArrayList<>();
		while (in.hasNextLine()) {
			lines.add(Arrays.asList(in.nextLine().split(" ")));
		}
		dictionary.setLines(lines);

		in.close();
		return dictionary;
	}
}