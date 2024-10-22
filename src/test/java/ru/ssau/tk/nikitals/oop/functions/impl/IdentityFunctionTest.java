package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {

    @Test
    void apply() {
        IdentityFunction identityFunction = new IdentityFunction();
        assertEquals(2, identityFunction.apply(2));
        assertEquals(1, identityFunction.apply(1));
        assertEquals(0, identityFunction.apply(0));
        assertEquals(-1, identityFunction.apply(-1));
        assertEquals(-2, identityFunction.apply(-2));
        assertEquals(1.0, identityFunction.apply(1.0));
        assertEquals(0.0, identityFunction.apply(0.0));
        assertEquals(-1.0, identityFunction.apply(-1.0));
    }
}