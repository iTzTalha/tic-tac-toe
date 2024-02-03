package models;

public class Symbol {
    private final char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public int hashCode() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        Symbol other = (Symbol) obj;
        if (this == other) return true;
        return this.symbol == other.symbol;
    }
}
