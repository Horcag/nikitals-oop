package ru.ssau.tk.nikitals.oop.secondLab.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {
    MathFunction identityFunction = new IdentityFunction();
    MathFunction zeroFunction = new ZeroFunction();
    MathFunction unitFunction = new UnitFunction();
    MathFunction constantFunction = new ConstantFunction(5);
    MathFunction constantFunction2 = new ConstantFunction(10);
    MathFunction sqrFunction = new SqrFunction();

    @Test
    void testIdentityFunctionAndThenUnitFunction() {
        // Проверяет, что композиция тождественной функции и единичной функции возвращает 1.
        assertEquals(1, identityFunction.andThen(unitFunction).apply(2));
    }

    @Test
    void testUnitFunctionAndThenIdentityFunction() {
        // Проверяет, что композиция единичной функции и тождественной функции возвращает 1.
        assertEquals(1, unitFunction.andThen(identityFunction).apply(2));
    }

    @Test
    void testZeroFunctionAndThenUnitFunction() {
        // Проверяет, что композиция нулевой функции и единичной функции возвращает 1.
        assertEquals(1, zeroFunction.andThen(unitFunction).apply(2));
    }

    @Test
    void testUnitFunctionAndThenZeroFunction() {
        // Проверяет, что композиция единичной функции и нулевой функции возвращает 0.
        assertEquals(0, unitFunction.andThen(zeroFunction).apply(2));
    }

    @Test
    void testConstantFunctionAndThenUnitFunction() {
        // Проверяет, что композиция константной функции и единичной функции возвращает 1.
        assertEquals(1, constantFunction.andThen(unitFunction).apply(2));
    }

    @Test
    void testUnitFunctionAndThenConstantFunction() {
        // Проверяет, что композиция единичной функции и константной функции возвращает константу.
        assertEquals(5, unitFunction.andThen(constantFunction).apply(2));
    }

    @Test
    void testZeroFunctionAndThenConstantFunction() {
        // Проверяет, что композиция нулевой функции и константной функции возвращает константу.
        assertEquals(5, zeroFunction.andThen(constantFunction).apply(2));
    }

    @Test
    void testConstantFunctionAndThenConstantFunction() {
        // Проверяет, что композиция двух константных функций возвращает значение первой константы.
        assertEquals(10, constantFunction.andThen(constantFunction2).apply(2));
    }

    @Test
    void testSqrFunctionAndThenSqrFunctionAndThenSquareRootFunction() {
        // Проверяет, что композиция функции f(x) = x^2, f(f(f(x))) = x^8.
        assertEquals(256, sqrFunction.andThen(sqrFunction).andThen(sqrFunction).apply(2));
    }



}