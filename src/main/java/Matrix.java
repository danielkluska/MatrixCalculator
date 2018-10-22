class Matrix {

    private int columns;
    private int rows;
    private float[][] values;

    Matrix(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        values = new float[columns][rows];
    }

    public void display() {
        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<columns; j++) {
                System.out.print(values[j][i]+"   ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public float getValue(int x, int y) {
        return values[x][y];
    }

    public void setValue(int x, int y, float value) {
        values[x][y] = value;
    }

    public void setValuesFromArray(float[][] array) {
        for (int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                setValue(i, j, array[i][j]);
            }
        }
    }

    public float[][] getArrayFromValues() {
        float[][] array = new float[columns][rows];
        for (int i=0; i<columns; i++) {
            for (int j=0; j<rows; j++) {
                array[i][j] = getValue(i, j);
            }
        }
        return array;
    }
}