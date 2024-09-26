package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import ru.ssau.tk.nikitals.oop.secondLab.functions.core.AbstractTabulatedFunction;
import ru.ssau.tk.nikitals.oop.secondLab.functions.core.MathFunction;

import java.util.Arrays;

/**
 * Класс, представляющий табулированную функцию, реализованную с использованием массивов.
 */
public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    private final int count;

    /**
     * Конструктор, принимающий массивы x и y значений.
     *
     * @param xValues массив значений x
     * @param yValues массив значений y
     * @throws IllegalArgumentException если длина массивов меньше 1 или массивы имеют разную длину,
     *                                  или значения x не упорядочены по возрастанию
     */
    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 1) {
            throw new IllegalArgumentException("Array length less than 1.");
        }
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("Arrays are not the same length.");
        }

        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) {
                throw new IllegalArgumentException("xValues are not in ascending order.");
            }
        }
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = xValues.length;
    }

    /**
     * Конструктор, принимающий функцию, диапазон значений и количество точек.
     *
     * @param source функция для табулирования
     * @param xFrom  начальное значение диапазона
     * @param xTo    конечное значение диапазона
     * @param count  количество точек табуляции
     * @throws IllegalArgumentException если количество точек меньше 1
     */
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count length less than 1.");
        }
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        if (xFrom == xTo) {
            for (int i = 0; i < count; i++) {
                xValues[i] = xFrom;
                yValues[i] = source.apply(xFrom);
            }
            return;
        }
        double step = (xTo - xFrom) / (count - 1);
        double xMoment = xFrom;
        for (int i = 0; i < count; i++) {
            xValues[i] = xMoment;
            yValues[i] = source.apply(xMoment);
            xMoment += step;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    /**
     * @throws IllegalArgumentException если индекс меньше 0 или больше количества точек
     */
    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return xValues[index];
    }

    /**
     * @throws IllegalArgumentException если индекс меньше 0 или больше количества точек
     */
    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return yValues[index];
    }

    /**
     * @throws IllegalArgumentException если индекс меньше 0 или больше количества точек
     */
    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            return 0;
        }
        if (x == xValues[count - 1]) { // 5.?
            return count - 1;
        }
        for (int i = 0; i < count-1; i++) {
            if (x < xValues[i + 1]) {
                return i;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return yValues[0];
        }
//        if (floorIndex == count - 1 || floorIndex == count) {
//            return yValues[count - 1];
//        }
        return interpolate(x, xValues[floorIndex], yValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex + 1]);
    }
}
