package ru.ssau.tk.nikitals.oop.core.domain.operations.impl;

import lombok.Getter;
import lombok.Setter;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.Point;

/**
 * Класс {@code TabulatedFunctionOperationService} предоставляет методы для выполнения
 * различных операций над табулированными функциями, таких как сложение, вычитание,
 * умножение и деление. Класс использует фабрику для создания новых табулированных функций.
 */
@Setter
@Getter
public class TabulatedFunctionOperationService {
    private interface BiOperation {
        double apply(double u, double v);
    }
    protected TabulatedFunctionFactory factory;

    /**
     * Конструктор по умолчанию, создающий фабрику для создания табулированных функций на основе массивов.
     */
    public TabulatedFunctionOperationService() {
        factory = new ArrayTabulatedFunctionFactory();
    }


    /**
     * Конструктор, принимающий фабрику для создания табулированных функций.
     *
     * @param factory фабрика для создания табулированных функций.
     */
    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод, преобразующий табулированную функцию в массив точек.
     *
     * @param tabulatedFunction табулированная функция.
     * @return массив точек.
     */
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            points[i++] = point;
        }
        return points;
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        Point[] aPoints = asPoints(a);
        Point[] bPoints = asPoints(b);
        if (aPoints.length != bPoints.length) {
            throw new InconsistentFunctionsException("The number of points in the functions is different.");
        }
        double[] xValues = new double[aPoints.length];
        double[] yValues = new double[aPoints.length];
        for (int i = 0; i < aPoints.length; i++) {
            if (aPoints[i].x != bPoints[i].x) {
                throw new InconsistentFunctionsException("The x values of the functions are different.");
            }
            xValues[i] = aPoints[i].x;
            yValues[i] = operation.apply(aPoints[i].y, bPoints[i].y);
        }
        return factory.create(xValues, yValues);
    }

    /**
     * Метод, складывающий значения функций в точках.
     *
     * @param a первая табулированная функция.
     * @param b вторая табулированная функция.
     * @return табулированная функция, являющаяся результатом сложения значений функций в точках.
     */
    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, Double::sum);
    }

    /**
     * Метод, вычитающий значения функций в точках.
     *
     * @param a первая табулированная функция.
     * @param b вторая табулированная функция.
     * @return табулированная функция, являющаяся результатом вычитания значений функций в точках.
     */
    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    /**
     * Метод, умножающий значения функций в точках.
     *
     * @param a первая табулированная функция.
     * @param b вторая табулированная функция.
     * @return табулированная функция, являющаяся результатом умножения значений функций в точках.
     */
    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    /**
     * Метод, делящий значения функций в точках.
     *
     * @param a первая табулированная функция.
     * @param b вторая табулированная функция.
     * @return табулированная функция, являющаяся результатом деления значений функций в точках.
     */
    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }
}
