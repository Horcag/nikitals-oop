package ru.ssau.tk.nikitals.oop.secondLab.functions;

import static java.lang.Math.pow;

/**
 * Класс, представляющий функцию возведения в квадрат.
 */
public class SqrFunction implements MathFunction {
    /**
     * Применяет эту функцию к заданному аргументу.
     * Возвращает квадрат аргумента.
     *
     * @param x аргумент функции
     * @return результат возведения аргумента в квадрат
     */
    @Override
    public double apply(double x) {
        return pow(x, 2);
    }

}
