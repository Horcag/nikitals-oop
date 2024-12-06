package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StrictTabulatedFunctionTest {
    private AutoCloseable closeable;
    @Mock TabulatedFunction mockFunction;
    @Mock private Iterator<Point> mockIterator;
    StrictTabulatedFunction strictFunction;
    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {2.0, 4.0, 6.0};

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        strictFunction = new StrictTabulatedFunction(mockFunction);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


    @Test
    void testStrictArrayTabulatedFunction() {

        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction strictFunction = new StrictTabulatedFunction(arrayFunction);

        assertEquals(2.0, strictFunction.apply(1.0));
        assertEquals(4.0, strictFunction.apply(2.0));
        assertEquals(6.0, strictFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> strictFunction.apply(1.5));
    }

    @Test
    void testStrictLinkedListTabulatedFunction() {
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction strictFunction = new StrictTabulatedFunction(linkedListFunction);

        assertEquals(2.0, strictFunction.apply(1.0));
        assertEquals(4.0, strictFunction.apply(2.0));
        assertEquals(6.0, strictFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> strictFunction.apply(1.5));
    }

    @Test
    void testGetCount() {
        when(mockFunction.getCount()).thenReturn(3);
        assertEquals(3, strictFunction.getCount());
        verify(mockFunction).getCount();
    }

    @Test
    void testGetX() {
        when(mockFunction.getX(0)).thenReturn(1.0);
        assertEquals(1.0, strictFunction.getX(0));
        verify(mockFunction).getX(0);
    }

    @Test
    void testGetY() {
        when(mockFunction.getY(0)).thenReturn(2.0);
        assertEquals(2.0, strictFunction.getY(0));
        verify(mockFunction).getY(0);
    }

    @Test
    void testSetY() {
        strictFunction.setY(0, 3.0);
        verify(mockFunction).setY(0, 3.0);
    }

    @Test
    void testIndexOfX() {
        when(mockFunction.indexOfX(1.0)).thenReturn(0);
        assertEquals(0, strictFunction.indexOfX(1.0));
        verify(mockFunction).indexOfX(1.0);
    }

    @Test
    void testIndexOfY() {
        when(mockFunction.indexOfY(2.0)).thenReturn(0);
        assertEquals(0, strictFunction.indexOfY(2.0));
        verify(mockFunction).indexOfY(2.0);
    }

    @Test
    void testLeftBound() {
        when(mockFunction.leftBound()).thenReturn(1.0);
        assertEquals(1.0, strictFunction.leftBound());
        verify(mockFunction).leftBound();
    }

    @Test
    void testRightBound() {
        when(mockFunction.rightBound()).thenReturn(3.0);
        assertEquals(3.0, strictFunction.rightBound());
        verify(mockFunction).rightBound();
    }

    @Test
    void testIterator()  {
        when(mockFunction.iterator()).thenReturn(mockIterator);
        assertSame(mockIterator, strictFunction.iterator());
        verify(mockFunction).iterator();
    }
}
