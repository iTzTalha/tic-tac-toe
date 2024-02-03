package models;

import enums.CellState;
import enums.GameState;
import exceptions.DuplicateSymbolException;
import exceptions.InvalidPlayerCountException;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final List<Move> moves;
    private int nextPlayer;
    private Player winner;
    private GameState gameState;

    private final List<WinningStrategy> winningStrategies;

    private Game(int dimensions, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimensions);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.moves = new ArrayList<>();
        nextPlayer = 0;
        winner = null;
        gameState = GameState.IN_PROGRESS;
    }

    public static class Builder {
        private int dimensions;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() throws DuplicateSymbolException, InvalidPlayerCountException {
            //Validations
            validatePlayerCount();
            validateDuplicateSymbols();

            return new Game(this.dimensions, this.players, this.winningStrategies);
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if (this.players != null && !this.players.isEmpty() && this.players.size() <= this.dimensions)
                return;
            throw new InvalidPlayerCountException("Total Players should be > 0 and <= Grid size.");
        }

        private void validateDuplicateSymbols() throws DuplicateSymbolException {
            Set<Symbol> set = new HashSet<>();
            for (Player player : this.players) {
                if (!set.add(player.getSymbol())) {
                    throw new DuplicateSymbolException("Symbol " + player.getSymbol().getSymbol() + " has already been selected by other Player.");
                }
            }
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void displayBoard() {
        this.board.displayBoar();
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayer);
        System.out.println("It's " + currentPlayer.getName() + "'s move.");

        Move move = currentPlayer.makeMove();

        while (!validateMove(move)) {
            System.out.println("Invalid move! Please try again.");
            move = currentPlayer.makeMove();
        }

        Cell updatedCell = board.getBoard().get(move.getCell().getRow()).get(move.getCell().getCol());
        updatedCell.setPlayer(currentPlayer);
        updatedCell.setCellState(CellState.FILLED);

        move.setCell(updatedCell);

        moves.add(move);

        nextPlayer += 1;
        nextPlayer %= players.size();

        if (checkWinner(board, move)) {
            setGameState(GameState.WON);
            setWinner(currentPlayer);
        } else if (moves.size() == board.getSize() * board.getSize()) {
            setGameState(GameState.DRAW);
        }
    }

    private boolean checkWinner(Board board, Move move) {
        for (WinningStrategy strategy : winningStrategies) {
            if (strategy.checkWinner(board, move)) {
                return true;
            }
        }

        return false;
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize())
            return false;

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void undo() {
        if (moves.isEmpty()) {
            System.out.println("No moves to UNDO");
            return;
        }

        Move move = moves.getLast();
        moves.remove(move);

        nextPlayer -= 1;
        nextPlayer = (nextPlayer + players.size()) % players.size();

        for (WinningStrategy strategy : winningStrategies) {
            strategy.undo(board, move);
        }

        Cell cell = move.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);
    }
}