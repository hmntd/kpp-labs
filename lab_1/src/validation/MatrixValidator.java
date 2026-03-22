package src.validation;

public class MatrixValidator {
    public static void validateSquare(int[][] matrix) throws Exception {
        if (matrix == null || matrix.length == 0) {
            throw new Exception("Масив не може бути порожнім.");
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i].length != rows) {
                throw new Exception("Масив повинен бути квадратним (n x n).");
            }
        }
    }
}
