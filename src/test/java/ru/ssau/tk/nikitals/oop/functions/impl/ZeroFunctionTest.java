package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {

    @Test
    void apply() {
        ZeroFunction zeroFunction = new ZeroFunction();
        assertEquals(0, zeroFunction.apply(1));
        assertEquals(0, zeroFunction.apply(0));
        assertEquals(0, zeroFunction.apply(-1));
        assertEquals(0, zeroFunction.apply(2));
        assertEquals(0, zeroFunction.apply(-2));
    }

}