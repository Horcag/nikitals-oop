package ru.ssau.tk.nikitals.oop.core.domain.operations.api;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;

/**
 * Абстрактный класс {@code SteppingDifferentialOperator} реализует интерфейс {@link DifferentialOperator} и содержит методы для нахождения производной функции.
 */
public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    /**
     * Конструктор, принимающий значение шага.
     *
     * @param step значение шага.
     */
    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step)) {
            throw new IllegalArgumentException("Step is incorrect.");
        }
        this.step = step;
    }


    public void setStep(double step) {
        this.step = step;
    }


    public double getStep() {
        return step;
    }
}
