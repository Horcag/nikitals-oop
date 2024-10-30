package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UnmodifiableTabulatedFunctionTest {
    private AutoCloseable closeable;
    @Mock
    TabulatedFunction mockFunction;
    @Mock private Iterator<Point> mockIterator;
    UnmodifiableTabulatedFunction unmodifiableFunction;
    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {2.0, 4.0, 6.0};

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        unmodifiableFunction = new UnmodifiableTabulatedFunction(mockFunction);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


    @Test
    void testUnmodifiableArrayTabulatedFunction() {
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(arrayFunction);

        assertEquals(2.0, unmodifiableFunction.apply(1.0));
        assertEquals(4.0, unmodifiableFunction.apply(2.0));
        assertEquals(6.0, unmodifiableFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableFunction.setY(1, 5.0));
    }

    @Test
    void testUnmodifiableLinkedListTabulatedFunction() {
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(linkedListFunction);

        assertEquals(2.0, unmodifiableFunction.apply(1.0));
        assertEquals(4.0, unmodifiableFunction.apply(2.0));
        assertEquals(6.0, unmodifiableFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableFunction.setY(1, 5.0));
    }

    @Test
    void testGetCount() {
        when(mockFunction.getCount()).thenReturn(3);
        assertEquals(3, unmodifiableFunction.getCount());
        verify(mockFunction).getCount();
    }

    @Test
    void testGetX() {
        when(mockFunction.getX(0)).thenReturn(1.0);
        assertEquals(1.0, unmodifiableFunction.getX(0));
        verify(mockFunction).getX(0);
    }

    @Test
    void testGetY() {
        when(mockFunction.getY(0)).thenReturn(2.0);
        assertEquals(2.0, unmodifiableFunction.getY(0));
        verify(mockFunction).getY(0);
    }

    @Test
    void testApply() {
        when(mockFunction.apply(1.0)).thenReturn(2.0);
        assertEquals(2.0, unmodifiableFunction.apply(1.0));
        verify(mockFunction).apply(1.0);
    }

    @Test
    void testIndexOfX() {
        when(mockFunction.indexOfX(1.0)).thenReturn(0);
        assertEquals(0, unmodifiableFunction.indexOfX(1.0));
        verify(mockFunction).indexOfX(1.0);
    }

    @Test
    void testIndexOfY() {
        when(mockFunction.indexOfY(2.0)).thenReturn(0);
        assertEquals(0, unmodifiableFunction.indexOfY(2.0));
        verify(mockFunction).indexOfY(2.0);
    }

    @Test
    void testLeftBound() {
        when(mockFunction.leftBound()).thenReturn(1.0);
        assertEquals(1.0, unmodifiableFunction.leftBound());
        verify(mockFunction).leftBound();
    }

    @Test
    void testRightBound() {
        when(mockFunction.rightBound()).thenReturn(3.0);
        assertEquals(3.0, unmodifiableFunction.rightBound());
        verify(mockFunction).rightBound();
    }

    @Test
    void testIterator()  {
        when(mockFunction.iterator()).thenReturn(mockIterator);
        assertSame(mockIterator, unmodifiableFunction.iterator());
        verify(mockFunction).iterator();
    }
}