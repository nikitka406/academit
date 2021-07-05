package ru.academits.popov.main;

import ru.academits.popov.range.Range;

public class Main {
    public static void main(String[] args) {
        double from = 4.5;
        double to = 7.9;
        double number = 5;
        Range range = new Range(from, to);

        System.out.println("Начало отрезка = " + range.getFrom());
        System.out.println("Конец отрезка = " + range.getTo());
        System.out.println("Длинна отрезка = " + range.getLength());
        System.out.println("Входит ли точка " + number + " в отрезок " + range.isInside(number));

        range.setFrom(1);
        range.setTo(8);

        System.out.println("Начало отрезка = " + range.getFrom());
        System.out.println("Конец отрезка = " + range.getTo());

        // Range*
        Range range1 = new Range(4, 9);
        Range range2 = new Range(1, 4);

        System.out.print("Пересечение отрезков = ");
        Range rangeResult = range1.getIntersection(range2);//.print();
        if (rangeResult != null) {
            rangeResult.print();
        } else {
            System.out.println((Object) null);
        }

        System.out.print("Объединение отрезков = ");
        Range[] ranges = range1.getUnion(range2);
        for (Range range3 : ranges) {
            if (range3 != null) {
                range3.print();
            } else {
                System.out.println((Object) null);
            }
        }

        System.out.print("Разность отрезков = ");
        ranges = range1.getDifference(range2);
        for (Range range3 : ranges) {
            range3.print();
        }

        if (ranges.length == 0) {
            System.out.println("null");
        }
    }
}
