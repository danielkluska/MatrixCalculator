class MatrixOperations {

    public static Matrix addMatrices(Matrix a, Matrix b) {
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
        return result;
    }

    public static Matrix multiplyMatrices(Matrix a, Matrix b) {
        int columnsA = a.getColumns();
        int rowsA = a.getRows();
        int columnsB = b.getColumns();
        Matrix result = new Matrix(columnsB ,rowsA);
        for (int i=0; i<columnsB; i++) {
            for (int j=0; j<rowsA; j++) {
                float sum = 0;
                for (int k=0; k<columnsA; k++) {
                    sum += (a.getValue(k,j) * b.getValue(i, k));
                }
                result.setValue(i, j, sum);
            }
        }
        return result;
    }

    public static Matrix calculateDeterminant(Matrix a) {
        Matrix result = new Matrix(1,1);
        if (a.getColumns() == 1)
            result.setValue(0,0, a.getValue(0,0));
        else {
            float sum = 0;
            for (int i = 0; i<a.getColumns(); i++) {
                Matrix minor = calculateMinor(a, i, 0);
                sum += Math.pow(-1, i) * a.getValue(i, 0) * calculateDeterminant(minor).getValue(0,0);
            }
            result.setValue(0,0, sum);
        }
        return result;
    }

    private static Matrix calculateMinor(Matrix a, int x, int y) {
        int size = a.getColumns();
        Matrix result = new Matrix(size-1, size-1);
        int column_index = 0;
        for(int i = 0; i<size; i++) {
            if (i==x)
                continue;
            int row_index = 0;
            for (int j = 0; j<size; j++) {
                if(j==y)
                    continue;
                result.setValue(column_index, row_index, a.getValue(i,j));
                row_index++;
            }
            column_index++;
        }
        return result;
    }

    public static boolean compareMatrices(Matrix a, Matrix b) {
        a.display();
        b.display();
        if(a.getColumns() != b.getColumns() || a.getRows() != b.getRows())
            return false;
        for(int i=0; i<a.getColumns(); i++) {
            for(int j=0; j<a.getRows(); j++) {
                if(a.getValue(i, j) != b.getValue(i, j))
                    return false;
            }
        }
        return true;
    }
}