package ru.ssau.tk.nikitals.oop.secondLab.functions;

/**
 * Интерфейс, представляющий математическую функцию.
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
     * Возвращает композицию функций, которая сначала применяет эту функцию, а затем применяет afterFunction.
     *
     * @param afterFunction функция, которая будет применена после этой функции
     * @return композиция функций, которая сначала применяет эту функцию, а затем afterFunction
     */
    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);

    }
}
