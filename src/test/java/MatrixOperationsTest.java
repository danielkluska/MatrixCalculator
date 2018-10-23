import org.junit.Assert;
import org.junit.Test;

public class MatrixOperationsTest {

    @Test
    public void testMatrixAddition() {
        Matrix a = new Matrix(2,3);
        Matrix b = new Matrix(2, 3);
        Matrix expected = new Matrix(2, 3);
        float[][] arrayA = { {3, -5, 1}, {2, 0, -1} };
        float[][] arrayB = { {-1, 0, -30}, {4, 0, 1} };
        a.setValuesFromArray(arrayA);
        b.setValuesFromArray(arrayB);
        float[][] expectedArray = { {2, -5, -29}, {6, 0, 0} };
        expected.setValuesFromArray(expectedArray);
        Assert.assertTrue(MatrixOperations.compareMatrices(expected, MatrixOperations.addMatrices(a, b)));
    }

    @Test
    public void testMatrixMultiplication() {
        Matrix a = new Matrix(3,2);
        Matrix b = new Matrix(2, 3);
        Matrix expected = new Matrix(2, 2);
        float[][] arrayA = { {2, 4}, {4, 0}, {-1, 2} };
        float[][] arrayB = { {1, 9, 1}, {-2, 3, 4} };
        a.setValuesFromArray(arrayA);
        b.setValuesFromArray(arrayB);
        float[][] expectedArray = { {37, 6}, {4, 0} };
        expected.setValuesFromArray(expectedArray);
        Assert.assertTrue(MatrixOperations.compareMatrices(expected, MatrixOperations.multiplyMatrices(a, b)));
    }

    @Test
    public void testDeterminant() {
        Matrix a = new Matrix(4,4);
        float [][] arrayA = { {1, 0, -3, 1}, {-2, 3, -1, 6}, {4, 1, 0, 2}, {9, 2, 5, 1} };
        a.setValuesFromArray(arrayA);
        float expectedDeterminant = -60.0f;
        Assert.assertEquals(MatrixOperations.calculateDeterminant(a).getValue(0,0),expectedDeterminant, 0.05);
    }
}