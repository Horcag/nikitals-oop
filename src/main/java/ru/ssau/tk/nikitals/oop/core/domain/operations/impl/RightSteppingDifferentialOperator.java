package ru.ssau.tk.nikitals.oop.core.domain.operations.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.operations.api.SteppingDifferentialOperator;

/**
 * Класс, реализующий оператор дифференцирования с постоянным шагом, использующий правостороннюю разность.
 */
public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {
    /**
     * Конструктор, создающий оператор дифференцирования с постоянным шагом, использующий правостороннюю разность.
     *
     * @param step шаг дифференцирования.
     */
    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x)) / step;
    }

    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException("Applying of RightSteppingDifferentialOperator is not supported.");
    }
}
