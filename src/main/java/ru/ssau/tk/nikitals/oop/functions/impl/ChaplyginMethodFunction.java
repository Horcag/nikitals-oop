package ru.ssau.tk.nikitals.oop.functions.impl;

import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;

import java.util.function.BiFunction;

public class ChaplyginMethodFunction implements MathFunction {
    private static final double LIPSCHITZ_CONSTANT = 1.0;  // Постоянная Липшица L = 1
    private double tolerance;  // Погрешность, при которой остановим итерации
    BiFunction<Double, Double, Double> f;    // ОДУ y' = f(x, y)
    double x0;
    double x;
    double u0;
    double v0;
    int maxIterations;
    int steps;

    /**
     * Интегрирования методом Симпсона
     * @param func - функция, которую интегрируем
     * @param a - нижний предел интегрирования
     * @param b - верхний предел интегрирования
     * @param steps - количество шагов
     * @param u_n - начальное значение u
     * @return - значение интеграла
     */
    public static double integrateSimpson(BiFunction<Double, Double, Double> func, double a, double b, int steps, double u_n) {
        double h = (b - a) / steps;
        double sum = func.apply(a, u_n) + func.apply(b, u_n);
        for (int i = 1; i < steps; i += 2) {
            double x = a + h * i;
            sum += 4 * func.apply(x, u_n);
        }
        for (int i = 2; i < steps - 1; i += 2) {
            double x = a + h * i;
            sum += 2 * func.apply(x, u_n);
        }
        return sum * h / 3.0;
    }

    ChaplyginMethodFunction(BiFunction<Double, Double, Double> f, double x0, double u0, double v0, int maxIterations, int steps, double tolerance) {
        this.f = f;
        this.x0 = x0;
        this.u0 = u0;
        this.v0 = v0;
        this.maxIterations = maxIterations;
        this.steps = steps;
        this.tolerance = tolerance;
    }

     //Метод Чаплыгина
    @Override
    public double apply(double x) {
        double u_n = u0;
        double v_n = v0;

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            double u_n1 = calculateNextU(x, u_n);
            double v_n1 = calculateNextV(x, v_n);

            // Проверяем условие завершения итераций
            if (Math.abs(v_n1 - u_n1) < tolerance) {
                return (u_n1 + v_n1) / 2;  // Полусумма приближений
            }

            u_n = u_n1;
            v_n = v_n1;
        }

        // Возвращаем результат после максимального числа итераций
        return (u_n + v_n) / 2;
    }
    private double calculateNextU(double x, double u_prev) {
        return u_prev + integrateSimpson((t, u) ->
                        Math.exp(-LIPSCHITZ_CONSTANT * (x - t)) * (f.apply(t, u_prev) - derivative(f, t, u_prev)),
                x0, x, steps, u_prev);
    }

    private double calculateNextV(double x, double v_prev) {
        return v_prev + integrateSimpson((t, v) ->
                        Math.exp(-LIPSCHITZ_CONSTANT * (x - t)) * (derivative(f, t, v_prev) - f.apply(t, v_prev)),
                x0, x, steps, v_prev);
    }


    // Приближенная производная (численно)
    public static double derivative(BiFunction<Double, Double, Double> f, double x, double y) {
        double h = 1e-8;
        return (f.apply(x, y + h) - f.apply(x, y - h)) / (2 * h);
    }

}
