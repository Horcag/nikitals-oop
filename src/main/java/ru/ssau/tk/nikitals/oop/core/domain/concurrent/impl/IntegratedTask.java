package ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;

import java.util.concurrent.Callable;

public class IntegratedTask implements Callable<Double> {
    TabulatedFunction func;
    double a;
    double b;
    double steps;

    /**
     * интегрирования методом Симпсона
     *
     * @param func  - функция, которую интегрируем
     * @param a     - нижний предел интегрирования
     * @param b     - верхний предел интегрирования
     * @param steps - количество шагов
     */
    public IntegratedTask(TabulatedFunction func, double a, double b, int steps) {
        this.func = func;
        this.a = a;
        this.b = b;
        this.steps = steps;
    }

    @Override
    public Double call() {
        double h = (b - a) / steps;
        double sum = func.apply(a) + func.apply(b);
        for (int i = 1; i < steps; i += 2) {
            sum += 4 * func.apply(a + h * i);
        }
        for (int i = 2; i < steps - 1; i += 2) {
            sum += 2 * func.apply(a + h * i);
        }
        return sum * h / 3.0;

    }
}
