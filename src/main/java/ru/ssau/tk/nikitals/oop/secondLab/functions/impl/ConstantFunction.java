package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import ru.ssau.tk.nikitals.oop.secondLab.functions.core.MathFunction;

/**
 * Класс, представляющий константную функцию.
 */
public class ConstantFunction implements MathFunction {
    private final double constant;

    /**
     * Конструктор, принимающий значение константы.
     *
     * @param constant значение константы
     */
    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    /**
     * Применяет эту функцию к заданному аргументу.
     * Возвращает константное значение.
     *
     * @param x аргумент функции (не используется)
     * @return константное значение
     */
    @Override
    public double apply(double x) {
        return constant;
    }

    /**
     * Возвращает значение константы.
     *
     * @return значение константы
     */
    public double getConstant() {
        return constant;
    }
}