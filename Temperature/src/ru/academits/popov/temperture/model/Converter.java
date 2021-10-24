package ru.academits.popov.temperture.model;

public class Converter {
    private final Scale[] scales;

    public Converter(Scale[] scales) {
        this.scales = scales;
    }

    public double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature) {
        double celsiusTemperature = inputScale.convertToCelsius(inputTemperature);
        return outputScale.convertFromCelsius(celsiusTemperature);
    }

    public Scale[] getScales() {
        return scales;
    }
}
