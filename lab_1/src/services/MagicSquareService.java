package src.services;

public class MagicSquareService {
    public boolean isMagicSquare(int[][] matrix) {
        int n = matrix.length;

        // Calculate the first row to find the target sum
        int targetSum = 0;
        for (int j = 0; j < n; j++) {
            targetSum += matrix[0][j];
        }

        // Compare the row sums with the target sum
        for (int i = 1; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
            }
            if (rowSum != targetSum)
                return false;
        }

        // Compare the column sums with the target sum
        for (int j = 0; j < n; j++) {
            int colSum = 0;
            for (int i = 0; i < n; i++) {
                colSum += matrix[i][j];
            }
            if (colSum != targetSum)
                return false;
        }

        // Compare the diagonal sums with the target sum
        int mainDiagSum = 0;
        for (int i = 0; i < n; i++) {
            mainDiagSum += matrix[i][i];
        }
        if (mainDiagSum != targetSum)
            return false;

        int sideDiagSum = 0;
        for (int i = 0; i < n; i++) {
            sideDiagSum += matrix[i][n - 1 - i];
        }
        if (sideDiagSum != targetSum)
            return false;

        return true;
    }
}
