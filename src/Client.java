import controllers.GameController;
import enums.GameState;
import models.Game;
import models.Player;
import models.Symbol;
import strategies.winningStrategies.ColWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.*;

public class Client {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Xx-TicTacToe-xX");

        System.out.print("Enter Grid size - ");
        int dimensions = scanner.nextInt();

        System.out.print("Enter Total Players - ");
        int totalPlayers = scanner.nextInt();

        while (totalPlayers < 0 || totalPlayers > dimensions) {
            System.out.println("Total Players should be > 0 and <= Grid size.");
            System.out.print("Enter Total Players - ");
            totalPlayers = scanner.nextInt();
        }

        int i = 0;
        Set<Character> set = new HashSet<>();
        List<Player> players = new ArrayList<>();
        while (i < totalPlayers) {
            scanner.nextLine();
            System.out.print("Enter Player " + (i + 1) + " name - ");
            String name = scanner.nextLine();
            System.out.print("Enter Player " + (i + 1) + " symbol - ");
            char symbol = scanner.next().charAt(0);
            while (!set.add(symbol)) {
                System.out.println("Symbol " + symbol + " has already been selected by other Player.");
                System.out.print("Enter Player " + (i + 1) + " symbol - ");
                symbol = scanner.next().charAt(0);
            }
            players.add(new Player(i++, name, new Symbol(symbol)));
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
