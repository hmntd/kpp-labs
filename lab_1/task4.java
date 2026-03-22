import src.services.MagicSquareService;
import src.services.MatrixService;
import src.validation.MatrixValidator;

public class task4 {
    public static void main(String[] args) {
        MatrixService matrixService = new MatrixService();
        MagicSquareService service = new MagicSquareService();

        int rows = 3;
        int cols = 3;
        int[][] magic = new int[rows][cols];

        matrixService.fillMatrix(magic);
        // int[][] magic = {
        // {2, 7, 6},
        // {9, 5, 1},
        // {4, 3, 8}
        // };
        matrixService.printMatrix(magic, "Initial matrix");

        try {
            MatrixValidator.validateSquare(magic);

            if (service.isMagicSquare(magic)) {
                System.out.println("Result: it is the magic square!");
            } else {
                System.out.println("Result: it is not the magic square.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
