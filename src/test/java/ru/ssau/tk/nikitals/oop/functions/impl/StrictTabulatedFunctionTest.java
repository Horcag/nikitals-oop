package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class StrictTabulatedFunctionTest {
    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {2.0, 4.0, 6.0};
    TabulatedFunction function;

    @BeforeEach
    void setUp() {
        function = new ArrayTabulatedFunction(xValues, yValues);
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
}
