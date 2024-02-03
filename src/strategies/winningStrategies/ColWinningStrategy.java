package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {
    Map<Integer, HashMap<Symbol, Integer>> colMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        // | X |  - > {X - 1, O - 0}
        // | X |  - > {X - 2, O - 0}
        // | X |  - > {X - 3, O - 0}

        int col = move.getCell().getCol();

        if (!colMap.containsKey(col)) {
            colMap.put(col, new HashMap<>());
        }

        HashMap<Symbol, Integer> symbolMap = colMap.get(col);

        Symbol symbol = move.getCell().getPlayer().getSymbol();

        symbolMap.put(symbol, symbolMap.getOrDefault(symbol, 0) + 1);

        return symbolMap.get(symbol) == board.getSize();
    }

    @Override
    public void undo(Board board, Move move) {
        int col = move.getCell().getCol();
        HashMap<Symbol, Integer> symbolMap = colMap.get(col);
        Symbol symbol = move.getCell().getPlayer().getSymbol();
        symbolMap.put(symbol, symbolMap.get(symbol) - 1);
    }
}
