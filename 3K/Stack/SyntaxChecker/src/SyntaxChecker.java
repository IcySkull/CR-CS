//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class SyntaxChecker
{
	private String exp;
	private Stack<Character> symbols;
	private String state;
	private final Set<BoundedSymbol> boundedSymbols = new TreeSet<>(List.of(
		new BoundedSymbol('(', ')'),
		new BoundedSymbol('[', ']'),
		new BoundedSymbol('{', '}'),
		new BoundedSymbol('<', '>')
	));

	public SyntaxChecker()
	{
		symbols = new Stack<>();
	}

	public SyntaxChecker(String s)
	{
		this();
		this.exp = s;
		setExpression();
		state = checkExpression() ? "valid" : "invalidad";
	}
	
	private void setExpression()
	{
		exp.chars().forEach( c -> {
			Character ch = 
				symbols.add(Character.valueOf((char) c));
			}
		);
	}

	public boolean checkExpression()
	{
		return false;
	}

	@Override
	public String toString() {
		return exp + " is " + state;
	}
}