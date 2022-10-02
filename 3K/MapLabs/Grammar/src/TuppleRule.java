import java.util.*;

public class TuppleRule {
    private String nonTerminalSymbol;
    private List<List<String>> rules;

    public TuppleRule(String nonTerminal, List<List<String>> rules) {
        if (nonTerminal == null || nonTerminal.length() == 0) {
            throw new IllegalArgumentException("Non-terminal must be non-empty.");
        }
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("Non-terminal must have at least one rule.");
        }
        this.nonTerminalSymbol = nonTerminal;
        this.rules = rules;
    }

    public String getNonTerminalSymbol() {
        return nonTerminalSymbol;
    }

    public List<List<String>> getRules() {
        return rules;
    }
}
