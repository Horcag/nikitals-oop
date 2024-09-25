package ru.ssau.tk.nikitals.oop.secondLab.functions;

/**
 * Класс, представляющий функцию идентичности.
 */

public class IdentityFunction implements MathFunction{
    /**
     * Применяет эту функцию к заданному аргументу.
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
