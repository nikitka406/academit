package ru.academits.popov.tree;

import javax.sound.midi.Track;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public Tree() {
    }

    public Tree(T data) {
        root = new TreeNode<T>(data);
        size++;
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        } else if (data1 == null) {
            return -1;
        } else if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    public TreeNode<T> getNode(T data) {
        TreeNode<T> node = root;

        while (node != null) {
            int result = compare(node.getData(), data);

            switch (result) {
                case 0:
                    return node;
                case 1:
                    node = node.getLeft();

                    if (node == null) {
                        return null;
                    }

                    break;
                case -1:
                    node = node.getRight();

                    if (node == null) {
                        return null;
                    }

                    break;
            }
        }

        return null;
    }

    public TreeNode<T> getPreviousNode(T data) {
        TreeNode<T> node = root;

        while (node != null) {
            if ((node.getLeft() != null && node.getLeft().getData() == data) ||
                    (node.getRight() != null && node.getRight().getData() == data)) {
                return node;
            }

            int result = compare(node.getData(), data);

            switch (result) {
                case 0:
                    return node;
                case 1:
                    node = node.getLeft();

                    if (node == null) {
                        return null;
                    }

                    break;
                case -1:
                    node = node.getRight();

                    if (node == null) {
                        return null;
                    }

                    break;
            }
        }

        return null;
    }

    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<T> node = root;

        while (true) {
            int comparison = compare(node.getData(), data);

            if (comparison > 0) {
                if (node.getLeft() == null) {
                    node.setLeft(new TreeNode<>(data));
                    size++;
                    return;
                }

                node = node.getLeft();
            } else {
                if (node.getRight() == null) {
                    node.setRight(new TreeNode<>(data));
                    size++;
                    return;
                }

                node = node.getRight();
            }
        }
    }

    public void remove(T data) {
        if (root == null) {
            return;
        }

        TreeNode<T> requiredNode = getNode(data);
        TreeNode<T> leftNode = requiredNode.getLeft();
        TreeNode<T> rightNode = requiredNode.getRight();

        if (leftNode == null && rightNode == null) {
            // Удаление листа
            TreeNode<T> previousNode = getPreviousNode(data);

            if (previousNode.getRight() != null && previousNode.getRight().getData() == data) {
                previousNode.setRight(null);
                requiredNode.setData(null);
            } else {
                previousNode.setLeft(null);
                requiredNode.setData(null);
            }
        } else if (leftNode == null || rightNode == null) {
            // Удаление узла с одним ребенком
            if (root.getData() != data) {
                TreeNode<T> previousNode = getPreviousNode(data);

                if (leftNode != null) {
                    previousNode.setLeft(leftNode);
                } else {
                    previousNode.setRight(rightNode);
                }
            } else {
                if (leftNode != null) {
                    root.setData(leftNode.getData());
                    root.setLeft(null);
                } else {
                    root.setData(rightNode.getData());
                    root.setRight(null);
                }
            }
        } else {
            // Удаление узла с двумя детьми
            TreeNode<T> minNode = requiredNode;

            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.add(requiredNode);

            while (!queue.isEmpty()) {
                TreeNode<T> queuedNode = queue.remove();
                int comparison = compare(queuedNode.getData(), minNode.getData());

                if (comparison <= 0) {
                    minNode = queuedNode;
                }

                if (queuedNode.getLeft() != null) {
                    queue.add(queuedNode.getLeft());
                }

                if (queuedNode.getRight() != null) {
                    queue.add(queuedNode.getRight());
                }
            }

            if (minNode.getRight() == null) {
                requiredNode.setData(minNode.getData());
                requiredNode.setLeft(null);
            } else {
                requiredNode.setData(minNode.getData());
                minNode = minNode.getRight();
            }
        }

        size--;
    }

    public void traverseWidth() {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        System.out.println(root);

        while (!queue.isEmpty()) {
            TreeNode<T> queuedNode = queue.remove();

            if (queuedNode.getLeft() != null) {
                queue.add(queuedNode.getLeft());
                System.out.print(queuedNode.getLeft());
            }

            if (queuedNode.getRight() != null) {
                queue.add(queuedNode.getRight());
                System.out.print(queuedNode.getRight());
            }

            System.out.println();
        }
    }

    public void traverseDepth() {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.add(root);
        System.out.println(root);

        while (!stack.isEmpty()) {
            TreeNode<T> queuedNode = stack.removeLast();

            if (queuedNode.getRight() != null) {
                stack.addLast(queuedNode.getRight());
                System.out.println(queuedNode.getRight());
            }

            if (queuedNode.getLeft() != null) {
                stack.addLast(queuedNode.getLeft());
                System.out.println(queuedNode.getLeft());
            }

            System.out.println();
        }
    }

    public void traverseDepthRecursion() {
        traverseDepthRecursion(root);
    }

    private void traverseDepthRecursion(TreeNode<T> node) {
        if (root == null) {
            return;
        }

        System.out.println(node);

        if (node.getLeft() != null) {
            traverseDepthRecursion(node.getLeft());
        }

        if (node.getRight() != null) {
            traverseDepthRecursion(node.getRight());
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> queuedNode = queue.remove();
            stringBuilder.append(queuedNode);

            if (queuedNode.getLeft() != null) {
                queue.add(queuedNode.getLeft());
            }

            if (queuedNode.getRight() != null) {
                queue.add(queuedNode.getRight());
            }
        }

        return stringBuilder.toString();
    }
}
