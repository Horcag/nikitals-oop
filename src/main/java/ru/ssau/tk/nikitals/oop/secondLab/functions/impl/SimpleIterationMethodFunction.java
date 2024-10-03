package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import ru.ssau.tk.nikitals.oop.secondLab.functions.api.MathFunction;

/**
 * Класс, реализующий метод простых итераций для нахождения корня функции.
 */
public class SimpleIterationMethodFunction implements MathFunction {
    private final MathFunction function;
    private final double tolerance;
    private final int maxIterations;
    private final double minBound;
    private final double maxBound;
    private double rootValue;
    private int iterationCount;

    /**
     * Конструктор, принимающий функцию, начальное приближение, допустимую погрешность и максимальное количество итераций.
     *
     * @param function      функция, для которой нужно найти корень
     * @param tolerance     допустимая погрешность
     * @param maxIterations максимальное количество итераций
     * @param maxBound      верхняя граница
     * @param minBound      нижняя граница
     */
    public SimpleIterationMethodFunction(MathFunction function, double tolerance, int maxIterations, double minBound, double maxBound) {
        this.function = function;
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
        this.minBound = minBound;
        this.maxBound = maxBound;
        if (maxIterations < 0) {
            throw new IllegalArgumentException("Количество итераций не может быть отрицательным.");
        }
    }

    /**
     * Метод, возвращающий найденный корень или выбрасывающий исключение, если корень не найден.
     *
     * @param initialGuess  начальное приближение
     * @return найденный корень
     * @throws IllegalStateException если корень не найден
     */
    @Override
    public double apply(double initialGuess) {
        if (simpleIteration(initialGuess)) {
            return rootValue;
        } else {
            throw new IllegalStateException("Корень не найден в пределах заданных ограничений.");
        }
    }

    /**
     * Метод, реализующий процесс простых итераций для нахождения корня функции.
     *
     * @return {@code true}, если корень найден, иначе {@code false}
     */
    public boolean simpleIteration(double initialGuess) {
        double currentGuess = initialGuess;
        int iteration = 0;

        while (true) {
            iteration++;
            double nextGuess = function.apply(currentGuess); // Обновляем currentGuess с использованием функции
            if (nextGuess > maxBound || nextGuess < minBound) return false;
            if (iteration >= this.maxIterations) return false;
            if (Math.abs(nextGuess - currentGuess) < this.tolerance) break;

            currentGuess = nextGuess;
        }

        this.iterationCount = iteration;
        this.rootValue = currentGuess;
        return true;
    }

    /**
     * Метод, возвращающий найденный корень.
     *
     * @return найденный корень
     */
    public double getResult() {
        return rootValue;
    }

    /**
     * Метод, возвращающий точность найденного корня.
     *
     * @return точность найденного корня
     */
    public double getEps() {
        return Math.abs(function.apply(rootValue) - rootValue);
    }

    /**
     * Метод, возвращающий количество итераций, потребовавшихся для нахождения корня.
     *
     * @return количество итераций
     */
    public int getIterations() {
        return iterationCount;
    }
}