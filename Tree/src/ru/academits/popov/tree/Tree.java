package ru.academits.popov.tree;

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

    public int getSize() {
        return size;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    private TreeNode<T> getPreviousNode(T data) {
        TreeNode<T> node = root;

        while (node != null) {
            if ((node.getLeft() != null && compare(node.getLeft().getData(), data) == 0) ||
                    (node.getRight() != null && compare(node.getRight().getData(), data) == 0)) {
                return node;
            }

            int result = compare(node.getData(), data);

            if (result == 0) {
                return node;
            } else if (result > 0) {
                node = node.getLeft();

                if (node == null) {
                    return null;
                }
            } else {
                node = node.getRight();

                if (node == null) {
                    return null;
                }
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

        TreeNode<T> requiredNode = root;
        TreeNode<T> previousNode = getPreviousNode(data);

        if (previousNode != null) {
            if (compare(previousNode.getData(), data) > 0) {
                requiredNode = previousNode.getLeft();
            } else {
                requiredNode = previousNode.getRight();
            }
        }

        TreeNode<T> leftNode = requiredNode.getLeft();
        TreeNode<T> rightNode = requiredNode.getRight();

        if (leftNode == null && rightNode == null) {
            // Удаление листа
            if (previousNode == null) {
                root = null;
            } else if (previousNode.getRight() != null) {
                previousNode.setRight(null);
            } else {
                previousNode.setLeft(null);
            }
        } else if (leftNode == null || rightNode == null) {
            TreeNode<T> insertNode = leftNode == null ? rightNode : leftNode;

            // Удаление узла с одним ребенком
            if (previousNode == null) {
                root = insertNode;
            } else if (previousNode.getRight() != null) {
                previousNode.setRight(insertNode);
            } else {
                previousNode.setLeft(insertNode);
            }
        } else {
            TreeNode<T> minNode = requiredNode.getRight();
            TreeNode<T> minPreviousNode = null;

            while (minNode.getLeft() != null) {
                minPreviousNode = minNode;
                minNode = minNode.getLeft();
            }

            if (minPreviousNode != null) {
                minPreviousNode.setLeft(minNode.getRight());
                minNode.setRight(minNode.getRight());
            }

            minNode.setLeft(minNode.getLeft());

            if (previousNode == null) {
                root = minNode;
            } else {
                if (previousNode.getRight() != null) {
                    previousNode.setRight(minNode);
                } else {
                    previousNode.setLeft(minNode);
                }
            }

            size--;
        }
    }

    public void traverseWidth(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> queuedNode = queue.remove();
            consumer.accept(queuedNode.getData());

            if (queuedNode.getLeft() != null) {
                queue.add(queuedNode.getLeft());
            }

            if (queuedNode.getRight() != null) {
                queue.add(queuedNode.getRight());
            }
        }
    }

    public void traverseDepth(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> queuedNode = stack.removeLast();
            consumer.accept(queuedNode.getData());

            if (queuedNode.getRight() != null) {
                stack.addLast(queuedNode.getRight());
            }

            if (queuedNode.getLeft() != null) {
                stack.addLast(queuedNode.getLeft());
            }
        }
    }

    public void traverseDepthRecursion(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        traverseDepthRecursion(root, consumer);
    }

    private void traverseDepthRecursion(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            traverseDepthRecursion(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            traverseDepthRecursion(node.getRight(), consumer);
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
