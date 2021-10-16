package ru.academits.popov.temperture.model;

public class Converter {
    private final Scale[] scales;

    public Converter(Scale[] scales) {
        this.scales = scales;
    }

    public double getResultConvert(Scale inputScale, Scale outScale, double inputTemperature) {
        double celsiusTemperature = inputScale.convertToCelsius(inputTemperature);
        return outScale.convertFromCelsius(celsiusTemperature);
    }

    public Scale[] getScales() {
        return scales;
    }
}
