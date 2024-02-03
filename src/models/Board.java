package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int size;
    private final List<List<Cell>> board;

    public Board(int size) {
        this.size = size;

        board = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void displayBoar() {
        for (List<Cell> row : this.board) {
            for (Cell cell : row) {
                cell.display(board.size());
            }
            System.out.println();
        }
    }
}
