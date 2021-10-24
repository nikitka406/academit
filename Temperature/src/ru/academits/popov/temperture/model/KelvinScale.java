package ru.academits.popov.temperture.model;

public class KelvinScale implements Scale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public String toString() {
        return "Шкала Кельвина";
    }
}
