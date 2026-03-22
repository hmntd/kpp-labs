import java.util.Scanner;

import src.models.Game2048State;
import src.services.Game2048Service;

public class task5 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Game2048Service service = new Game2048Service();
            Game2048State state = new Game2048State();

            boolean sessionActive = true;

            while (sessionActive) {
                state.resetForNewGame();
                service.spawnTile(state.getGrid());
                service.spawnTile(state.getGrid());
                boolean gameActive = true;

                while (gameActive) {
                    printBoard(state);
                    System.out.print("Move (W/A/S/D), R to Restart, Q to Quit: ");
                    String input = sc.next().toLowerCase();

                    if (input.equals("q")) {
                        sessionActive = false;
                        break;
                    }

                    if (input.equals("r"))
                        break;

                    boolean moved = service.move(state.getGrid(), input.charAt(0), state);
                    if (moved) {
                        service.spawnTile(state.getGrid());

                        if (state.checkFor2048(state.getGrid())) {
                            System.out.println("\n************************************");
                            System.out.println("* CONGRATULATIONS! You reached 2048! *");
                            System.out.println("* You can continue to set a record.  *");
                            System.out.println("************************************\n");
                        }
                    }

                    if (service.isGameOver(state.getGrid())) {
                        printBoard(state);
                        System.out.println("\n[!] GAME OVER: No more moves possible.");
                        System.out.println("Your Final Score: " + state.getCurrentScore());
                        gameActive = false;
                    }
                }
            }

            System.out.println("Thanks for playing! Final Best Score: " + state.getBestScore());
        }
    }

    private static void printBoard(Game2048State state) {
        System.out.println("\nScore: " + state.getCurrentScore() + " | Best: " + state.getBestScore());
        for (int[] row : state.getGrid()) {
            for (int cell : row) {
                if (cell == 0) {
                    System.out.print("[    ] ");
                    continue;
                }

                System.out.printf("[%4d] ", cell);
            }

            System.out.println();
        }
    }
}
