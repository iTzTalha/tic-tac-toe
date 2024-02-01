package models;

import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int dimensions) {
        this.size = dimensions;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
