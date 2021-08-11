package ru.academits.popov.matrix;

import ru.academits.popov.vector.Vector;

import java.util.Arrays;

import static ru.academits.popov.vector.Vector.getScalarProduct;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Колличество строк в матрице не может быть меньше и равно 0, n = " + n);
        }

        if (m <= 0) {
            throw new IllegalArgumentException("Колличество столбцов в матрице не может быть меньше ии равно 0, m = " + m);
        }

        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Колличество строк в матрице не может быть равно 0");
        }

        this.rows = new Vector[matrix.length];

        int maxSize = 0;

        for (double[] doubles : matrix) {
            if (maxSize < doubles.length) {
                maxSize = doubles.length;
            }
        }

        for (int i = 0; i < matrix.length; ++i) {
            this.rows[i] = new Vector(maxSize, matrix[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Колличество строк в матрице не может быть равно 0");
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

    public void setRows(Vector vector, int index) {
        rows[index] = new Vector(vector);
    }

    public Vector getRows(int index) {
        return rows[index];
    }

    public Vector getColumn(int index) {
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
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void transpose() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Транспонировать можно только квадратную матрицу, количество строк = " + rows.length + ", количество столбцов " + rows[0].getSize());
        }

        double temp;

        for (int i = 0; i < rows.length; ++i) {
            for (int j = i + 1; j < rows[i].getSize(); ++j) {
                if (i != j) {
                    temp = rows[i].getComponent(j);
                    rows[i].setComponent(j, rows[j].getComponent(i));
                    rows[j].setComponent(i, temp);
                }
            }
        }
    }

    public void multiplyByScalar(double number) {
        for (Vector row : rows) {
            row.multiplyByScalar(number);
        }
    }

    public void add(Matrix matrix) {
        if (rows.length < matrix.rows.length) {
            rows = Arrays.copyOf(rows, matrix.rows.length);
        }

        for (int i = 0; i < matrix.rows.length; ++i) {
            if (rows[i] == null) {
                assert rows[0] != null;
                rows[i] = new Vector(rows[0].getSize());
            }

            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length < matrix.rows.length) {
            rows = Arrays.copyOf(rows, matrix.rows.length);
        }

        for (int i = 0; i < matrix.rows.length; ++i) {
            if (rows[i] == null) {
                assert rows[0] != null;
                rows[i] = new Vector(rows[0].getSize());
            }

            rows[i].subtract(matrix.rows[i]);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        Vector result = new Vector(Math.max(rows.length, vector.getSize()));

        for (int i = 0; i < Math.min(rows.length, vector.getSize()); ++i) {
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

    public static Matrix getMatrixProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix1.rows[0].getSize() || matrix1.rows.length != matrix2.rows.length) {
            throw new IllegalArgumentException("Для умножения матрицы должны быть квадратные одинакового размера");
        }

        Matrix result = new Matrix(matrix1.rows.length, matrix1.rows[0].getSize());

        for (int i = 0; i < result.rows.length; ++i) {
            for (int j = 0; j < result.rows[i].getSize(); ++j) {
                result.rows[i].setComponent(j, getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return result;
    }
}
