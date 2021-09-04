package ru.academits.popov.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileLine = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("ArrayListHome/input.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                fileLine.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла: " + e.getMessage());
        }

        System.out.println("Список сторок, прочитанных из файла = " + fileLine);

        ArrayList<Integer> arrayList1 = new ArrayList<>(Arrays.asList(489, 578, 999, 666, 777, 253, 7, 2));

        Iterator<Integer> iterator = arrayList1.iterator();

        System.out.println("Список чисел до удаления четных = " + arrayList1);

        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println("Список без четных чисел = " + arrayList1);

        ArrayList<Integer> arrayList2 = new ArrayList<>(Arrays.asList(1, 4, 489, 4, 1, 578, 999, 999, 666, 777, 2, 253, 7, 2));

        System.out.println("Список чисел до удаления четных = " + arrayList2);

        ArrayList<Integer> uniqueNumbersList = new ArrayList<>();

        for (int number : arrayList2) {
            if (!uniqueNumbersList.contains(number)) {
                uniqueNumbersList.add(number);
            }
        }

        System.out.println("Список с уникальными значениями = " + uniqueNumbersList);
    }
}
