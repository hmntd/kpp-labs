import java.util.Scanner;

import src.models.GameState;
import src.services.GameService;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameService service = new GameService();
        GameState state = new GameState();

        try {
            // --- LEVEL 1 ---
            boolean level1Success = runLevel(1, 3, 1, 10, scanner, service, state);

            if (level1Success) {
                System.out.println("\nLevel 1 Complete! Player Score: " + state.getPlayerScore());
                System.out.print("Do you want to continue to Level 2? (y/N): ");

                if (scanner.next().equalsIgnoreCase("y")) {
                    // --- LEVEL 2 ---
                    boolean level2Success = runLevel(2, 2, 10, 100, scanner, service, state);
                    if (!level2Success) {
                        System.out.println("\nGAME OVER. You failed Level 2.");
                    }
                }
            } else {
                System.out.println("\nGAME OVER. Computer wins Level 1.");
            }

            System.out.println("\n=== FINAL RESULTS ===");
            System.out.println("Final Player Score: " + state.getPlayerScore());
            System.out.println("Final Computer Score: " + state.getComputerScore());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static boolean runLevel(
            int level,
            int rounds,
            int min,
            int max,
            Scanner sc,
            GameService service,
            GameState state) {
        int range = (max - min) + 1;
        int multiplier = service.getMultiplier(level);
        boolean hasLostAnyRound = false;

        for (int r = 1; r <= rounds; r++) {
            int initialLives = service.calculateInitialLives(level, range);
            state.setLives(initialLives);
            state.setTargetNumber(service.generateTarget(min, max));

            System.out.println("\n--- Level " + level + " | Round " + r + " ---");
            System.out.println("New lives granted: " + initialLives);

            while (state.getLives() > 0) {
                System.out.print("Lives: " + state.getLives()  + " Correct answer is: " + state.getTargetNumber() + ". Guess: ");
                int guess = sc.nextInt();

                if (guess == state.getTargetNumber()) {
                    System.out.println("Correct! Round " + r + " won.");
                    state.addPlayerScore(state.getLives() * multiplier);
                    break;
                }

                state.setLives(state.getLives() - 1);

                if (state.getLives() > 0) {
                    System.out.print("Wrong! Use hint for 1 life? (y/n): ");
                    if (sc.next().equalsIgnoreCase("y")) {
                        state.setLives(state.getLives() - 1);
                        String hint = (state.getTargetNumber() > guess) ? "Higher" : "Lower";
                        System.out.println("Hint: The number is " + hint);
                    }
                }
            }

            if (state.getLives() <= 0) {
                hasLostAnyRound = true;
                System.out.println("\nNo lives left! The correct number was: " + state.getTargetNumber());
                int penalty = initialLives * multiplier;
                state.addComputerScore(penalty);
                System.out.println("Round " + r + " lost. Computer gets " + penalty + " points.");
            }

            System.out.println("Round " + r + " Summary -> Player: " + state.getPlayerScore() + " | Computer: "
                    + state.getComputerScore());
        }

        return !hasLostAnyRound;
    }
}