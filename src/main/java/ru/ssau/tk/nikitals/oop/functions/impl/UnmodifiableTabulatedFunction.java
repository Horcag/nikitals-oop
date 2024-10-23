package ru.ssau.tk.nikitals.oop.functions.impl;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

import java.util.Iterator;

/**
 * Класс {@code UnmodifiableTabulatedFunction} является неизменяемой оберткой над табулированной функцией.
 * Он реализует интерфейс {@link TabulatedFunction} и переопределяет метод {@link #setY(int, double)}}.
 * Если попытаться изменить значения функции, то метод {@link #setY(int, double)} выбрасывает исключение {@link UnsupportedOperationException}.
 */
public class UnmodifiableTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction function;

    /**
     * Конструктор, принимающий табулированную функцию.
     *
     * @param function табулированная функция.
     */
    public UnmodifiableTabulatedFunction(TabulatedFunction function) {
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

    /**
     * @throws UnsupportedOperationException если попытаться изменить значения функции
     */
    @Override
    public void setY(int index, double value) {
        throw new UnsupportedOperationException("Can't change values of unmodifiable function");
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

    @Override
    public double apply(double x) {
        return function.apply(x);
    }
}
