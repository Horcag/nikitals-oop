package ru.ssau.tk.nikitals.oop.secondLab.functions.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.secondLab.functions.impl.*;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {
    private MathFunction identityFunction;
    private MathFunction zeroFunction;
    private MathFunction unitFunction;
    private MathFunction constantFunction;
    private MathFunction constantFunction2;
    private MathFunction sqrFunction;

    @BeforeEach
    void setUp() {
        identityFunction = new IdentityFunction();
        zeroFunction = new ZeroFunction();
        unitFunction = new UnitFunction();
        constantFunction = new ConstantFunction(5);
        constantFunction2 = new ConstantFunction(10);
        sqrFunction = new SqrFunction();
    }

    @Test
    void testIdentityFunctionAndThenItself() {
        // Проверяет, что композиция функции g(x) = x, f(x) = x, f(g(x)) = x.
        assertEquals(2, identityFunction.andThen(identityFunction).apply(2));
    }

    @Test
    void testIdentityFunctionAndThenUnitFunction() {
        // Проверяет, что композиция функции g(x) = x, f(x) = 1, f(g(x)) = 1.
        assertEquals(1, identityFunction.andThen(unitFunction).apply(2));
    }

    @Test
    void testUnitFunctionAndThenIdentityFunction() {
        // Проверяет, что композиция функции g(x) = 1, f(x) = x, f(g(x)) = 1.
        assertEquals(1, unitFunction.andThen(identityFunction).apply(2));
    }

    @Test
    void testZeroFunctionAndThenUnitFunction() {
        // Проверяет, что композиция функции g(x) = 0, f(x) = 1, f(g(x)) = 1.
        assertEquals(1, zeroFunction.andThen(unitFunction).apply(2));
    }

    @Test
    void testUnitFunctionAndThenZeroFunction() {
        // Проверяет, что композиция функции g(x) = 1, f(x) = 0, f(g(x)) = 0.
        assertEquals(0, unitFunction.andThen(zeroFunction).apply(2));
    }

    @Test
    void testConstantFunctionAndThenUnitFunction() {
        // Проверяет, что композиция функции g(x) = 5, f(x) = 1, f(g(x)) = 1.
        assertEquals(1, constantFunction.andThen(unitFunction).apply(2));
    }

    @Test
    void testUnitFunctionAndThenConstantFunction() {
        // Проверяет, что композиция функции g(x) = 1, f(x) = 5, f(g(x)) = 5.
        assertEquals(5, unitFunction.andThen(constantFunction).apply(2));
    }

    @Test
    void testZeroFunctionAndThenConstantFunction() {
        // Проверяет, что композиция функции g(x) = 0, f(x) = 5, f(g(x)) = 5.
        assertEquals(5, zeroFunction.andThen(constantFunction).apply(2));
    }

    @Test
    void testConstantFunctionAndThenConstantFunction() {
        // Проверяет, что композиция функции g(x) = 5, f(x) = 10, f(g(x)) = 5.
        assertEquals(10, constantFunction.andThen(constantFunction2).apply(2));
    }

    @Test
    void testSqrFunctionAndThenSqrFunctionAndThenSqrFunction() {
        // Проверяет, что композиция функции f(x) = x^2, f(f(f(x))) = x^8.
        assertEquals(256, sqrFunction.andThen(sqrFunction).andThen(sqrFunction).apply(2));
    }

    @Test
    void testSqrFunctionAndThenIdentityFunctionAndThenUnitFunction() {
        // Проверяет, что композиция функции h(x) = x^2, g(x) = x, f(x) = 1, f(g(h(x)) = 1.
        assertEquals(1, sqrFunction.andThen(identityFunction).andThen(unitFunction).apply(2));
    }
}