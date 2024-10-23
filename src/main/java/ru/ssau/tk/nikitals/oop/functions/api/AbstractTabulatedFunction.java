package ru.ssau.tk.nikitals.oop.functions.api;

import ru.ssau.tk.nikitals.oop.exceptions.ArrayHasDuplicateElementsException;
import ru.ssau.tk.nikitals.oop.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.nikitals.oop.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.nikitals.oop.functions.impl.Point;

/**
 * Абстрактный класс, представляющий табулированную функцию.
 */
public abstract class AbstractTabulatedFunction implements TabulatedFunction, MathFunction {

    /**
     * Возвращает индекс аргумента, ближайшего меньшего или равного значению аргумента {@code x}.
     * <ul>
     * <li>Если {@code x} больше всех табулированных аргументов, возвращается индекс последнего аргумента.
     * <li>Если {@code x} совпадает с одним из табулированных аргументов, возвращается индекс этого аргумента.
     * <li>Если {@code x} находится между табулированными аргументами, возвращается индекс аргумента, ближайшего меньшего.
     * </ul>
     *
     * @param x значение аргумента для поиска
     * @return индекс аргумента, ближайшего меньшего или равного значению аргумента {@code x}
     * @throws IllegalArgumentException если {@code x} меньше всех табулированных аргументов
     */
    abstract protected int floorIndexOfX(double x);

    /**
     * Экстраполирует значение функции слева от табулированных значений.
     *
     * @param x значение аргумента для экстраполяции
     * @return экстраполированное значение функции
     */
    abstract protected double extrapolateLeft(double x);

    /**
     * Экстраполирует значение функции справа от табулированных значений.
     *
     * @param x значение аргумента для экстраполяции
     * @return экстраполированное значение функции
     */
    abstract protected double extrapolateRight(double x);

    /**
     * Интерполирует значение функции для заданного аргумента {@code x}.
     *
     * @param x          значение аргумента для интерполяции
     * @param floorIndex индекс аргумента, ближайшего меньшего или равного значению аргумента {@code x}
     * @return интерполированное значение функции
     */
    abstract protected double interpolate(double x, int floorIndex);

    /**
     * Интерполирует значение функции для заданного аргумента {@code x}.
     *
     * @param x      значение аргумента для интерполяции
     * @param leftX  значение аргумента слева от интерполируемого значения
     * @param leftY  значение функции слева от интерполируемого значения
     * @param rightX значение аргумента справа от интерполируемого значения
     * @param rightY значение функции справа от интерполируемого значения
     * @return интерполированное значение функции
     */
    protected double interpolate(double x, double leftX, double leftY, double rightX, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    /**
     * Применяет табулированную функцию к заданному аргументу {@code x}.
     * <ul>
     * <li>Если {@code x} меньше всех табулированных аргументов (меньше левой границы), выполняется экстраполяция слева.
     * <li>Если {@code x} больше всех табулированных аргументов (больше правой границы), выполняется экстраполяция справа.
     * <li>Если {@code x} совпадает с одним из табулированных аргументов, возвращается соответствующее значение функции.
     * <li>Если {@code x} находится между табулированными аргументами, выполняется интерполяция.
     * </ul>
     *
     * @param x аргумент функции
     * @return значение функции в точке {@code x}
     */
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }
        return interpolate(x, floorIndexOfX(x));
    }

    /**
     * Проверяет, что массивы {@code xValues} и {@code yValues} имеют одинаковую длину.
     *
     * @param xValues массив аргументов функции
     * @param yValues массив значений функции
     * @throws DifferentLengthOfArraysException если длины массивов различны
     */
    static protected void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Arrays' lengths are different: xValues.length = " + xValues.length + ", yValues.length = " + yValues.length);
        }
    }

    /**
     * Проверяет, что массив {@code xValues} отсортирован по возрастанию и не содержит дубликатов.
     *
     * @param xValues массив аргументов функции
     * @throws ArrayIsNotSortedException          если массив не отсортирован
     * @throws ArrayHasDuplicateElementsException если массив содержит дубликаты
     */
    static protected void checkSortedAndDuplicate(double[] xValues) {
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] < xValues[i - 1]) {
                throw new ArrayIsNotSortedException("Array is not sorted: xValues[" + (i - 1) + "] = " + xValues[i - 1] + " > xValues[" + i + "] = " + xValues[i]);
            }
            if (xValues[i] == xValues[i - 1]) {
                throw new ArrayHasDuplicateElementsException("Array has duplicate elements: xValues[" + (i - 1) + "] = xValues[" + i + "] = " + xValues[i]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(" size = ").append(getCount()).append("\n");
        for (Point point : this) {
            stringBuilder.append("[").append(point.x).append("; ").append(point.y).append("]\n");
        }
        return stringBuilder.toString();
    }
}
