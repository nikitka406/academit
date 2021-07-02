package ru.academits.popov.range;

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
    }
}
