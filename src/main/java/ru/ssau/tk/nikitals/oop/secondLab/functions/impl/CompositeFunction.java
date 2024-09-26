package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import ru.ssau.tk.nikitals.oop.secondLab.functions.core.MathFunction;

/**
 * Класс, представляющий композицию двух функций.
 */
public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    /**
     * Конструктор, принимающий две функции для композиции.
     *
     * @param firstFunction первая функция
     * @param secondFunction вторая функция
     */
    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    /**
     * Применяет композицию функций к заданному аргументу.
     * Сначала применяется первая функция, затем вторая.
     *
     * @param x аргумент функции
     * @return результат применения второй функции к результату первой функции
     */
    @Override
    public double apply(double x) {
        return secondFunction.apply(firstFunction.apply(x));
    }
}
