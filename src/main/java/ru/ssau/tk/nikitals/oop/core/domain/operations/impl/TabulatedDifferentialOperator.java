package ru.ssau.tk.nikitals.oop.core.domain.operations.impl;

import lombok.Getter;
import lombok.Setter;
import ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl.SynchronizedTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.Point;
import ru.ssau.tk.nikitals.oop.core.domain.operations.api.DifferentialOperator;

/**
 * Класс, реализующий оператор дифференцирования табулированных функций.
 */
@Setter
@Getter
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

    /**
     * Выполняет синхронное дифференцирование табулированной функции.
     * Если переданная функция не является экземпляром {@link SynchronizedTabulatedFunction},
     * она будет обернута в этот класс для обеспечения потокобезопасности.
     *
     * @param function табулированная функция для дифференцирования.
     * @return результат дифференцирования табулированной функции.
     */
    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (!(function instanceof SynchronizedTabulatedFunction)) {
            function = new SynchronizedTabulatedFunction(function);
        }
        TabulatedFunction finalFunction = function;
        return ((SynchronizedTabulatedFunction) function).doSynchronously((this::derive));
    }

    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException("Applying of TabulatedDifferentialOperator is not supported");
    }
}
