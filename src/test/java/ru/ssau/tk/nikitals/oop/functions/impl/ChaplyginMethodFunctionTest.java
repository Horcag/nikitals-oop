package ru.ssau.tk.nikitals.oop.functions.impl;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.ssau.tk.nikitals.oop.functions.impl.ChaplyginMethodFunction.derivative;
import static ru.ssau.tk.nikitals.oop.functions.impl.ChaplyginMethodFunction.integrateSimpson;

class ChaplyginMethodFunctionTest {

    @Test
    void testIntegrateSimpson() {
        assertEquals(-0.0526262, integrateSimpson((x, y) -> sin(x * x + 2 * x), -PI, PI, 10000, 2), 1e-6);
    }

    @Test
    void testIntegrateSimpsonAbsoluteFunction() {
        // Функция f(x) = |x|
        assertEquals(PI * PI, integrateSimpson((x, y) -> abs(x), -PI, PI, 10000, 2), 1e-6);
    }
    @Test
    void testDerivative() {
        BiFunction<Double, Double, Double> function = (x, y) -> sin(y * y + 2 * y);

        double expectedDerivativeAt0 = 2 * (0 + 1) * cos(0 * 0 + 2 * 0);
        double actualDerivativeAt0 = derivative(function, 0.0, 0);

        assertEquals(expectedDerivativeAt0, actualDerivativeAt0, 1e-6);
    }

    @Test
    void testDerivativeAtNonZero() {
        double expectedDerivativeAt1 = 2 * (1 + 1) * cos(1 * 1 + 2 * 1);
        double actualDerivativeAt1 = derivative((x, y) -> sin(y * y + 2 * y), 0, 1);

        assertEquals(expectedDerivativeAt1, actualDerivativeAt1, 1e-6);
    }
    @Test
    void testApply() {
        BiFunction<Double, Double, Double> function = (x, y) -> Math.sin(x + y);
        ChaplyginMethodFunction chaplyginMethodFunction = new ChaplyginMethodFunction(function, 0, 1, 1, 100, 10000, 1e-10);

        double result = chaplyginMethodFunction.apply(Math.PI);
        assertEquals(0.0, result, 1e-6);
    }

    @Test
    void testApplyWithDifferentFunction() {
        BiFunction<Double, Double, Double> function = (x, y) -> x * y;
        ChaplyginMethodFunction chaplyginMethodFunction = new ChaplyginMethodFunction(function, 0, 1, 1, 100, 10000, 1e-6);

        double result = chaplyginMethodFunction.apply(2.0);
        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testApplyWithZeroTolerance() {
        BiFunction<Double, Double, Double> function = (x, y) -> x + y;
        ChaplyginMethodFunction chaplyginMethodFunction = new ChaplyginMethodFunction(function, 0, 0.5, 1, 10, 10000, 1e-6);

        double result = chaplyginMethodFunction.apply(1.0);
        assertEquals(5.9157589, result, 1e-6);
    }
}