package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.UnitFunction;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {
    @Test
    void apply() {
        UnitFunction unitFunction = new UnitFunction();
        assertEquals(1, unitFunction.apply(1));
        assertEquals(1, unitFunction.apply(0));
        assertEquals(1, unitFunction.apply(-1));
        assertEquals(1, unitFunction.apply(2));
        assertEquals(1, unitFunction.apply(-2));
    }

}