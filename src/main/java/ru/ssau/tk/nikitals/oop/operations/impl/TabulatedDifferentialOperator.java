package ru.ssau.tk.nikitals.oop.operations.impl;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.Point;
import ru.ssau.tk.nikitals.oop.operations.api.DifferentialOperator;

/**
 * Класс, реализующий оператор дифференцирования табулированных функций.
 */
public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    protected TabulatedFunctionFactory factory;

    /**
     * Конструктор по умолчанию, создающий фабрику для создания табулированных функций на основе массивов.
     */
    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    /**
     * Конструктор, принимающий фабрику для создания табулированных функций.
     *
     * @param factory фабрика для создания табулированных функций.
     */
    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];
        for (int i = 0; i < points.length - 1; i++) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }
        xValues[points.length - 1] = points[points.length - 1].x;
        yValues[points.length - 1] = yValues[points.length - 2];
        return factory.create(xValues, yValues);
    }

    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException("Applying of TabulatedDifferentialOperator is not supported");
    }
}
