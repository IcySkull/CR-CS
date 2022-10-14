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
	private Stack<char> symbols;
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
	}
	
	public void setExpression(String s)
	{
		s.chars().forEach( c -> symbols.add());
	}

	public boolean checkExpression()
	{
		return false;
	}

	@Override
	public String toString() {
		return exp;
	}
}