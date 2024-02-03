package models;

import enums.CellState;

public class Cell {
    private final int row;
    private final int col;
    private CellState cellState;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        cellState = CellState.EMPTY;
        player = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void display(int size) {
        if (col == 0) {
            System.out.print("|" + (player == null ? " - " : " " + player.getSymbol().getSymbol() + " "));
        } else if (col == size - 1) {
            System.out.print((player == null ? " - " : " " + player.getSymbol().getSymbol() + " ") + "|");
        } else {
            System.out.print("|" + (player == null ? " - " : " " + player.getSymbol().getSymbol() + " ") + "|");
        }
    }
}
