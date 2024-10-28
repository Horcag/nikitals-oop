package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.exceptions.InterpolationException;
import ru.ssau.tk.nikitals.oop.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.nikitals.oop.exceptions.ArrayHasDuplicateElementsException;
import ru.ssau.tk.nikitals.oop.exceptions.DifferentLengthOfArraysException;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTabulatedFunctionTest {
    private final double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
    private final double[] yValues = {2.0, 4.0, 6.0, 8.0, 10.0};
    private final double[] singleXValue = {2.0};
    private final double[] singleYValue = {4.0};
    private final MathFunction source = new SqrFunction();
    private LinkedListTabulatedFunction function;

    @BeforeEach
    void setUp() {
        function = new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    void testConstructorWithArrays() {
        assertEquals(5, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(5.0, function.getX(4));
    }

    @Test
    void testConstructorWithFunction() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 1.0, 10.0, 5);
        assertEquals(5, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(10, function.getX(4));
        assertEquals(1.0, function.getY(0));
        assertEquals(100, function.getY(4));
    }

    @Test
    void testConstructorWithFunction_v1() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 1.0, 1.0, 2);
        assertEquals(2, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(1.0, function.getY(0));
    }

    @Test
    void testConstructorWithFunction_v2() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 5.0, 1.0, 5);
        assertEquals(5, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(5.0, function.getX(4));
        assertEquals(1.0, function.getY(0));
        assertEquals(25.0, function.getY(4));
    }

    @Test
    void testConstructorWithFunction_v3() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 5.0, 5.0, 5);
        assertEquals(5, function.getCount());
        assertEquals(5.0, function.getX(0));
        assertEquals(5.0, function.getX(4));
        assertEquals(25.0, function.getY(0));
        assertEquals(25.0, function.getY(4));
    }


    @Test
    void getCount() {
        assertEquals(5, function.getCount());
    }

    @Test
    void getX() {
        assertEquals(1.0, function.getX(0));
        assertEquals(5.0, function.getX(4));
    }

    @Test
    void getY() {
        assertEquals(2.0, function.getY(0));
        assertEquals(10.0, function.getY(4));
    }

    @Test
    void setY() {
        function.setY(0, 3.0);
        assertEquals(3.0, function.getY(0));
    }

    @Test
    void indexOfX() {
        assertEquals(0, function.indexOfX(1.0));
        assertEquals(3, function.indexOfX(4.0));
        assertEquals(-1, function.indexOfX(6.0));
    }

    @Test
    void indexOfY() {
        assertEquals(0, function.indexOfY(2.0));
        assertEquals(4, function.indexOfY(10.0));
        assertEquals(-1, function.indexOfY(12.0));
    }

    @Test
    void leftBound() {
        assertEquals(1.0, function.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(5.0, function.rightBound());
    }

    @Test
    void floorIndexOfX() {
        assertThrows(IllegalArgumentException.class, () -> function.floorIndexOfX(0.5));
        assertEquals(0, function.floorIndexOfX(1.0));
        assertEquals(0, function.floorIndexOfX(1.5));
        assertEquals(1, function.floorIndexOfX(2.0));
        assertEquals(1, function.floorIndexOfX(2.5));
        assertEquals(2, function.floorIndexOfX(3.0));
        assertEquals(2, function.floorIndexOfX(3.5));
        assertEquals(3, function.floorIndexOfX(4.0));
        assertEquals(3, function.floorIndexOfX(4.5));
        assertEquals(4, function.floorIndexOfX(5.0));
        assertEquals(5, function.floorIndexOfX(5.5));
        assertEquals(5, function.floorIndexOfX(6.0));
    }

    @Test
    void extrapolateLeft() {
        assertEquals(0.0, function.extrapolateLeft(0.0));
        assertEquals(-2.0, function.extrapolateLeft(-1.0));
        assertEquals(-4.0, function.extrapolateLeft(-2.0));
    }

    @Test
    void extrapolateRight() {
        assertEquals(12.0, function.extrapolateRight(6.0));
        assertEquals(14.0, function.extrapolateRight(7.0));
        assertEquals(16.0, function.extrapolateRight(8.0));
    }

    @Test
    void interpolate() {
        assertEquals(3.0, function.interpolate(1.5, function.floorIndexOfX(1.5)));
        assertEquals(4.0, function.interpolate(2.0, function.floorIndexOfX(2.0)));
        assertEquals(7.0, function.interpolate(3.5, function.floorIndexOfX(3.5)));
        assertEquals(6.0, function.interpolate(3.0, function.floorIndexOfX(3.0)));
        assertEquals(8.0, function.interpolate(4.0, function.floorIndexOfX(4.0)));
    }

    @Test
    void testInterpolateAtLowerBound() {
        assertEquals(2.0, function.interpolate(1.0, function.floorIndexOfX(1.0)));
    }

    @Test
    void testInterpolateAtUpperBound() {
        assertEquals(10.0, function.interpolate(5.0, function.floorIndexOfX(5.0)));
    }

    @Test
    void testInterpolateOutOfBoundsLower() {
        assertThrows(IllegalArgumentException.class, () -> function.interpolate(0.5, function.floorIndexOfX(0.5)));
        assertThrows(InterpolationException.class, () -> function.interpolate(0.5, 0));
    }

    @Test
    void testInterpolateOutOfBoundsUpper() {
        assertThrows(InterpolationException.class, () -> function.interpolate(5.5, function.floorIndexOfX(5.5)));
    }

    @Test
    void testApply() {
        assertEquals(0.0, function.apply(0.0));
        assertEquals(2.0, function.apply(1.0));
        assertEquals(3.0, function.apply(1.5));
        assertEquals(4.0, function.apply(2.0));
        assertEquals(7.0, function.apply(3.5));
        assertEquals(6.0, function.apply(3.0));
        assertEquals(8.0, function.apply(4.0));
        assertEquals(10.0, function.apply(5.0));
        assertEquals(12.0, function.apply(6.0));
    }

    @Test
    void testConstructorWithSingleValueException() {
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(source, 2.0, 2.0, 1));
    }

    @Test
    void testLeftBoundWithSingleValue() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(singleXValue, singleYValue);
        assertEquals(2.0, function.leftBound());
    }

    @Test
    void testRightBoundWithSingleValue() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(singleXValue, singleYValue);
        assertEquals(2.0, function.rightBound());
    }

    @Test
    void testExtrapolateLeftWithSingleValue() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(singleXValue, singleYValue);
        assertEquals(4.0, function.extrapolateLeft(1.0));
    }

    @Test
    void testExtrapolateRightWithSingleValue() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(singleXValue, singleYValue);
        assertEquals(4.0, function.extrapolateRight(3.0));
    }

    @Test
    void testInterpolateWithSingleValue() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(singleXValue, singleYValue);
        assertEquals(4.0, function.interpolate(2.0, 0));
    }

    @Test
    void testInsertIntoEmptyList() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{}, new double[]{});
        function.insert(1.0, 2.0);
        assertEquals(1, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getY(0));
    }

    @Test
    void testInsertAtBeginning() {
        function.insert(0.5, 1.0);
        assertEquals(6, function.getCount());
        assertEquals(0.5, function.getX(0));
        assertEquals(1.0, function.getY(0));
    }

    @Test
    void testInsertAtEnd() {
        function.insert(6.0, 12.0);
        assertEquals(6, function.getCount());
        assertEquals(6.0, function.getX(5));
        assertEquals(12.0, function.getY(5));
    }

    @Test
    void testInsertInMiddle() {
        function.insert(2.5, 5.0);
        assertEquals(6, function.getCount());
        assertEquals(2.5, function.getX(2));
        assertEquals(5.0, function.getY(2));
    }

    @Test
    void testUpdateExistingNode() {
        function.insert(3.0, 7.0);
        assertEquals(5, function.getCount());
        assertEquals(3.0, function.getX(2));
        assertEquals(7.0, function.getY(2));
    }

    @Test
    void testRemoveFromBeginning() {
        function.remove(0);
        assertEquals(4, function.getCount());
        assertEquals(2.0, function.getX(0));
        assertEquals(4.0, function.getY(0));
    }

    @Test
    void testRemoveFromEnd() {
        function.remove(4);
        assertEquals(4, function.getCount());
        assertEquals(4.0, function.getX(3));
        assertEquals(8.0, function.getY(3));
    }

    @Test
    void testRemoveFromMiddle() {
        function.remove(2);
        assertEquals(4, function.getCount());
        assertEquals(4.0, function.getX(2));
        assertEquals(8.0, function.getY(2));
    }

    @Test
    void testRemoveEmptyElement() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{}, new double[]{});
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(0));
    }

    @Test
    void testRemoveWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(5));
    }


    @Test
    void testRemove() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.remove(1);
        assertEquals(2, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(3.0, function.getX(1));

        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(2));
    }

    @Test
    void testGetX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1.0, function.getX(0));
        assertEquals(2.0, function.getX(1));
        assertEquals(3.0, function.getX(2));
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(3));
    }

    @Test
    void testGetY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(2.0, function.getY(0));
        assertEquals(4.0, function.getY(1));
        assertEquals(6.0, function.getY(2));
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(3));
    }

    @Test
    void testSetY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.setY(1, 5.0);
        assertEquals(5.0, function.getY(1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.setY(3, 7.0));
    }

    @Test
    void testIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(0, function.indexOfX(1.0));
        assertEquals(1, function.indexOfX(2.0));
        assertEquals(-1, function.indexOfX(4.0));
    }

    @Test
    void testIndexOfY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(0, function.indexOfY(2.0));
        assertEquals(1, function.indexOfY(4.0));
        assertEquals(-1, function.indexOfY(5.0));
    }

    @Test
    void testBounds() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1.0, function.leftBound());
        assertEquals(3.0, function.rightBound());
    }

    @Test
    void testInterpolation() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(3.0, function.apply(1.5));
        assertEquals(5.0, function.apply(2.5));
    }


    @Test
    void testConstructorWithUnorderedXValues() {
        double[] xValues = {1.0, 3.0, 2.0};
        double[] yValues = {1.0, 3.0, 2.0};
        assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
    }

    @Test
    void testConstructorWithRepeatingXValues() {
        double[] xValues = {1.0, 2.0, 2.0};
        double[] yValues = {1.0, 2.0, 2.0};
        assertThrows(ArrayHasDuplicateElementsException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
    }

    @Test
    void testConstructorWithInvalidCount() {
        MathFunction source = x -> x;
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(source, 0, 1, 0));
    }

    @Test
    void testRemoveFromEmptyList() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{}, new double[]{});
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(0));
    }

    @Test
    void testConstructorWithDifferentLengthArraysLinkedList() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0};
        assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
    }

    @Test
    void testRemoveSingleElement() {
        double[] xValues = {1.0};
        double[] yValues = {2.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.remove(0);
        assertEquals(0, function.getCount());
    }

    @Test
    void testRemoveHeadElement() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.remove(0);
        assertEquals(2, function.getCount());
    }

    @Test
    @SuppressWarnings("WhileLoopReplaceableByForEach")
    void testIteratorWithWhile() {
        int i = 0;
        Iterator<Point> iterator = function.iterator();
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(function.getX(i++), point.x);
        }
    }

    @Test
    void testIteratorWithForEach() {
        int i = 0;
        for (Point point : function) {
            assertEquals(function.getX(i++), point.x);
        }
    }

    @Test
    void testIteratorNoSuchElementException() {
        Iterator<Point> iterator = function.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

}