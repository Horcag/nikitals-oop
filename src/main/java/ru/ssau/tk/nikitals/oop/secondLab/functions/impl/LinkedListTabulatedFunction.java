package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import ru.ssau.tk.nikitals.oop.secondLab.functions.core.AbstractTabulatedFunction;
import ru.ssau.tk.nikitals.oop.secondLab.functions.core.MathFunction;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private static class Node {
        Node next;
        Node prev;
        double x;
        double y;
    }

    private Node head = null;
    private int count = 0;

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


    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("Lengths of xValues and yValues are different.");
        }
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1] || xValues[i] == xValues[i - 1]) {
                throw new IllegalArgumentException("xValues are not in ascending order or have repeating elements.");
            }
        }
        for (int i = 0; i < xValues.length; i++) {
            addNote(xValues[i], yValues[i]);
        }

    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count length less than 1.");
        }
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (xFrom == xTo) {
            for (int i = 0; i < count; i++) {
                addNote(xFrom, source.apply(xFrom));
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

    Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }
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

    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
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

    @Override
    protected int floorIndexOfX(double x) {
        if (x < head.x) {
            return 0;
        }
        if (x == head.prev.x) {
            return count - 1;
        }
        Node node = head;
        for (int i = 0; i < count-1; i++) {
            if (x < node.next.x) {
                return i;
            }
            node = node.next;
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return head.y;
        }
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return head.y;
        }
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return head.y;
        }
        if (floorIndex == count - 1 || floorIndex == count) {
            return head.prev.y;
        }
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, leftNode.y, rightNode.x, rightNode.y);
    }

    /**
     *
     */
    private Node floorNodeOfX(double x) {
        if (x < head.x) {
            return head;
        }
        if (x == head.prev.x) {
            return head.prev;
        }
        Node node = head;
        for (int i = 0; i < count-1; i++) {
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
}
