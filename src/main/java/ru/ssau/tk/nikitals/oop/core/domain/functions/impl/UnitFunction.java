package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.MathFunctionInfo;

/**
 * Класс, представляющий функцию, которая всегда возвращает {@code 1}.
 */
@MathFunctionInfo(name = "Единичная функция", priority = 1)
public class UnitFunction extends ConstantFunction {
    /**
     * Конструктор, создающий функцию, возвращающую {@code 1}.
     */
    public UnitFunction() {
        super(1);
    }
}
