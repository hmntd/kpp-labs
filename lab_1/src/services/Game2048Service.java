package src.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.models.Game2048State;

public class Game2048Service {
    private Random rnd = new Random();

    public void spawnTile(int[][] grid) {
        List<int[]> emptyCells = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0)
                    emptyCells.add(new int[] { i, j });
            }
        }

        if (!emptyCells.isEmpty()) {
            int[] cell = emptyCells.get(rnd.nextInt(emptyCells.size()));
            grid[cell[0]][cell[1]] = (rnd.nextDouble() < 0.9) ? 2 : 4;
        }
    }

    public boolean move(int[][] grid, char direction, Game2048State state) {
        boolean moved = false;
        boolean isVertical = (direction == 'w' || direction == 's');

        if (isVertical)
            transpose(grid);

        for (int i = 0; i < 4; i++) {
            int[] row = grid[i];

            if (direction == 'd' || direction == 's')
                reverse(row);

            int[] newRow = slideAndMerge(row, state);

            if (direction == 'd' || direction == 's')
                reverse(newRow);

            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != newRow[j])
                    moved = true;
                grid[i][j] = newRow[j];
            }
        }

        if (isVertical)
            transpose(grid);

        return moved;
    }

    private int[] slideAndMerge(int[] row, Game2048State state) {
        int[] result = new int[4];
        int pos = 0;

        for (int i = 0; i < 4; i++) {
            if (row[i] != 0)
                result[pos++] = row[i];
        }

        for (int i = 0; i < 3; i++) {
            if (result[i] != 0 && result[i] == result[i + 1]) {
                result[i] *= 2;
                state.addScore(result[i]);
                result[i + 1] = 0;
            }
        }

        int[] finalRow = new int[4];
        pos = 0;
        for (int i = 0; i < 4; i++) {
            if (result[i] != 0)
                finalRow[pos++] = result[i];
        }

        return finalRow;
    }

    private void reverse(int[] row) {
        for (int i = 0; i < 2; i++) {
            int temp = row[i];
            row[i] = row[3 - i];
            row[3 - i] = temp;
        }
    }

    private void transpose(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int temp = grid[i][j];
                grid[i][j] = grid[j][i];
                grid[j][i] = temp;
            }
        }
    }

    public boolean isGameOver(int[][] grid) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0)
                    return false;

                if (j < 3 && grid[i][j] == grid[i][j + 1])
                    return false;

                if (i < 3 && grid[i][j] == grid[i + 1][j])
                    return false;
            }
        }

        return true;
    }
}
