package ru.academits.popov.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("ArrayListHome/input.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла: " + e.getMessage());
        }

        System.out.println("Список строк, прочитанных из файла = " + fileLines);

        ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(489, 578, 999, 666, 4, 777, 253, 7, 2));

        System.out.println("Список чисел до удаления четных = " + integers1);

        for (int i = 0; i < integers1.size(); ++i) {
            if (integers1.get(i) % 2 == 0) {
                integers1.remove(i);
                i--;
            }
        }

        System.out.println("Список без четных чисел = " + integers1);

        ArrayList<Integer> integers2 = new ArrayList<>(Arrays.asList(1, 4, 489, 4, 1, 578, 999, 999, 666, 777, 2, 253, 7, 2));

        System.out.println("Список чисел до удаления четных = " + integers2);

        ArrayList<Integer> uniqueNumbersList = new ArrayList<>(integers2.size());

        for (Integer number : integers2) {
            if (!uniqueNumbersList.contains(number)) {
                uniqueNumbersList.add(number);
            }
        }

        System.out.println("Список с уникальными значениями = " + uniqueNumbersList);
    }
}
