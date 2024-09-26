package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    @Test
    void applyReturnsSquareOfPositiveNumber() {
        SqrFunction sqrFunction = new SqrFunction();
        assertEquals(1, sqrFunction.apply(1));
        assertEquals(4, sqrFunction.apply(2));
        assertEquals(9, sqrFunction.apply(3));
    }

    @Test
    void applyReturnsSquareOfNegativeNumber() {
        SqrFunction sqrFunction = new SqrFunction();
        assertEquals(1, sqrFunction.apply(-1));
        assertEquals(4, sqrFunction.apply(-2));
        assertEquals(9, sqrFunction.apply(-3));
    }
    @Test
    void applyReturnsZeroForZero() {
        SqrFunction sqrFunction = new SqrFunction();
        assertEquals(0, sqrFunction.apply(0));
    }

    @Test
    void applyReturnsSquareOfFraction() {
        SqrFunction sqrFunction = new SqrFunction();
        assertEquals(0.25, sqrFunction.apply(0.5));
    }

    @Test
    void applyReturnsSquareOfLargeNumber() {
        SqrFunction sqrFunction = new SqrFunction();
        assertEquals(1_000_000_000_000.0, sqrFunction.apply(1_000_000));
    }
}