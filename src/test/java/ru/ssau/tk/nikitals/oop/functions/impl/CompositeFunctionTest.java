package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {

    @Test
    void apply() {
        CompositeFunction compositeFunction = new CompositeFunction(new SqrFunction(), new IdentityFunction());
        assertEquals(4, compositeFunction.apply(2));
        assertEquals(1, compositeFunction.apply(1));
        assertEquals(0, compositeFunction.apply(0));
        assertEquals(1, compositeFunction.apply(-1));
        assertEquals(4, compositeFunction.apply(-2));

        CompositeFunction compositeFunction2 = new CompositeFunction(new SqrFunction(), new SqrFunction());
        assertEquals(16, compositeFunction2.apply(2));
        assertEquals(1, compositeFunction2.apply(1));
        assertEquals(0, compositeFunction2.apply(0));
        assertEquals(1, compositeFunction2.apply(-1));
        assertEquals(16, compositeFunction2.apply(-2));
        assertEquals(16.0, compositeFunction2.apply(2.0));
        assertEquals(16.0, compositeFunction2.apply(-2.0));

    }
}