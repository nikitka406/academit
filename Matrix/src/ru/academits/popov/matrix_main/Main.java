package ru.academits.popov.matrix_main;

import ru.academits.popov.matrix.Matrix;
import ru.academits.popov.vector.Vector;

import static ru.academits.popov.matrix.Matrix.*;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 5);
        System.out.println(matrix1);

        double[] row1 = {1, 2, 3};
        double[] row2 = {4, 5};
        double[] row3 = {7, 8, 9, 10};
        double[][] arrayVectors1 = {row1, row2, row3};
        Matrix matrix2 = new Matrix(arrayVectors1);
        System.out.println(matrix2);

        Vector vector1 = new Vector(3);
        Vector vector2 = new Vector(4);
        Vector vector3 = new Vector(1);
        Vector[] vectors = {vector1, vector2, vector3};
        Matrix matrix3 = new Matrix(vectors);
        System.out.println(matrix3);

        Matrix matrix4 = new Matrix(matrix2);
        System.out.println(matrix4);

        System.out.println("Количество колонок = " + matrix4.getColumnsCount());
        System.out.println("Количество строк = " + matrix4.getRowsCount());

        System.out.println("Столбец №" + 1 + " матрицы = " + matrix4.getColumn(1));
        System.out.println("Строка №" + 2 + " матрицы = " + matrix4.getRow(2));

        double[][] arrayVectors2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        Matrix matrix5 = new Matrix(arrayVectors2);
        System.out.println("Матрица до транспонирования  = " + matrix5);
        matrix5.transpose();
        System.out.println("Транспонированная матрица = " + matrix5);

        matrix5.multiplyByScalar(5);
        System.out.println("Умножение матрицы на скаляр 5 = " + matrix5);

        matrix5.add(matrix5);
        System.out.println("Сложение двух матриц = " + matrix4);

        matrix5.subtract(matrix5);
        System.out.println("Вычетание двух матриц = " + matrix5);

        System.out.println("Статическое сложение матриц = " + getSum(matrix5, matrix5));

        System.out.println("Статическое вычитание матриц = " + getDifference(matrix5, matrix5));

        Vector vector = new Vector(row3);
        System.out.println("Произведение матрицы на вектор = " + matrix4.multiplyByVector(vector));

        System.out.println("Произведение матриц = " + getProduct(matrix5, matrix5));

        double[][] arrayVectors3 = {{2, 4, 1}, {0, 2, 1}, {2, 1, 1}};
        Matrix matrix6 = new Matrix(arrayVectors3);
        System.out.println("Детерминант матрицы = " + matrix6.getDeterminant());
    }
}
