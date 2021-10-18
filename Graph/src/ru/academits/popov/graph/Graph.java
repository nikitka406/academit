
package ru.academits.popov.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

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

    public void traverseWidth(IntConsumer consumer) {
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

                consumer.accept(queuedIndex);
                visited[queuedIndex] = true;

                for (int j = 0; j < matrix.length; j++) {
                    if (j != queuedIndex && matrix[queuedIndex][j] > 0) {
                        queue.add(j);
                    }
                }
            }
        }
    }

    public void traverseDepth(IntConsumer consumer) {
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

                consumer.accept(queuedIndex);
            }
        }
    }

    public void traverseDepthRecursion(int index, boolean[] visited, IntConsumer consumer) {
        visited[index] = true;
        consumer.accept(index);

        for (int i = 1; i < matrix[index].length; ++i) {
            if (matrix[index][i] == 1 && !visited[i]) {
                traverseDepthRecursion(i, visited, consumer);
            }
        }
    }

    public void traverseDepthRecursion(IntConsumer consumer) {
        boolean[] visited = new boolean[matrix.length];

        for (int i = 0; i < matrix.length; ++i) {
            if (!visited[i]) {
                traverseDepthRecursion(i, visited, consumer);
            }
        }
    }
}
