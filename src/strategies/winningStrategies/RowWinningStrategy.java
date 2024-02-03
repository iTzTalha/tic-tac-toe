package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    Map<Integer, HashMap<Symbol, Integer>> rowMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        // | X | X | X | - > {X - 3, O - 0}

        int row = move.getCell().getRow();

        if (!rowMap.containsKey(row)) {
            rowMap.put(row, new HashMap<>());
        }

        HashMap<Symbol, Integer> symbolMap = rowMap.get(row);

        Symbol symbol = move.getCell().getPlayer().getSymbol();

        symbolMap.put(symbol, symbolMap.getOrDefault(symbol, 0) + 1);

        return symbolMap.get(symbol) == board.getSize();
    }

    @Override
    public void undo(Board board, Move move) {
        int row = move.getCell().getRow();
        HashMap<Symbol, Integer> symbolMap = rowMap.get(row);
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        symbolMap.put(symbol, symbolMap.get(symbol) - 1);
    }
}
