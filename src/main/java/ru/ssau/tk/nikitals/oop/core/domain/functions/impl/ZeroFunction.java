package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.MathFunctionInfo;

/**
 * Класс, представляющий функцию, которая всегда возвращает {@code 0}.
 */
@MathFunctionInfo(name = "Нулевая функция", priority = 0)
public class ZeroFunction extends ConstantFunction {
    /**
     * Конструктор, создающий функцию, возвращающую {@code 0}.
     */
    public ZeroFunction(){
        super(0);
    }
}
