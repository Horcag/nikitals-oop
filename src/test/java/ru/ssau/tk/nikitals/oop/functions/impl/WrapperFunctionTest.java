package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class WrapperFunctionTest {

    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {2.0, 4.0, 6.0};
    TabulatedFunction function;

    @BeforeEach
    void setUp() {
        function = new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    void testStrictUnmodifiableArrayTabulatedFunction() {
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction strictFunction = new StrictTabulatedFunction(arrayFunction);
        TabulatedFunction unmodifiableStrictFunction = new UnmodifiableTabulatedFunction(strictFunction);

        assertEquals(2.0, unmodifiableStrictFunction.apply(1.0));
        assertEquals(4.0, unmodifiableStrictFunction.apply(2.0));
        assertEquals(6.0, unmodifiableStrictFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableStrictFunction.apply(1.5));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableStrictFunction.setY(1, 5.0));
    }

    @Test
    void testUnmodifiableStrictArrayTabulatedFunction() {
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(arrayFunction);
        TabulatedFunction strictUnmodifiableFunction = new StrictTabulatedFunction(unmodifiableFunction);

        assertEquals(2.0, strictUnmodifiableFunction.apply(1.0));
        assertEquals(4.0, strictUnmodifiableFunction.apply(2.0));
        assertEquals(6.0, strictUnmodifiableFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableFunction.apply(1.5));
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableFunction.setY(1, 5.0));
    }

    @Test
    void testStrictUnmodifiableLinkedListTabulatedFunction() {
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction strictFunction = new StrictTabulatedFunction(linkedListFunction);
        TabulatedFunction unmodifiableStrictFunction = new UnmodifiableTabulatedFunction(strictFunction);

        assertEquals(2.0, unmodifiableStrictFunction.apply(1.0));
        assertEquals(4.0, unmodifiableStrictFunction.apply(2.0));
        assertEquals(6.0, unmodifiableStrictFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableStrictFunction.apply(1.5));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableStrictFunction.setY(1, 5.0));
    }

    @Test
    void testUnmodifiableStrictLinkedListTabulatedFunction() {
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(linkedListFunction);
        TabulatedFunction strictUnmodifiableFunction = new StrictTabulatedFunction(unmodifiableFunction);

        assertEquals(2.0, strictUnmodifiableFunction.apply(1.0));
        assertEquals(4.0, strictUnmodifiableFunction.apply(2.0));
        assertEquals(6.0, strictUnmodifiableFunction.apply(3.0));
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableFunction.apply(1.5));
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableFunction.setY(1, 5.0));
    }
}
