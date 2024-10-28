package ru.ssau.tk.nikitals.oop.functions.factory.impl;

import ru.ssau.tk.nikitals.oop.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;

/**
 * Класс, реализующий фабрику для создания табулированных функций на основе массивов.
 */
public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public ArrayTabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
