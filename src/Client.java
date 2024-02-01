import controllers.GameController;
import models.Game;
import models.Player;

import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        int dimensions = 3;
        List<Player> players = Arrays.asList(
                new Player(),
                new Player()
        );

        Game game = gameController.startGame(dimensions, players);
    }
}
