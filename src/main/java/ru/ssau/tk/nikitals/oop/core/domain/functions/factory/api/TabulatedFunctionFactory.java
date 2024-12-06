package ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.StrictTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.UnmodifiableTabulatedFunction;

/**
 * Интерфейс {@code TabulatedFunctionFactory} содержит методы для создания табулированных функций.
 */
public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction function, double xFrom, double xTo, int count);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues) {
        return new StrictTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues) {
        return new UnmodifiableTabulatedFunction(create(xValues, yValues));
    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues) {
        return new StrictTabulatedFunction(createUnmodifiable(xValues, yValues));
    }

    /**
     * Возвращает класс создаваемой функции.
     * @return класс создаваемой функции
     */
    Class<? extends TabulatedFunction> typeClass();
}
