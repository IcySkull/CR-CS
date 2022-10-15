//� A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -  
//Class -
//Lab  -

import java.util.List;
import java.util.Stack;
import static java.lang.System.*;

public class SyntaxCheckRunner
{
	public static void main ( String[] args )
	{
		List<String> exps = List.of(
			"(abc(*def)",
			"[{}]",
			"[",
			"[{<()>}]",
			"{<html[value=4]*(12)>{$x}}",
			"[one]<two>{three}(four)",
			"car(cdr(a)(b)))",
			"car(cdr(a)(b))"
		);

		for (String exp : exps) {
			System.out.println(new SyntaxChecker(exp));
		}
	}
}