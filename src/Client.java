import controllers.GameController;
import enums.GameState;
import models.Game;
import models.Player;
import models.Symbol;
import strategies.winningStrategies.ColWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Xx-TicTacToe-xX");

        System.out.print("Enter Grid size - ");
        int dimensions = scanner.nextInt();

        System.out.print("Enter Total Players - ");
        int totalPlayers = scanner.nextInt();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < totalPlayers; i++) {
            scanner.nextLine();
            System.out.print("Enter Player " + (i + 1) + " name - ");
            String name = scanner.nextLine();
            System.out.print("Enter Player " + (i + 1) + " symbol - ");
            char symbol = scanner.next().charAt(0);
            players.add(new Player(i, name, new Symbol(symbol)));
        }

        List<WinningStrategy> winningStrategies = Arrays.asList(new RowWinningStrategy(), new ColWinningStrategy(), new DiagonalWinningStrategy());

        try {
            Game game = gameController.startGame(dimensions, players, winningStrategies);

            gameController.displayBoard(game);

            while (gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
                gameController.makeMove(game);
                System.out.println("Do you want to UNDO? (Y/N)?");
                String undo = scanner.next();
                if (undo.equalsIgnoreCase("Y")) {
                    gameController.undo(game);
                }
                gameController.displayBoard(game);
            }

            System.out.println("The Game is finished!");

            if (gameController.checkState(game).equals(GameState.WON)) {
                System.out.println("The Winner is " + game.getWinner().getName());
            } else {
                System.out.println("DRAW!!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
