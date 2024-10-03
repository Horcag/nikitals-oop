package ru.ssau.tk.nikitals.oop.secondLab.functions.impl;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.secondLab.functions.api.MathFunction;

import static java.lang.Math.cos;
import static org.junit.jupiter.api.Assertions.*;

class SimpleIterationMethodFunctionTest {
    @Test
    void testSimpleIteration() {
        MathFunction function = x -> (x * x + 4) / 5; // Пример функции f(x) = (x^2 + 4) / 5
        SimpleIterationMethodFunction method = new SimpleIterationMethodFunction(function, 1e-13, 1000, -1e10, 1e10);

        assertTrue(method.simpleIteration(2));
        assertEquals(1, method.getResult(), 1e-12);
        assertTrue(method.getIterations() < 1000);
        assertTrue(method.getEps() < 1e-13);
    }

    @Test
    void testApply() {
        MathFunction function = x -> x * x - 2; // Пример функции f(x) = x^2 - 2
        SimpleIterationMethodFunction method = new SimpleIterationMethodFunction(function, 1e-6, 1000, -1e10, 1e10);

        assertDoesNotThrow(() -> method.apply(-1));
        assertEquals(-1, method.getResult(), 1e-6);
        assertEquals(2, method.apply(-2), 1e-6);
    }
    @Test
    void testApply_v2() {
        MathFunction function = x -> -(0.5 + x * x - cos(x)); // Пример функции f(x) = -(1/2 + x^2 - cos(x))
        SimpleIterationMethodFunction method = new SimpleIterationMethodFunction(function, 1e-6, 3000, -1e10, 1e10);

        assertEquals(0.33359, method.apply(1), 1e-6);
    }


    @Test
    void testApplyThrowsException() {
        MathFunction function = x -> x * x + 2; // Пример функции, не имеющей корней
        SimpleIterationMethodFunction method = new SimpleIterationMethodFunction(function, 1e-6, 1000, -1e10, 1e10);

        assertThrows(IllegalStateException.class, () -> method.apply(0));
    }

    @Test
    void testConstructorWithNegativeMaxIterations() {
        MathFunction function = x -> x;
        assertThrows(IllegalArgumentException.class, () -> new SimpleIterationMethodFunction(function, 1e-6, -1, -1e10, 1e10));
    }

    @Test
    void testSimpleIterationExceedsBounds() {
        MathFunction function = x -> x * 2;
        SimpleIterationMethodFunction method = new SimpleIterationMethodFunction(function, 1e-6, 1000, -1e10, 1e10);

        assertFalse(method.simpleIteration(1e10));
    }

    @Test
    void testSimpleIterationExceedsMaxIterations() {
        MathFunction function = x -> x;
        SimpleIterationMethodFunction method = new SimpleIterationMethodFunction(function, 1e-6, 1, -1e10, 1e10);

        assertFalse(method.simpleIteration(0));
    }

}