package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.secondLab.functions.core.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {
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
    void floorIndexOfX() {
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
        assertEquals(4.0, function.apply(2.0));
        assertEquals(6.0, function.apply(3.0));
        assertEquals(8.0, function.apply(4.0));
//        assertEquals(10.0, function.interpolate(5.0, function.floorIndexOfX(5.0)));
        assertEquals(10.0, function.apply(5.0));
        assertEquals(12, function.apply(6.0));
        assertEquals(3.0, function.apply(1.5));
        assertEquals(7.0, function.apply(3.5));
    }

    @Test
    void testConstructorWithSingleValueFunction() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 2.0, 4.0, 1);
        assertEquals(1, function.getCount());
        assertEquals(2.0, function.getX(0));
        assertEquals(4.0, function.getY(0));
    }

    @Test
    void testLeftBoundWithSingleValue() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(singleXValue, singleYValue);
        assertEquals(2.0, function.leftBound());
    }

    @Test
    void testRightBoundWithSingleValue() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(singleXValue, singleYValue);
        assertEquals(2.0, function.rightBound());
    }

    @Test
    void testExtrapolateLeftWithSingleValue() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(singleXValue, singleYValue);
        assertEquals(4.0, function.extrapolateLeft(1.0));
    }

    @Test
    void testExtrapolateRightWithSingleValue() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(singleXValue, singleYValue);
        assertEquals(4.0, function.extrapolateRight(3.0));
    }

    @Test
    void testInterpolateWithSingleValue() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(singleXValue, singleYValue);
        assertEquals(4.0, function.interpolate(2.0, 0));
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
    void testRemoveLastElement() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new double[]{1.0}, new double[]{2.0});
        assertThrows(IllegalStateException.class, () -> function.remove(0));
    }

    @Test
    void testRemoveWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(5));
    }
}