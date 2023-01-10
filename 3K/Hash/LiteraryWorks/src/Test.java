public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
		//TODO Initialize the hash tables 
		ChainingHash table1 = new ChainingHash();
		QPHash table2 = new QPHash();
		
		//TODO Use the FileInput functions to read the two files.
		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		FileInput.init();
		String[] shakes = FileInput.readShakespeare();
		for (String word : shakes) {
			table1.insert(word);
		}

		String[] bacon = FileInput.readBacon();
		for (String word : bacon) {
			table2.insert(word);
		}

		float totalSquaredError = 0;
		String mostDistantWord = "";
		float mostDistant = 0;

		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.

		for (WordCount shakesWord : table1) {
			float shakesFrequency = ((float)shakesWord.getCount()) / shakes.length;
			WordCount baconWord = table2.get(shakesWord.getWord());
			if (baconWord == null) {
				totalSquaredError += Math.pow(shakesFrequency, 2);
				if (shakesFrequency > mostDistant) {
					mostDistant = shakesFrequency;
					mostDistantWord = shakesWord.getWord();
				}
			} else {
				float baconFrequency = ((float)baconWord.getCount()) / bacon.length;
				float difference = Math.abs(shakesFrequency - baconFrequency);
				totalSquaredError += Math.pow(difference, 2);
				if (difference > mostDistant) {
					mostDistant = difference;
					mostDistantWord = shakesWord.getWord();
				}
			}
		}
		
		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total
		for (WordCount baconWord : table2) {
			float baconFrequency = ((float)baconWord.getCount()) / bacon.length;
			WordCount shakesWord = table1.get(baconWord.getWord());
			if (shakesWord == null) {
				totalSquaredError += Math.pow(baconFrequency, 2);
				if (baconFrequency > mostDistant) {
					mostDistant = baconFrequency;
					mostDistantWord = baconWord.getWord();
				}
			}
		}
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
		System.out.println("Total squared error: " + totalSquaredError);
		System.out.println("Most distant word: " + mostDistantWord + " with a distance of " + mostDistant);

		System.out.println(mostDistantWord +" in shakespeare " + table1.get(mostDistantWord));
		System.out.println(mostDistantWord + " in bacon " + table2.get(mostDistantWord));
	}

}
