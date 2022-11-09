//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.*;
import static java.lang.System.*;

public class HistoList
{
   private HistoNode front;

	public HistoList( )
	{
		front = null;
	}

	//addLetter will add a new node to the front for let if let does not exist
	//addLetter will bump up the count if let already exists
	public void addLetter(char let)
	{
		HistoNode itr = front;
		while (itr != null) {
			if (itr.getLetter() == let) {
				itr.setLetterCount(itr.getLetterCount() + 1);
				return;
			}
			itr = itr.getNext();
		}
		front = new HistoNode(let, 1, front);
	}

	//returns the index pos of let in the list if let exists
	public int indexOf(char let)
	{
		HistoNode current = front;
		int index = 0;
		do {
			if (current.getLetter() == let) {
				return index;
			}
			current = current.getNext();
		} while (current != null);
		return -1;
	}

	//returns a reference to the node at spot
	private HistoNode nodeAt(int spot)
	{
		HistoNode current = front;
		while (spot-- > 0)
		{
			current = current.getNext();
			spot--;
		}
		return current;
	}

	//returns a string will all values from list
	public String toString()
	{
		String output = "";
		HistoNode itr = front;
		while (itr != null) {
			output += itr.getLetter() + " - " + itr.getLetterCount() + "\t";
			itr = itr.getNext();
		}
		return output;
	}
}