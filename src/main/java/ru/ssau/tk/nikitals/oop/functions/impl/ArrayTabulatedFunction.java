package ru.ssau.tk.nikitals.oop.functions.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.nikitals.oop.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.nikitals.oop.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.nikitals.oop.exceptions.ArrayHasDuplicateElementsException;
import ru.ssau.tk.nikitals.oop.exceptions.InterpolationException;
import ru.ssau.tk.nikitals.oop.functions.api.AbstractTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.api.Insertable;
import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.functions.api.Removable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, представляющий табулированную функцию, реализованную с использованием массивов.
 */
public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {
    @Serial
    private static final long serialVersionUID = 3077297132301647066L;

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private double[] xValues;

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private double[] yValues;

     private int count;

    /**
     * Конструктор, принимающий массивы {@code x} и {@code y} значений.
     *
     * @param xValues массив значений {@code x}
     * @param yValues массив значений {@code y}
     * @throws IllegalArgumentException           если длина массивов меньше <b>2</b>
     * @throws DifferentLengthOfArraysException   если длины массивов не совпадают
     * @throws ArrayIsNotSortedException          если массив {@code xValues} не отсортирован
     * @throws ArrayHasDuplicateElementsException если массив {@code xValues} содержит дубликаты
     */
    @JsonCreator
    public ArrayTabulatedFunction(@JsonProperty("xValues") double[] xValues, @JsonProperty("yValues") double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array length less than 2.");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSortedAndDuplicate(xValues);
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = xValues.length;
    }

    /**
     * Конструктор, принимающий функцию, диапазон значений и количество точек.
     * <ui>
     * <li> Если {@code xFrom} больше {@code xTo}, то значения меняются местами.
     * <li> Если {@code xFrom} равен {@code xTo}, то все точки будут иметь одинаковое значение.
     * </ui>
     *
     * @param source функция для табулирования
     * @param xFrom  начальное значение диапазона
     * @param xTo    конечное значение диапазона
     * @param count  количество точек табуляции
     * @throws IllegalArgumentException если количество точек меньше <b>2</b>
     */
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Count length less than 2.");
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
            double value = source.apply(xFrom);
            for (int i = 0; i < count; i++) {
                xValues[i] = xFrom;
                yValues[i] = value;
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
     * @throws IllegalStateException если пытаемся удалить предпоследний элемент
     */
    @Override
    public void remove(int index) {
        if (count == 2) {
            throw new IllegalStateException("The penultimate point cannot be deleted. There must be at least 2 points.");
        }
        System.arraycopy(xValues, index + 1, xValues, index, count - index - 1);
        System.arraycopy(yValues, index + 1, yValues, index, count - index - 1);
        count--;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
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
            throw new IllegalArgumentException("X is less than left bound of function.");
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
        return interpolate(x, xValues[0], yValues[0], xValues[1], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], yValues[count - 2], xValues[count - 1], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (0 <= floorIndex && floorIndex < xValues.length) {
            if (xValues[floorIndex] == x) {
                return yValues[floorIndex];
            }
            if (!(xValues[floorIndex] <= x && x <= xValues[floorIndex + 1])) {
                throw new InterpolationException("X is out of bounds of interpolation.");
            }
        } else {
            throw new InterpolationException("X is out of bounds of interpolation.");
        }
        return interpolate(x, xValues[floorIndex], yValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex + 1]);
    }


    @NotNull
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                return index + 1 < count;
            }

            @Override
            public Point next() {
                index++;
                if (index < count) {
                    return new Point(xValues[index], yValues[index]);
                }
                throw new NoSuchElementException("No such element.");
            }
        };
    }
}
