package ru.academits.popov.temperture.model;

public interface Scale {
    double convertFromCelsius(double temperature);

    double convertToCelsius(double temperature);
}
