package src.services;

import java.util.Random;

public class MatrixService {
    public void fillMatrix(int[][] matrix) {
        Random rnd = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rnd.nextInt(100) + 1;
            }
        }
    }

    public void printMatrix(int[][] matrix, String message) {
        System.out.println("\n--- " + message + " ---");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    public int[] findMaxCoordinates(int[][] matrix) {
        int max = matrix[0][0];
        int rowIdx = 0;
        int colIdx = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    rowIdx = i;
                    colIdx = j;
                }
            }
        }

        System.out.println("\nMax element: " + max + " at [" + rowIdx + "][" + colIdx + "]");
        return new int[] { rowIdx, colIdx };
    }

    public void moveMaxToTopLeft(int[][] matrix, int targetRow, int targetCol) {
        int[] tempRow = matrix[0];
        matrix[0] = matrix[targetRow];
        matrix[targetRow] = tempRow;

        for (int i = 0; i < matrix.length; i++) {
            int tempVal = matrix[i][0];
            matrix[i][0] = matrix[i][targetCol];
            matrix[i][targetCol] = tempVal;
        }
    }
}
