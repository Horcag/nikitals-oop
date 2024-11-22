package ru.ssau.tk.nikitals.oop.core.domain.operations.api;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;

/**
 * Интерфейс {@code DifferentialOperator} содержит метод для нахождения производной функции.
 */
public interface DifferentialOperator <T> extends MathFunction {
    /**
     * Метод, находящий производную функции.
     *
     * @param function функция, производную которой нужно найти.
     * @return производная функции.
     */
    T derive(T function);
}
