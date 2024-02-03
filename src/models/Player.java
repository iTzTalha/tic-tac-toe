package models;

import java.util.Scanner;

public class Player {
    private final int id;
    private final String name;
    private final Symbol symbol;
    private final Scanner scanner;

    public Player(int id, String name, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Move makeMove() {
        System.out.print("Enter the row - ");
        int row = scanner.nextInt();
        System.out.print("Enter the col - ");
        int col = scanner.nextInt();
        return new Move(new Cell(row, col));
    }
}
