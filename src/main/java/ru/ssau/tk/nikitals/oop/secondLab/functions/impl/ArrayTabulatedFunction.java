package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import ru.ssau.tk.nikitals.oop.secondLab.functions.core.AbstractTabulatedFunction;
import ru.ssau.tk.nikitals.oop.secondLab.functions.core.Insertable;
import ru.ssau.tk.nikitals.oop.secondLab.functions.core.MathFunction;
import ru.ssau.tk.nikitals.oop.secondLab.functions.core.Removable;

import java.util.Arrays;

/**
 * Класс, представляющий табулированную функцию, реализованную с использованием массивов.
 */
public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    double[] xValues;
    double[] yValues;
    private int count;

    /**
     * Конструктор, принимающий массивы {@code x} и {@code y} значений.
     *
     * @param xValues массив значений {@code  x}
     * @param yValues массив значений {@code y}
     * @throws IllegalArgumentException если длина массивов меньше <b>1</b> или массивы имеют разную длину,
     *                                  или значения {@code xValues} не упорядочены по возрастанию, или имеют повторяющиеся элементы
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
                throw new IllegalArgumentException("xValues are not in ascending order or have repeating elements.");
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
     * @throws IllegalArgumentException если количество точек меньше <b>1</b>
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

    /**
     * Метод, увеличивающий размер массивов {@code xValues} и {@code yValues} вдвое или до значения {@code minCapacity},
     * если текущий размер умноженный на <b>2</b> меньше {@code minCapacity}.
     *
     * @param minCapacity минимальная вместимость массивов
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > xValues.length) {
            int newCapacity = Math.max(minCapacity, xValues.length * 2);
            xValues = Arrays.copyOf(xValues, newCapacity);
            yValues = Arrays.copyOf(yValues, newCapacity);
        }
    }

    @Override
    public void insert(double x, double y) {
        int index = Arrays.binarySearch(xValues, 0, count, x);
        if (index >= 0) {
            yValues[index] = y;
        } else {
            int insertIndex = -index - 1;
            ensureCapacity(count + 1);
            if (insertIndex != count) {
                System.arraycopy(xValues, insertIndex, xValues, insertIndex + 1, count - insertIndex);
                System.arraycopy(yValues, insertIndex, yValues, insertIndex + 1, count - insertIndex);
            }
            xValues[insertIndex] = x;
            yValues[insertIndex] = y;
            count++;
        }
    }

    /**
     * @throws IllegalStateException если пытаемся удалить последний элемент функции
     * @throws IndexOutOfBoundsException если индекс меньше <b>0</b> или больше количества точек
     */
    @Override
    public void remove(int index) {
        if (count == 1) {
            throw new IllegalStateException("Can't remove last element of function.");
        }
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        System.arraycopy(xValues, index + 1, xValues, index, count - index - 1);
        System.arraycopy(yValues, index + 1, yValues, index, count - index - 1);
        count--;
    }

    @Override
    public int getCount() {
        return count;
    }

    /**
     * @throws IndexOutOfBoundsException если индекс меньше <b>0</b> или больше количества точек
     */
    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return xValues[index];
    }

    /**
     * @throws IndexOutOfBoundsException если индекс меньше <b>0</b> или больше количества точек
     */
    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return yValues[index];
    }

    /**
     * @throws IndexOutOfBoundsException если индекс меньше <b>0</b> или больше количества точек
     */
    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
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
        for (int i = 0; i < count - 1; i++) {
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
