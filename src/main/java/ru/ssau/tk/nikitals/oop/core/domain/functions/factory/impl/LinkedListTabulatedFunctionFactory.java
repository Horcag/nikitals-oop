package ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.MathFunctionInfo;

/**
 * Класс, реализующий фабрику для создания табулированных функций на основе связного списка.
 */
@MathFunctionInfo(name = "Связный список", priority = 1)
public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public LinkedListTabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Override
    public LinkedListTabulatedFunction create(MathFunction function, double xFrom, double xTo, int count) {
        return new LinkedListTabulatedFunction(function, xFrom, xTo, count);
    }

    @Override
    public Class<LinkedListTabulatedFunction> typeClass() {
        return LinkedListTabulatedFunction.class;
    }
}
