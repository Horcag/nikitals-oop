package ru.ssau.tk.nikitals.oop.functions.api;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.AbstractTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulatedFunctionTest {
    @Test
    void testToString() {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        AbstractTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        String expected = "LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]\n";

        assertEquals(expected, function.toString());
    }

}