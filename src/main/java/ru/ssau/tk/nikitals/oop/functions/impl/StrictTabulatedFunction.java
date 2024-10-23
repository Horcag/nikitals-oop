package ru.ssau.tk.nikitals.oop.functions.impl;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

import java.util.Iterator;

/**
 * Класс {@code StrictTabulatedFunction} является строгой оберткой над табулированной функцией.
 * Он реализует интерфейс {@link TabulatedFunction} и переопределяет метод {@link  #apply(double)}}.
 * Если функция не содержит значение {@code x}, то метод {@link #apply(double)} выбрасывает исключение {@link  UnsupportedOperationException}.
 */
public class StrictTabulatedFunction implements TabulatedFunction {
    protected final TabulatedFunction function;

    /**
     * Конструктор, принимающий табулированную функцию.
     *
     * @param function табулированная функция.
     */
    public StrictTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public int getCount() {
        return function.getCount();
    }

    @Override
    public double getX(int index) {
        return function.getX(index);
    }

    @Override
    public double getY(int index) {
        return function.getY(index);
    }

    @Override
    public void setY(int index, double value) {
        function.setY(index, value);
    }

    @Override
    public int indexOfX(double x) {
        return function.indexOfX(x);
    }

    @Override
    public int indexOfY(double y) {
        return function.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return function.leftBound();
    }

    @Override
    public double rightBound() {
        return function.rightBound();
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        return function.iterator();
    }

    /**
     * @throws UnsupportedOperationException если данная функция не содержит значение x
     */
    @Override
    public double apply(double x) {
        int index = function.indexOfX(x);
        if (index == -1) {
            throw new UnsupportedOperationException("x " + x + " is not in the function domain");
        }
        return function.getY(index);
    }
}
