package models;

import enums.GameState;
import exceptions.DuplicateSymbolException;
import exceptions.InvalidPlayerCountException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayer;
    private Player winner;
    private GameState gameState;

    private Game(int dimensions, List<Player> players) {
        this.board = new Board(dimensions);
        this.players = players;
        this.moves = new ArrayList<>();
        nextPlayer = 0;
        winner = null;
        gameState = GameState.IN_PROGRESS;
    }

    public static class Builder {
        private int dimensions;
        private List<Player> players;

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() throws DuplicateSymbolException, InvalidPlayerCountException {
            //Validations
            validatePlayerCount();
            validateDuplicateSymbols();

            return new Game(this.dimensions, this.players);
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if (this.players != null && !this.players.isEmpty() && this.players.size() <= this.dimensions)
                return;
            throw new InvalidPlayerCountException();
        }

        private void validateDuplicateSymbols() throws DuplicateSymbolException {
            Set<Symbol> set = new HashSet<>();
            for (Player player : this.players) {
                if (!set.add(player.getSymbol())) {
                    throw new DuplicateSymbolException();
                }
            }
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
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
}