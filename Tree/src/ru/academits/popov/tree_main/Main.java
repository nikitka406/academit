package ru.academits.popov.tree_main;

import ru.academits.popov.tree.Tree;

public class Main {
    public static void main(String[] args) {
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
        tree.traverseWidth();

        tree.remove(2);
        tree.traverseWidth();

        System.out.println("Обход в глубину без рекурсии:");
        tree.traverseDepth();

        System.out.println("Обход в глубину с рекурсии:");
        tree.traverseDepthRecursion();
    }
}
