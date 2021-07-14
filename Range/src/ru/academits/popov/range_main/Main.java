package ru.academits.popov.range_main;

import ru.academits.popov.range.Range;

public class Main {
    public static void printRangesArray(Range[] ranges) {
        System.out.print("[");

        for (int i = 0; i < ranges.length; ++i) {
            System.out.print(ranges[i]);

            if (i != ranges.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    public static void main(String[] args) {
        double from = 4.5;
        double to = 7.9;
        double number = 5;
        Range range1 = new Range(from, to);

        System.out.println("Начало отрезка = " + range1.getFrom());
        System.out.println("Конец отрезка = " + range1.getTo());
        System.out.println("Длинна отрезка = " + range1.getLength());
        System.out.println("Входит ли точка " + number + " в отрезок " + range1.isInside(number));

        range1.setFrom(1);
        range1.setTo(8);

        System.out.println("Начало отрезка = " + range1.getFrom());
        System.out.println("Конец отрезка = " + range1.getTo());

        // Range*
        Range range2 = new Range(3, 5);
        Range range3 = new Range(1, 7);

        System.out.print("Пересечение отрезков = ");
        Range intersection = range2.getIntersection(range3);

        if (intersection != null) {
            System.out.println(intersection);
        } else {
            System.out.println("null");
        }

        System.out.print("Объединение отрезков = ");
        Range[] union = range2.getUnion(range3);
        printRangesArray(union);

        System.out.print("Разность отрезков = ");
        Range[] difference = range2.getDifference(range3);
        printRangesArray(difference);
    }
}
