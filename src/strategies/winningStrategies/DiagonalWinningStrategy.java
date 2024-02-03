package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    Map<Symbol, Integer> leftDiagonalMap = new HashMap<>();
    Map<Symbol, Integer> rightDiagonalMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getCell().getPlayer().getSymbol();

        //Left Diagonal
        if (row == col) {
            leftDiagonalMap.put(symbol, leftDiagonalMap.getOrDefault(symbol, 0) + 1);
            return leftDiagonalMap.get(symbol) == board.getSize();
        }

        //Right Diagonal
        if (row + col == board.getSize() - 1) {
            rightDiagonalMap.put(symbol, rightDiagonalMap.getOrDefault(symbol, 0) + 1);
            return rightDiagonalMap.get(symbol) == board.getSize();
        }

        return false;
    }

    @Override
    public void undo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getCell().getPlayer().getSymbol();

        //Left Diagonal
        if (row == col) {
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol) - 1);
        }

        //Right Diagonal
        if (row + col == board.getSize() - 1) {
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol) - 1);
        }
    }
}
