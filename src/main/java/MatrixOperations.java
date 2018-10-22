class MatrixOperations {

    static Matrix addMatrices(Matrix a, Matrix b) {
        int columns = a.getColumns();
        int rows = a.getRows();
        Matrix result = new Matrix(columns, rows);
        for (int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                float valueA = a.getValue(i, j);
                float valueB = b.getValue(i, j);
                result.setValue(i, j, valueA+valueB);
            }
        }
        a.display(); b.display(); result.display();
        return result;
    }

    static Matrix multiplyMatrices(Matrix a, Matrix b) {
        return new Matrix(0,0);
    }

    static float calculateDeterminant(Matrix a) {
        return 0;
    }
}