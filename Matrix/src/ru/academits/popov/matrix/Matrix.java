package ru.academits.popov.matrix;

import ru.academits.popov.vector.Vector;

import java.util.Arrays;

import static ru.academits.popov.vector.Vector.getScalarProduct;

public class Matrix {
    private Vector[] rows;

    public Matrix(int row, int column) {
        if (row <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице не может быть меньше и равно 0, n = " + row);
        }

        if (column <= 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице не может быть меньше ии равно 0, m = " + column);
        }

        rows = new Vector[row];

        for (int i = 0; i < row; i++) {
            rows[i] = new Vector(column);
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

        for (double[] rows : matrix) {
            if (maxSize < rows.length) {
                maxSize = rows.length;
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

    public void setRow(int index, Vector vector) {
        if (index > rows.length || index < 0) {
            throw new IllegalArgumentException("Нет строки с таким номером, index = " + index);
        }

        if (vector.getSize() == 0) {
            throw new IllegalArgumentException("В матрицу нельзя вставить вектор размера 0, vector = " + vector);
        }

        rows[index] = new Vector(vector);
    }

    public Vector getRow(int index) {
        if (index > rows.length || index < 0) {
            throw new IllegalArgumentException("Нет строки с таким номером, index = " + index);
        }

        return new Vector(rows[index]);
    }

    public Vector getColumn(int index) {
        if (index > getColumnsCount() || index < 0) {
            throw new IllegalArgumentException("Нет колонки с таким номером, index = " + index);
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
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void transpose() {
        Vector[] transposedVectors = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); ++i) {
            transposedVectors[i] = getColumn(i);
        }

        rows = transposedVectors;
    }

    public void multiplyByScalar(double number) {
        for (Vector row : rows) {
            row.multiplyByScalar(number);
        }
    }

    public void add(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера, matrix1 = "
                    + getRowsCount() + "x" + getColumnsCount() + " matrix2 = " + matrix.getRowsCount() + "x" + matrix.getColumnsCount());
        }

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера, matrix1 = "
                    + getRowsCount() + "x" + getColumnsCount() + " matrix2 = " + matrix.getRowsCount() + "x" + matrix.getColumnsCount());
        }

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    //todo
    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()){
            throw  new IllegalArgumentException("Размер вектора = " + vector.getSize() +
                    " должен совпадать с количеством столбцов в матрице = " + getColumnsCount());
        }
        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            result.setComponent(i, getScalarProduct(rows[i], vector));
        }

        return result;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix1.getColumnsCount() || matrix2.getRowsCount() != matrix2.getRowsCount()
                || matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Для умножения матрицы должны быть квадратные и одинакового размера");
        }

        Matrix result = new Matrix(matrix1.rows.length, matrix1.getColumnsCount());

        for (int i = 0; i < result.rows.length; ++i) {
            for (int j = 0; j < result.getColumnsCount(); ++j) {
                result.rows[i].setComponent(j, getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return result;
    }
}
