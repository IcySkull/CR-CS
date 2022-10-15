//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.util.Stack;

import java.util.*;;

public class PostFix
{
	private Stack<Double> numbers;
	private String expression;
	private double res;
	private final Map<Character, MathSymbol> symbols = Map.ofEntries(
		Map.entry('1', new Number(1)),
		Map.entry('2', new Number(2)),
		Map.entry('3', new Number(3)),
		Map.entry('4', new Number(4)),
		Map.entry('5', new Number(5)),
		Map.entry('6', new Number(6)),
		Map.entry('7', new Number(7)),
		Map.entry('8', new Number(8)),
		Map.entry('9', new Number(9)),

		Map.entry('+', new Operator('+')),
		Map.entry('-', new Operator('-')),
		Map.entry('*', new Operator('*')),
		Map.entry('/', new Operator('/'))
	);

	public PostFix()
	{
		numbers = new Stack<>();
	}

	public PostFix(String exp) {
		this();
		setExpression(exp);
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public double calc(char op) {
		double two = numbers.pop();
		double one = numbers.pop();
		switch(op) {
			case '+':
				return one + two;
			case '-':
				return one - two;
			case '*':
				return one * two;
			case '/':
				return one / two;
		}
		return 0.0;
	}

	public void clearState() {
		res = 0;
		numbers = new Stack<>();
	}

	public void solve() {
		clearState();
		expression.chars().forEach(c -> {
			Character symbol = Character.valueOf((char) c);
			MathSymbol s = symbols.get(symbol);
			if (s instanceof Number n)
				numbers.push(n.n);
			else if (s instanceof Operator op) {
				numbers.push(calc(op.op));
			}
		});
		res = numbers.pop();
	}

	@Override
	public String toString() {
		return expression + " = " + res;
	}
}