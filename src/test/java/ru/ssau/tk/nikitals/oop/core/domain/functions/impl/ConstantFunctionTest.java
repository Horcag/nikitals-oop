package ru.ssau.tk.nikitals.oop.core.domain.functions.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

    @Test
    void apply() {
        ConstantFunction constantFunction = new ConstantFunction(5);
        assertEquals(5, constantFunction.apply(1));
        assertEquals(5, constantFunction.apply(0));
        assertEquals(5, constantFunction.apply(-1));
        assertEquals(5, constantFunction.apply(2));
        assertEquals(5, constantFunction.apply(-2));
    }

    @Test
    void getConstant() {
        ConstantFunction constantFunction = new ConstantFunction(5);
        assertEquals(5, constantFunction.getConstant());
    }
}