package ru.ssau.tk.nikitals.oop.core.domain.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl.SynchronizedTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.Point;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTabulatedFunctionTest {
    private TabulatedFunction originalFunction;
    private SynchronizedTabulatedFunction synchronizedFunction;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};
        originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        synchronizedFunction = new SynchronizedTabulatedFunction(originalFunction);
    }

    @Test
    public void testGetCount() {
        assertEquals(3, synchronizedFunction.getCount());
    }

    @Test
    public void testGetX() {
        assertEquals(1.0, synchronizedFunction.getX(0));
        assertEquals(2.0, synchronizedFunction.getX(1));
    }

    @Test
    public void testGetY() {
        assertEquals(10.0, synchronizedFunction.getY(0));
        assertEquals(20.0, synchronizedFunction.getY(1));
    }

    @Test
    public void testSetY() {
        synchronizedFunction.setY(0, 100.0);
        assertEquals(100.0, synchronizedFunction.getY(0));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(0, synchronizedFunction.indexOfX(1.0));
        assertEquals(2, synchronizedFunction.indexOfX(3.0));
    }

    @Test
    public void testIndexOfY() {
        assertEquals(0, synchronizedFunction.indexOfY(10.0));
        assertEquals(2, synchronizedFunction.indexOfY(30.0));
    }

    @Test
    public void testLeftBound() {
        assertEquals(1.0, synchronizedFunction.leftBound());
    }

    @Test
    public void testRightBound() {
        assertEquals(3.0, synchronizedFunction.rightBound());
    }

    @Test
    public void testIterator() {
        Iterator<Point> iterator = synchronizedFunction.iterator();
        Point firstPoint = iterator.next();
        assertEquals(1.0, firstPoint.x);
        assertEquals(10.0, firstPoint.y);
        Point secondPoint = iterator.next();
        assertEquals(2.0, secondPoint.x);
        assertEquals(20.0, secondPoint.y);
    }

    @Test
    public void testApply() {
        assertEquals(10.0, synchronizedFunction.apply(1.0));
        assertEquals(30.0, synchronizedFunction.apply(3.0));
    }

    @Test
    public void testDoSynchronously() {
        Integer result = synchronizedFunction.doSynchronously(syncFunc -> syncFunc.getCount());
        assertEquals(3, result);
    }

    @Test
    public void testIteratorException() {
        Iterator<Point> iterator = synchronizedFunction.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(IllegalStateException.class, iterator::next);
    }

    @Test
    public void testDoSynchronouslyWithInteger() {
        Integer result = synchronizedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount);
        assertEquals(3, result);
    }

    @Test
    public void testDoSynchronouslyWithDouble() {
        Double result = synchronizedFunction.doSynchronously(syncFunc -> syncFunc.getX(0));
        assertEquals(1.0, result);
    }

    @Test
    public void testDoSynchronouslyWithVoid() {
        synchronizedFunction.doSynchronously(syncFunc -> {
            syncFunc.setY(0, 100.0);
            return null;
        });
        assertEquals(100.0, synchronizedFunction.getY(0));
    }

    @Test
    public void testDoSynchronouslyWithString() {
        String result = synchronizedFunction.doSynchronously(syncFunc -> "Test String");
        assertEquals("Test String", result);
    }
}