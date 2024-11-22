package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.ArrayHasDuplicateElementsException;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.InterpolationException;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.AbstractTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.Insertable;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.Removable;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.JaCoCoGenerated;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Класс, представляющий табулированную функцию, хранящую значения в связном списке.
 */
public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {
    @Serial
    private static final long serialVersionUID = -2529849605610265778L;

    private static class Node implements Serializable {
        @Serial
        private static final long serialVersionUID = -1761771530979886282L;
        Node next;
        Node prev;
        double x;
        double y;
    }

    private Node head = null;
    private int count = 0;

    /**
     * Добавляет новую точку в связный список.
     *
     * @param x значение аргумента
     * @param y значение функции
     */
    private void addNote(double x, double y) {
        Node node = new Node();
        node.x = x;
        node.y = y;
        if (head == null) {
            head = node;
            head.prev = head;
            head.next = head;
            count++;
            return;
        }
        Node last = head.prev;
        last.next = node;
        node.prev = last;
        node.next = head;
        head.prev = node;
        count++;
    }

    /**
     * Возвращает узел по индексу.
     *
     * @param index индекс узла
     * @return узел по индексу
     * @throws IndexOutOfBoundsException если индекс меньше <b>0</b> или больше количества узлов
     */
    private Node getNode(int index) {
        checkIndex(index);
        Node node = head;
        if (index > count / 2) {
            node = head.prev;
            for (int i = count - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * @param index - индекс
     * @throws IndexOutOfBoundsException если индекс меньше <b>0</b> или больше количества узлов
     */
    private void checkIndex(int index) {
        if (!(0 <= index && index < count)) {
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }


    /**
     * Создает связный список на основе массивов значений {@code xValues} и {@code yValues}.
     *
     * @param xValues массив значений {@code x}
     * @param yValues массив значений {@code y}
     * @throws DifferentLengthOfArraysException   если длины массивов {@code xValues} и {@code yValues} различны
     * @throws ArrayIsNotSortedException          если массив {@code xValues} не отсортирован по возрастанию
     * @throws ArrayHasDuplicateElementsException если массив {@code xValues} содержит повторяющиеся элементы
     */
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSortedAndDuplicate(xValues);
        for (int i = 0; i < xValues.length; i++) {
            addNote(xValues[i], yValues[i]);
        }
    }

    /**
     * Создает связный список на основе функции {@code source}, диапазона значений {@code xFrom} и {@code xTo} и количества точек {@code count}.
     *
     * @param source функция для табулирования
     * @param xFrom  начальное значение диапазона
     * @param xTo    конечное значение диапазона
     * @param count  количество точек табуляции
     * @throws IllegalArgumentException если количество точек меньше <b>2</b>
     */
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Count length less than 2.");
        }
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (xFrom == xTo) {
            double value = source.apply(xFrom);
            for (int i = 0; i < count; i++) {
                addNote(xFrom, value);
            }
            return;
        }
        double step = (xTo - xFrom) / (count - 1);
        double xMoment = xFrom;
        for (int i = 0; i < count; i++) {
            addNote(xMoment, source.apply(xMoment));
            xMoment += step;
        }
    }

    @JaCoCoGenerated
    @Override
    public void insert(double x, double y) {
        if (count == 0) {
            addNote(x, y);
            return;
        }
        if (x < head.x || x > head.prev.x) {
            Node node = new Node();
            node.x = x;
            node.y = y;
            node.next = head;
            node.prev = head.prev;
            head.prev.next = node;
            head.prev = node;
            if (x < head.x) {
                head = node;
            }
            count++;
            return;
        }
        Node node = head;
        for (int i = 0; i < count; i++) {
            if (node.x == x) {
                node.y = y;
                return;
            }
            if (x < node.next.x) {
                Node newNode = new Node();
                newNode.x = x;
                newNode.y = y;
                newNode.next = node.next;
                newNode.prev = node;
                node.next.prev = newNode;
                node.next = newNode;
                count++;
                return;
            }
            node = node.next;
        }
    }

    /**
     * @throws IndexOutOfBoundsException если индекс меньше <b>0</b> или больше количества узлов
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        if (count == 1) {
            head = null;
        } else {
            Node node = getNode(index);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            if (node == head) {
                head = node.next;
            }
        }
        count--;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public double getX(int index) {
        checkIndex(index);
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        checkIndex(index);
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        checkIndex(index);
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node node = head;
        for (int i = 0; i < count; i++) {
            if (node.x == x) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node node = head;
        for (int i = 0; i < count; i++) {
            if (node.y == y) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    /**
     * @throws IllegalArgumentException если значение {@code x} меньше левой границы функции
     */
    @Override
    protected int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X is less than left bound of function.");
        }
        if (x == head.prev.x) {
            return count - 1;
        }
        Node node = head;
        for (int i = 0; i < count - 1; i++) {
            if (x < node.next.x) {
                return i;
            }
            node = node.next;
        }
        return count;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return head.y;
        }
        return interpolate(x, head.x, head.y, head.next.x, head.next.y);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return head.y;
        }
        return interpolate(x, head.prev.prev.x, head.prev.prev.y, head.prev.x, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node node;
        if (0 <= floorIndex && floorIndex < count) {
            node = getNode(floorIndex);
            if (node.x == x) {
                return node.y;
            }
            if (!(node.x <= x && x < node.next.x)) {
                throw new InterpolationException("X is out of bounds of interpolation.");
            }
        } else {
            throw new InterpolationException("X is out of bounds of interpolation.");
        }

        Node rightNode = node.next;
        return interpolate(x, node.x, node.y, rightNode.x, rightNode.y);
    }

    /**
     * Возвращает узел, содержащий ближайшее меньшее или равное значению аргументу {@code x}.
     * <ul>
     * <li>Если все значения {@code x} больше заданного, возвращает начальный узел.
     * <li>Если все значения {@code x} меньше заданного, возвращает последний узел.
     * </ul>
     *
     * @param x значение {@code x} для поиска
     * @return узел, содержащий ближайшее меньшее или равное значение {@code x}
     * @throws IllegalArgumentException если значение {@code x} меньше левой границы функции
     */
    private Node floorNodeOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X is less than left bound of function.");
        }
        if (x == head.prev.x) {
            return head.prev;
        }
        Node node = head;
        for (int i = 0; i < count - 1; i++) {
            if (x < node.next.x) {
                return node;
            }
            node = node.next;
        }
        return head.prev;
    }

    @Override
    public double apply(double x) {
        if (x < head.x) {
            return extrapolateLeft(x);
        }
        if (x > head.prev.x) {
            return extrapolateRight(x);
        }
        Node node = floorNodeOfX(x);
        if (node.x == x) {
            return node.y;
        }
        return interpolate(x, node.x, node.y, node.next.x, node.next.y);
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int index = -1;
            private Node node = head;

            @Override
            public boolean hasNext() {
                return index + 1 < count;
            }

            @Override
            public Point next() {
                index++;
                if (index < count) {
                    Point point = new Point(node.x, node.y);
                    node = node.next;
                    return point;
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }
        };
    }
}
