
package ru.academits.popov.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private final int[][] matrix;

    public Graph(int[][] matrix) {
        int rowsCount = matrix.length;

        for (int[] rows : matrix) {
            int columnsCount = rows.length;

            if (rowsCount != columnsCount) {
                throw new IllegalArgumentException("Количество строк = " + rowsCount + ". Должно совпадать с количеством столбцов" + columnsCount + ".");
            }
        }

        this.matrix = matrix;
    }

    public int getSize() {
        return matrix.length;
    }

    public void traverseWidth() {
        boolean[] visited = new boolean[matrix.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            if (visited[i]) {
                continue;
            }

            queue.add(i);

            while (!queue.isEmpty()) {
                int queuedIndex = queue.remove();

                if (visited[queuedIndex]) {
                    continue;
                }

                visited[queuedIndex] = true;

                for (int j = queuedIndex + 1; j < matrix.length; j++) {
                    if (matrix[queuedIndex][j] != 0) {
                        queue.add(j);
                    }
                }

                System.out.println(queuedIndex);
            }
        }
    }

    public void traverseDepth() {
        boolean[] visited = new boolean[matrix.length];

        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            if (visited[i]) {
                continue;
            }

            stack.addLast(i);

            while (!stack.isEmpty()) {
                int queuedIndex = stack.removeLast();

                if (visited[queuedIndex]) {
                    continue;
                }

                visited[queuedIndex] = true;

                for (int j = matrix.length - 1; j >= 0; j--) {
                    if (j != queuedIndex && matrix[queuedIndex][j] != 0) {
                        stack.addLast(j);
                    }
                }

                System.out.println(queuedIndex);
            }
        }
    }

    public void traverseDepthRecursion(int index, boolean[] visited) {
        if (visited[index]) {
            return;
        }

        visited[index] = true;

        System.out.println(index);

        for (int j = index + 1; j < matrix.length; ++j) {
            if (matrix[index][j] != 0) {
                traverseDepthRecursion(j, visited);
            }
        }
    }

    public void traverseDepthRecursion() {
        boolean[] visited = new boolean[matrix.length];
        traverseDepthRecursion(0, visited);

        for (int i = 0; i < matrix.length; ++i) {
            if (!visited[i]) {
                traverseDepthRecursion(i, visited);
            }
        }
    }
}
