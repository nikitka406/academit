package ru.academits.popov.matrix_main;

import ru.academits.popov.matrix.Matrix;
import ru.academits.popov.vector.Vector;

import static ru.academits.popov.matrix.Matrix.*;

public class Main {
    public static void main(String[] args) {
        Matrix test1 = new Matrix(3, 5);
        System.out.println(test1);

        double[] row1 = {1, 2, 3};
        double[] row2 = {4, 5};
        double[] row3 = {7, 8, 9, 10};
        double[][] matrix1 = {row1, row2, row3};
        Matrix test2 = new Matrix(matrix1);
        System.out.println(test2);

        Vector vector1 = new Vector(3);
        Vector vector2 = new Vector(4);
        Vector vector3 = new Vector(1);
        Vector[] vectors = {vector1, vector2, vector3};
        Matrix test3 = new Matrix(vectors);
        System.out.println(test3);

        Matrix test4 = new Matrix(test2);
        System.out.println(test4);

        System.out.println("Количество колонок = " + test4.getColumnsCount());
        System.out.println("Количество строк = " + test4.getRowsCount());

        System.out.println("Столбец №" + 1 + " матрицы = " + test4.getColumn(1));
        System.out.println("Строка №" + 2 + " матрицы = " + test4.getRow(2));

        double[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        Matrix test5 = new Matrix(matrix2);
        System.out.println("Матрица до транспонирования  = " + test5);
        test5.transpose();
        System.out.println("Транспонированная матрица = " + test5);

        test5.multiplyByScalar(5);
        System.out.println("Умножение матрицы на скаляр 5 = " + test5);

        test5.add(test5);
        System.out.println("Сложение двух матриц = " + test4);

        test5.subtract(test5);
        System.out.println("Вычетание двух матриц = " + test5);

        System.out.println("Статическое сложение матриц = " + getSum(test5, test5));

        System.out.println("Статическое вычитание матриц = " + getDifference(test5, test5));

        Vector vector = new Vector(row3);
        System.out.println("Произведение матрицы на вектор = " + test4.multiplyByVector(vector));

        System.out.println("Произведение матриц = " + getProduct(test5, test5));

        double[][] matrix3 = {{2, 4, 1}, {0,2,1}, {2,1,1}};
        Matrix test6 = new Matrix(matrix3);
        System.out.println("Детерминант матрицы = " + test6.getDeterminant());
    }
}
