package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.ArrayHasDuplicateElementsException;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.nikitals.oop.core.domain.exceptions.InterpolationException;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {
    private static final Logger logger = Logger.getLogger(ArrayTabulatedFunctionTest.class.getName());
    private final double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
    private final double[] yValues = {2.0, 4.0, 6.0, 8.0, 10.0};
    private final double[] singleXValue = {2.0};
    private final double[] singleYValue = {4.0};
    ArrayTabulatedFunction function;
    private final MathFunction source = new SqrFunction();

    @BeforeEach
    void setUp() {
        function = new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    void testConstructorWithArrays() {
        assertEquals(5, function.getCount());
    }

    @Test
    void testConstructorWithFunction() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 1.0, 5.0, 5);
        assertEquals(5, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(5.0, function.getX(4));
        assertEquals(1.0, function.getY(0));
        assertEquals(25.0, function.getY(4));
    }

    @Test
    void testConstructorWithFunction_v1() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 10.0, 1.0, 10);
        assertEquals(10, function.getCount());
        assertEquals(1.0, function.getX(0));
        assertEquals(10.0, function.getX(9));
        assertEquals(1.0, function.getY(0));

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
    void testFloorIndexOfX() {
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
    void apply() {
        assertEquals(2.0, function.apply(1.0));
        assertEquals(3.0, function.apply(1.5));
        assertEquals(4.0, function.apply(2.0));
        assertEquals(6.0, function.apply(3.0));
        assertEquals(7.0, function.apply(3.5));
        assertEquals(8.0, function.apply(4.0));
        assertEquals(10.0, function.apply(5.0));
        assertEquals(12, function.apply(6.0));
    }


    @Test
    void testInsertExistingX() {
        function.insert(3.0, 7.0);
        assertEquals(3.0, function.getX(2));
        assertEquals(7.0, function.getY(2));
        assertEquals(5, function.getCount());
    }

    @Test
    void testInsertAtBeginning() {
        function.insert(0.5, 1.0);
        assertEquals(0.5, function.getX(0));
        assertEquals(1.0, function.getY(0));
        assertEquals(6, function.getCount());
    }

    @Test
    void testInsertInMiddle() {
        function.insert(3.5, 7.0);
        assertEquals(7.0, function.getY(3));
        assertEquals(3.5, function.getX(3));
        assertEquals(6, function.getCount());
    }

    @Test
    void testInsertAtEnd() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.insert(6.0, 12.0);
        int count = function.getCount();
        assertEquals(6.0, function.getX(count - 1));
        assertEquals(12.0, function.getY(count - 1));
        assertEquals(6, count);
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
    void testCreateWithSingleValueException() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(singleXValue, singleYValue));
    }

    @Test
    void testRemoveWithInvalidIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.remove(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.remove(5));
    }

    @Test
    void testArrayTabulatedFunctionConstructorXFromEqualsXTo() {
        MathFunction source = new SqrFunction();
        double xFrom = 3.0;
        double xTo = 3.0;
        int count = 5;

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, xFrom, xTo, count);

        assertEquals(count, function.getCount());
        for (int i = 0; i < count; i++) {
            assertEquals(xFrom, function.getX(i), 0.0);
            assertEquals(source.apply(xFrom), function.getY(i), 0.0);
        }
    }

    @Test
    void testGetXIndexOutOfBounds() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getX(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getX(function.getCount()));
    }

    @Test
    void testConstructorWithEmptyArray() {
        double[] emptyXValues = {};
        double[] yValues = {};
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(emptyXValues, yValues));
    }

    @Test
    void testConstructorWithDifferentLengthArrays() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {1.0};
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }

    @Test
    void testConstructorWithUnorderedXValues() {
        double[] xValues = {1.0, 3.0, 2.0};
        double[] yValues = {1.0, 3.0, 2.0};
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }

    @Test
    void testConstructorWithRepeatingXValues() {
        double[] xValues = {1.0, 2.0, 2.0};
        double[] yValues = {1.0, 2.0, 2.0};
        assertThrows(ArrayHasDuplicateElementsException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }

    @Test
    void testConstructorWithInvalidCount() {
        MathFunction source = x -> x;
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, 0, 1, 0));
    }

    @Test
    void testGetYWithInvalidIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getY(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getY(6));
    }

    @Test
    void testSetYWithInvalidIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.setY(-1, 5.0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.setY(6, 5.0));
    }

    @Test
    void testFloorIndexOfXLessThanFirstElementException() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertThrows(IllegalArgumentException.class, () -> function.floorIndexOfX(0.5));
    }

    @Test
    void testFloorIndexOfXEqualToFirstElement() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(0, function.floorIndexOfX(1.0));
    }

    @Test
    void testFloorIndexOfXBetweenElements() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(1, function.floorIndexOfX(2.5));
    }

    @Test
    void testFloorIndexOfXEqualToLastElement() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(2, function.floorIndexOfX(3.0));
    }

    @Test
    void testFloorIndexOfXGreaterThanLastElement() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(3, function.floorIndexOfX(4.0));
    }

    @Test
    void testRemovePenultimatePoint() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {3.0, 4.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertThrows(IllegalStateException.class, () -> function.remove(0));
    }

    @Test
    void testInterpolationException() {
        int floorIndex = 0;
        double x = 6.0; // Out of bounds for interpolation
        assertThrows(InterpolationException.class, () -> function.interpolate(x, floorIndex));
    }

    @Test
    void testInterpolationWithinBounds() {
        assertDoesNotThrow(() -> function.interpolate(1.5, 0));
        assertDoesNotThrow(() -> function.interpolate(2.5, 1));
    }

    @Test
    void testInterpolationOutOfBoundsLower() {
        assertThrows(InterpolationException.class, () -> function.interpolate(0.5, 0));
        assertThrows(InterpolationException.class, () -> function.interpolate(0.5, -1));
    }

    @Test
    void testInterpolationOutOfBoundsUpper() {
        assertThrows(InterpolationException.class, () -> function.interpolate(5.5, 5));
    }

    @Test
    void testInterpolationAtBounds() {
        assertDoesNotThrow(() -> function.interpolate(1.0, 0));
        assertDoesNotThrow(() -> function.interpolate(1.5, 0));
        assertDoesNotThrow(() -> function.interpolate(2.0, 0));
        assertDoesNotThrow(() -> function.interpolate(2.0, 1));
        assertDoesNotThrow(() -> function.interpolate(2.5, 1));
        assertDoesNotThrow(() -> function.interpolate(3.0, 1));
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

    @Test
    void testEnsureCapacity() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 1.0, 5.0, 5);
        try {
            Method ensureCapacityMethod = ArrayTabulatedFunction.class.getDeclaredMethod("ensureCapacity", int.class);
            ensureCapacityMethod.setAccessible(true);

            Field xValuesField = ArrayTabulatedFunction.class.getDeclaredField("xValues");
            xValuesField.setAccessible(true);

            Field yValuesField = ArrayTabulatedFunction.class.getDeclaredField("yValues");
            yValuesField.setAccessible(true);

            assertEquals(5, function.getCount());
            assertEquals(5, ((double[]) xValuesField.get(function)).length);
            assertEquals(5, ((double[]) yValuesField.get(function)).length);

            ensureCapacityMethod.invoke(function, 5);

            assertEquals(5, ((double[]) xValuesField.get(function)).length);
            assertEquals(5, ((double[]) yValuesField.get(function)).length);
            assertEquals(5, function.getCount());

            ensureCapacityMethod.invoke(function, 6);

            assertEquals(10, ((double[]) xValuesField.get(function)).length);
            assertEquals(10, ((double[]) yValuesField.get(function)).length);
            assertEquals(5, function.getCount());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
        }
    }
}