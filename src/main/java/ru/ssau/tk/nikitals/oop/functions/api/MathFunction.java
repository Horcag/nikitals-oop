package ru.ssau.tk.nikitals.oop.functions.api;

import ru.ssau.tk.nikitals.oop.functions.impl.CompositeFunction;

/**
 * Интерфейс, представляющий математические функции.
 */
public interface MathFunction {
    /**
     * Применяет эту функцию к заданному аргументу.
     *
     * @param x аргумент функции
     * @return результат функции
     */
    double apply(double x);

    /**
     * Возвращает композицию этой функции и указанной функции.
     *
     * @param afterFunction функция, которая будет применена после этой функции
     * @return композиция функций
     */
    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);

    }
}
