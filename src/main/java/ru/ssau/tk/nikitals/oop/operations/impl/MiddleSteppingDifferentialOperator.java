package ru.ssau.tk.nikitals.oop.operations.impl;

import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.operations.api.SteppingDifferentialOperator;

/**
 * Класс, реализующий оператор дифференцирования с постоянным шагом, использующий среднюю разность.
 */
public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {
    /**
     * Конструктор, создающий оператор дифференцирования с постоянным шагом, использующий среднюю разность.
     *
     * @param step шаг дифференцирования.
     */
    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException("Applying of MiddleSteppingDifferentialOperator is not supported.");
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x - step)) / (2 * step);
    }
}
