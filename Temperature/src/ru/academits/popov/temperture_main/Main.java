package ru.academits.popov.temperture_main;

import ru.academits.popov.temperture.model.*;
import ru.academits.popov.temperture.view.View;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = {
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        };

        View view = new View(new Converter(scales));
        view.run();
    }
}
