package ru.academits.popov.graph_main;

import ru.academits.popov.graph.Graph;

public class Main {
    public static void main(String[] args) {
        int[] row1 = {0, 1, 1, 0, 1};
        int[] row2 = {1, 0, 0, 1, 1};
        int[] row3 = {1, 0, 0, 0, 1};
        int[] row4 = {0, 1, 0, 0, 1};
        int[] row5 = {1, 1, 1, 1, 0};
        int[][] matrix ={row1, row2, row3, row4, row5};

        Graph graph = new Graph(matrix);
        System.out.println("Обход графа в ширину: ");
        graph.traverseWidth();
        System.out.println("Обход графа в глубину: ");
        graph.traverseDepth();
        System.out.println("Обход графа в глубину с рекурсией: ");
        graph.traverseDepthRecursion();
    }
}
