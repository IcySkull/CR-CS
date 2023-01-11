import java.util.Hashtable;

public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
		//TODO Initialize the hash tables 
		ChainingHash shakesTable1 = new ChainingHash();
		QPHash baconTable1 = new QPHash();
		
		//TODO Use the FileInput functions to read the two files.
		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		FileInput.init();
		String[] shakes = FileInput.readShakespeare();
		for (String word : shakes) {
			shakesTable1.insert(word);
		}

		String[] bacon = FileInput.readBacon();
		for (String word : bacon) {
			baconTable1.insert(word);
		}

		float totalSquaredError = 0;
		String mostDistantWord = "";
		float mostDistant = 0;

		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
		for (String shakesWord : shakesTable1) {
			float shakesFrequency = ((float)shakesTable1.findCount(shakesWord)) / shakes.length;
			if (baconTable1.findCount(shakesWord) == 0) {
				totalSquaredError += Math.pow(shakesFrequency, 2);
				if (shakesFrequency > mostDistant) {
					mostDistant = shakesFrequency;
					mostDistantWord = shakesWord;
				}
			} else {
				float baconFrequency = ((float)baconTable1.findCount(shakesWord)) / bacon.length;
				float difference = Math.abs(shakesFrequency - baconFrequency);
				totalSquaredError += Math.pow(difference, 2);
				if (difference > mostDistant) {
					mostDistant = difference;
					mostDistantWord = shakesWord;
				}
			}
		}
		
		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total
		for (String baconWord : baconTable1) {
			float baconFrequency = ((float)baconTable1.findCount(baconWord)) / bacon.length;
			if (shakesTable1.findCount(baconWord) == 0) {
				totalSquaredError += Math.pow(baconFrequency, 2);
				if (baconFrequency > mostDistant) {
					mostDistant = baconFrequency;
					mostDistantWord = baconWord;
				}
			}
		}
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
		System.out.println("Diego Hastable");
		System.out.println("Total squared error: " + totalSquaredError);
		System.out.println("Most distant word: " + mostDistantWord + " with a distance of " + mostDistant);

		System.out.println(mostDistantWord +" in shakespeare " + shakesTable1.get(mostDistantWord));
		System.out.println(mostDistantWord + " in bacon " + baconTable1.get(mostDistantWord));

		System.out.println();
		System.out.println("Oracle Hashtable");
		Hashtable<String, Integer> shakesTable2 = new Hashtable();
		Hashtable<String, Integer> baconTable2 = new Hashtable();

		for (String word : shakes) {
			if (shakesTable2.get(word) == null)
				shakesTable2.put(word, 1);
			else
				shakesTable2.put(word, shakesTable2.get(word) + 1);
		}

		for (String word : bacon) {
			if (baconTable2.get(word) == null)
				baconTable2.put(word, 1);
			else
				baconTable2.put(word, baconTable2.get(word) + 1);
		}

		totalSquaredError = 0;
		mostDistantWord = "";
		mostDistant = 0;

		for (String shakesWord : shakesTable2.keySet()) {
			float shakesFrequency = ((float)shakesTable2.get(shakesWord)) / shakes.length;
			if (baconTable2.get(shakesWord) == null) {
				totalSquaredError += Math.pow(shakesFrequency, 2);
				if (shakesFrequency > mostDistant) {
					mostDistant = shakesFrequency;
					mostDistantWord = shakesWord;
				}
			} else {
				float baconFrequency = ((float)baconTable2.get(shakesWord)) / bacon.length;
				float difference = Math.abs(shakesFrequency - baconFrequency);
				totalSquaredError += Math.pow(difference, 2);
				if (difference > mostDistant) {
					mostDistant = difference;
					mostDistantWord = shakesWord;
				}
			}
		}

		for (String baconWord : baconTable2.keySet()) {
			float baconFrequency = ((float)baconTable2.get(baconWord)) / bacon.length;
			if (shakesTable2.get(baconWord) == null) {
				totalSquaredError += Math.pow(baconFrequency, 2);
				if (baconFrequency > mostDistant) {
					mostDistant = baconFrequency;
					mostDistantWord = baconWord;
				}
			}
		}

		System.out.println("Total squared error: " + totalSquaredError);
		System.out.println("Most distant word: " + mostDistantWord + " with a distance of " + mostDistant);

		System.out.println(mostDistantWord +" in shakespeare " + shakesTable2.get(mostDistantWord));
		System.out.println(mostDistantWord + " in bacon " + baconTable2.get(mostDistantWord));
	}


}
