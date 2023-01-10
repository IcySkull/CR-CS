//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class - 
//Lab  -

public class Word
{

    private String theValue;
	
	public Word(String word) {
		theValue = word;
    }
	
	public String getWord() {
		return theValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Word) {
			Word other = (Word) obj;
			return theValue.equals(other.theValue);
		}
		return false;
	}
	
	public int hashCode() {
		int letters = 0;
		int vowels = 0;
		for (char c : theValue.toCharArray()) {
			letters++;
			if (isVowel(c))
				vowels++;
		}
		return vowels * letters % 10;
	}

	private boolean isVowel(char c) {
		return "aeiou".indexOf(c) != -1;
	}

	@Override
	public String toString() {
		return theValue;
	}
	
}