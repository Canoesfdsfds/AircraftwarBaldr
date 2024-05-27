import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StrassenMatrixMultiplicationTest {

    @Test
    void testMultiply() {
        // Create instances of StrassenMatrixMultiplication
        StrassenMatrixMultiplication strassen = new StrassenMatrixMultiplication();

        // Define test matrices
        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        int[][] B = {
            {17, 18, 19, 20},
            {21, 22, 23, 24},
            {25, 26, 27, 28},
            {29, 30, 31, 32}
        };
        int[][] expectedResult = {
            {250, 260, 270, 280},
            {618, 644, 670, 696},
            {986, 1028, 1070, 1112},
            {1354, 1412, 1470, 1528}
        };

        // Perform matrix multiplication
        int[][] result = strassen.multiply(A, B);

        // Assert the result
        assertArrayEquals(expectedResult, result);
    }
}
    