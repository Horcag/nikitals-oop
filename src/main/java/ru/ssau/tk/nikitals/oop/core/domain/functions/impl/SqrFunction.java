package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.MathFunctionInfo;

import static java.lang.Math.pow;

/**
 * Класс, представляющий функцию возведения в квадрат.
 */
@MathFunctionInfo(name = "Квадратичная функция", priority = 1)
public class SqrFunction implements MathFunction {
    /**
     * Применяет эту функцию к заданному аргументу.
     * <p>
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
