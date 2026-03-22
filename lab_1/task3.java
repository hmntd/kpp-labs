import src.services.MatrixService;

public class task3 {
    public static void main(String[] args) {
        MatrixService matrixService = new MatrixService();

        int rows = 5;
        int cols = 5;
        int[][] arr = new int[rows][cols];

        matrixService.fillMatrix(arr);
        matrixService.printMatrix(arr, "Initial matrix");

        int[] coords = matrixService.findMaxCoordinates(arr);
        int maxRow = coords[0];
        int maxCol = coords[1];

        matrixService.moveMaxToTopLeft(arr, maxRow, maxCol);

        matrixService.printMatrix(arr, "Matrix after moving max to [0][0]");
    }
}
