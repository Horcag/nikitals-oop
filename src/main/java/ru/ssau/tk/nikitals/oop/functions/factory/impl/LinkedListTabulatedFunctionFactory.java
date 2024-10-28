package ru.ssau.tk.nikitals.oop.functions.factory.impl;

import ru.ssau.tk.nikitals.oop.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunction;

/**
 * Класс, реализующий фабрику для создания табулированных функций на основе связного списка.
 */
public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public LinkedListTabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
