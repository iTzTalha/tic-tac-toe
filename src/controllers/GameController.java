package controllers;

import enums.GameState;
import exceptions.DuplicateSymbolException;
import exceptions.InvalidPlayerCountException;
import models.Game;
import models.Player;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimensions, List<Player> players, List<WinningStrategy> winningStrategies) {
        try {
            return Game.getBuilder()
                    .setDimensions(dimensions)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
        } catch (DuplicateSymbolException | InvalidPlayerCountException e) {
            throw new RuntimeException(e);
        }
    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undo(Game game) {
        game.undo();
    }
}
