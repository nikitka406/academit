package ru.academits.popov.tree_main;

import ru.academits.popov.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<Integer> consumer = node -> System.out.println(node + " ");
        Tree<Integer> tree = new Tree<>(5);
        tree.insert(4);
        tree.insert(6);
        tree.insert(5);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);

        System.out.println("Дерево: " + tree);
        System.out.println("Обход в ширину:");
        tree.traverseWidth(consumer);

        tree.remove(5);
        tree.traverseWidth(consumer);

        System.out.println("Обход в глубину без рекурсии:");
        tree.traverseDepth(consumer);

        System.out.println("Обход в глубину с рекурсии:");
        tree.traverseDepthRecursion(consumer);
    }
}
