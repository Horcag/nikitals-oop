package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableTabulatedFunctionTest {
    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {2.0, 4.0, 6.0};
    TabulatedFunction function;

    @BeforeEach
    void setUp() {
        function = new ArrayTabulatedFunction(xValues, yValues);
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

}