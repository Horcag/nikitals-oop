package ru.ssau.tk.nikitals.oop.operations.impl;

import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.operations.api.SteppingDifferentialOperator;

/**
 * Класс, реализующий оператор дифференцирования с постоянным шагом, использующий левостороннюю разность.
 */
public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {
    /**
     * Конструктор, создающий оператор дифференцирования с постоянным шагом, использующий левостороннюю разность.
     *
     * @param step шаг дифференцирования.
     */
    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x) - function.apply(x - step)) / step;
    }

    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException("Applying of LeftSteppingDifferentialOperator is not supported.");
    }
}
