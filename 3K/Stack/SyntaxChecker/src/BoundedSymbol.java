

public class BoundedSymbol {
    private char open;
    private char closed;

    public BoundedSymbol(char open, char closed) {
        this.open = open;
        this.closed = closed;
    }

    public char getOpen() {
        return open;
    }

    public char getClose() {
        return closed;
    }

    public boolean isContained(char character) {
        return character == open || character == closed;
    }

    public boolean isOpen(char contained) {
        if (!isContained(contained))
            throw new IllegalStateException("character must be contained ");
        return contained == open ? true : false;
    }

    public char[] getSymbols() {
        return new char[]{open, closed};
    }
    
}
