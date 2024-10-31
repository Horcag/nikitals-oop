package ru.ssau.tk.nikitals.oop.concurrent;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.Point;
import ru.ssau.tk.nikitals.oop.operations.api.DifferentialOperator;
import ru.ssau.tk.nikitals.oop.operations.impl.TabulatedFunctionOperationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Класс {@code SynchronizedTabulatedFunction} представляет собой потокобезопасную обертку
 * для табулированной функции.
 */
public class SynchronizedTabulatedFunction implements TabulatedFunction {
    protected final TabulatedFunction function;
    protected final Object mutex;

    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
        mutex = this;
    }

    @FunctionalInterface
    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction);
    }

    public <T> T doSynchronously(Operation<? extends T> operation) {
        return operation.apply(this);
    }

    @Override
    public int getCount() {
        synchronized (mutex) {
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (mutex) {
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (mutex) {
            return function.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (mutex) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (mutex) {
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (mutex) {
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (mutex) {
            return function.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (mutex) {
            return function.rightBound();
        }
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        Point[] points;
        synchronized (mutex) {
            points = TabulatedFunctionOperationService.asPoints(function);
        }
        return new Iterator<Point>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < points.length;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                return points[index++];
            }
        };
    }

    @Override
    public double apply(double x) {
        synchronized (mutex) {
            return function.apply(x);
        }
    }
}
