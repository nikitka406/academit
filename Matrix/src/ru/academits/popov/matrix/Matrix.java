package ru.academits.popov.matrix;

import ru.academits.popov.vector.Vector;

import static ru.academits.popov.vector.Vector.getScalarProduct;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице не может быть меньше или равно 0, rowsCount = " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице не может быть меньше или равно 0, columnsCount = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице не может быть равно 0");
        }

        int maxSize = 0;

        for (double[] row : matrix) {
            if (maxSize < row.length) {
                maxSize = row.length;
            }
        }

        if (maxSize == 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице не может быть равно 0");
        }

        rows = new Vector[matrix.length];

        for (int i = 0; i < matrix.length; ++i) {
            rows[i] = new Vector(maxSize, matrix[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице не может быть равно 0");
        }

        int maxSize = 0;

        for (Vector vector : vectors) {
            if (maxSize < vector.getSize()) {
                maxSize = vector.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(maxSize);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Нет строки с индексом, " + index + ". Допустимый диапазон: от 0 до " + (rows.length-1));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Нет строки с индексом, " + index + ". Допустимый диапазон: от 0 до " + (rows.length-1));
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер вектора не совпадает с количеством столбцов. " +
                    "Размер вектора  = " + vector.getSize() + ", количество столбцов = " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Нет строки с индексом, " + index + ". Допустимый диапазон: от 0 до " + (getColumnsCount()-1));
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector row : rows) {
            stringBuilder.append(row);
            stringBuilder.append(", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); ++i) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double number) {
        for (Vector row : rows) {
            row.multiplyByScalar(number);
        }
    }

    public void add(Matrix matrix) {
        checkMatrixSizes(this, matrix);

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrixSizes(this, matrix);

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("Матрица должна быть квадратная, количество строк = "
                    + getRowsCount() + " количество столбцов = " + getColumnsCount());
        }

        if (getRowsCount() == 1) {
            return rows[0].getComponent(0);
        }

        if (getRowsCount() == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = 0;
        // Раскладываем по столбцу
        for (int i = 0; i < getRowsCount(); ++i) {
            double[][] minor = new double[getRowsCount() - 1][getColumnsCount() - 1];

            for (int j = 1; j < getRowsCount(); ++j) {
                int indexByMinor = 0;

                for (int k = 0; k < getColumnsCount(); ++k) {
                    if (i != k) {
                        minor[j - 1][indexByMinor] = rows[j].getComponent(k);
                        ++indexByMinor;
                    }
                }
            }

            determinant += rows[0].getComponent(i) * Math.pow(-1, i) * new Matrix(minor).getDeterminant();
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер вектора = " + vector.getSize() +
                    " должен совпадать с количеством столбцов в матрице = " + getColumnsCount());
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            result.setComponent(i, getScalarProduct(rows[i], vector));
        }

        return result;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatrixSizes(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatrixSizes(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Количество столбцов первой матицы должно совпадать с количеством строк второй матрицы." +
                    "Количество столбцов первой матицы = " + matrix1.getColumnsCount() + ", количество строк второй матрицы = " + matrix2.getRowsCount());
        }

        Matrix result = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());

        for (int i = 0; i < result.rows.length; ++i) {
            for (int j = 0; j < result.getColumnsCount(); ++j) {
                result.rows[i].setComponent(j, getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return result;
    }

    private static void checkMatrixSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера, matrix1 = "
                    + matrix1.getRowsCount() + "x" + matrix1.getColumnsCount() + " matrix2 = " + matrix2.getRowsCount() + "x" + matrix2.getColumnsCount());
        }
    }
}
