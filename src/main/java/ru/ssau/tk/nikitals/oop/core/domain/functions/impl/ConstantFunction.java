package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import lombok.Getter;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;

/**
 * Класс, представляющий константную функцию.
 */
@Getter
public class ConstantFunction implements MathFunction {
    /**
     * -- GETTER --
     *  Возвращает значение константы.
     *
     * @return значение константы
     */
    private final double constant;

    /**
     * Конструктор, принимающий значение константы.
     * <p>Значение константы не может быть изменено после создания объекта.
     * @param constant значение константы
     */
    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    /**
     * Применяет эту функцию к заданному аргументу.
     * <p>
     * Возвращает константное значение.
     *
     * @param x аргумент функции (не используется)
     * @return константное значение
     */
    @Override
    public double apply(double x) {
        return constant;
    }

}
