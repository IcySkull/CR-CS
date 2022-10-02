import java.util.*;
import java.util.stream.Collectors;

public class RuleParser {
    private List<TuppleRule> rules;

    /**
     * Constructs a RuleParser from the given list of BNF rules. The BNF list of Raw Rules is unmodified.
     * 
     * Safety: creating a rule parser throws and exception if the bnf list of raw rules: 
     *              ~ contains a non-terminal empty symbol rule.
     *              ~ contains a non-terminal with no rules.
     *              ~ contains a rule without symbols.
     *              ~ does not follow the BNF format.
     * @param bnf the list of raw BNF rules read from a file.
     */
    public RuleParser(List<String> bnf) {
        if (bnf.isEmpty()) {
            throw new IllegalArgumentException("Grammar must have at least one rule.");
        }
        rules = bnf.stream()
                .map(this::parseRawRuleLine)
                .collect(Collectors.toList());
    }

    /**
     * Parses a raw rule line into a TuppleRule  that stores the data of the line. A raw rule line that contains a 
     * correct format BNF rule withing the string of the line.
     * @param rawRuleLine the raw rule line to parse.
     * @return the TuppleRule that stores the non-terminal and its list of rules.
     */
    protected TuppleRule parseRawRuleLine(String rawRuleLine) {
        String[] parts = rawRuleLine.split("::=");
        // whitespace for the grammar is unncesessary and might produce errors
        parts[0] = parts[0].trim();
        parts[1] = parts[1].trim();
        if (parts.length != 2) {
            throw new IllegalArgumentException("Raw Rule Line must have one '::='");
        }
        else if (parts[0].length() == 0) {
            throw new IllegalArgumentException("Non-terminal symbol must be non-empty.");
        }
        else if (parts[1].length() == 0) {
            throw new IllegalArgumentException("Rule must have at least one symbol.");
        }
        return new TuppleRule(
            parts[0], // get the non-terminal symbol and remove unnecessary spaces
            parseRawRules(parts[1]) // get the Set of rules for the non-terminal symbol
        );
    }

    /**
     * Helper method of the parseRawRuleLine method; Which, parses the raw rules portion of the raw BNF line
     * into a List of correct gramatical rules present in the non-terminal.
     * @param rawRules
     * @return
     */
    protected List<List<String>> parseRawRules(String rawRules) {
        if (rawRules.isEmpty()) {
            throw new IllegalArgumentException("non-terminal must have at least one rule");
        }
        String[] rawRulesSplit = rawRules.split("\\|");
        return Arrays.asList(rawRulesSplit).stream() // iterate over the rules splitted by '|'
            .map(String::trim) // remove unnecessary spaces
            .map(rule -> rule.split("\\s+")) // split by whitespace to get the specific rule symbols
            .map(Arrays::asList)
            .collect(Collectors.toList()); // convert the array of symbols to a list
    }

    /**
     * Returns the list of TuppleRules that stores the non-terminal symbol and its list of rules.
     * @return
     */
    public List<TuppleRule> getRules() {
        return rules;
    }
}
