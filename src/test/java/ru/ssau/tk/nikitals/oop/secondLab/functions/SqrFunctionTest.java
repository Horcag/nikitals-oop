package ru.ssau.tk.nikitals.oop.secondLab.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    @Test
    void apply() {
        SqrFunction sqrFunction = new SqrFunction();
        assertEquals(4, sqrFunction.apply(2));
        assertEquals(1, sqrFunction.apply(1));
        assertEquals(0, sqrFunction.apply(0));
        assertEquals(1, sqrFunction.apply(-1));
        assertEquals(4, sqrFunction.apply(-2));
    }
}