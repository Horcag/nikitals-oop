package ru.ssau.tk.nikitals.oop.functions.impl;

import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;

/**
 * Класс, представляющий функцию идентичности.
 */

public class IdentityFunction implements MathFunction {
    /**
     * Применяет эту функцию к заданному аргументу.
     * <p>
     * Возвращает тот же аргумент.
     *
     * @param x аргумент функции
     * @return тот же аргумент
     */
    @Override
    public double apply(double x) {
        return x;
    }
}
