package ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.MathFunctionInfo;

/**
 * Класс, реализующий фабрику для создания табулированных функций на основе массивов.
 */
@MathFunctionInfo(name = "Массив", priority = 0)
public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public ArrayTabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Override
    public ArrayTabulatedFunction create(MathFunction function, double xFrom, double xTo, int count) {
        return new ArrayTabulatedFunction(function, xFrom, xTo, count);
    }

    @Override
    public Class<ArrayTabulatedFunction> typeClass() {
        return ArrayTabulatedFunction.class;
    }


}
