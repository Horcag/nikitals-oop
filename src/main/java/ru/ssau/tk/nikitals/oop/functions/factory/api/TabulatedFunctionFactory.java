package ru.ssau.tk.nikitals.oop.functions.factory.api;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.StrictTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.UnmodifiableTabulatedFunction;

/**
 * Интерфейс {@code TabulatedFunctionFactory} содержит методы для создания табулированных функций.
 */
public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues) {
        return new StrictTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues) {
        return new UnmodifiableTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues) {
        return new StrictTabulatedFunction(createUnmodifiable(xValues, yValues));
    }
}
