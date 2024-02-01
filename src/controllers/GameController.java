package controllers;

import exceptions.DuplicateSymbolException;
import exceptions.InvalidPlayerCountException;
import models.Game;
import models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int dimensions, List<Player> players) {
        try {
            return Game.getBuilder().setDimensions(dimensions).setPlayers(players).build();
        } catch (DuplicateSymbolException e) {
            throw new RuntimeException(e);
        } catch (InvalidPlayerCountException e) {
            throw new RuntimeException(e);
        }
    }
}
