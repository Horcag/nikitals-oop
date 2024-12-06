package ru.ssau.tk.nikitals.oop.core.domain.operations.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl.SynchronizedTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.Point;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {
    double[] xValues = {1.0, 2.0, 3.0, 4.0};
    double[] yValues = {1.0, 4.0, 9.0, 16.0}; // y = x^2, dy/dx = 2x

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDeriveWithArrayTabulatedFunctionFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction derivedFunction = operator.derive(function);

        Point[] points = TabulatedFunctionOperationService.asPoints(derivedFunction);
        assertEquals(3.0, points[0].y, 1e-5);
        assertEquals(5.0, points[1].y, 1e-5);
        assertEquals(7.0, points[2].y, 1e-5);
        assertEquals(7.0, points[3].y, 1e-5); // Last value should be same as second last
    }

    @Test
    void testDeriveWithLinkedListTabulatedFunctionFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction derivedFunction = operator.derive(function);

        Point[] points = TabulatedFunctionOperationService.asPoints(derivedFunction);
        assertEquals(3.0, points[0].y, 1e-5);
        assertEquals(5.0, points[1].y, 1e-5);
        assertEquals(7.0, points[2].y, 1e-5);
        assertEquals(7.0, points[3].y, 1e-5); // Last value should be same as second last
    }

    @Test
    void testApplyException() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        assertThrows(UnsupportedOperationException.class, () -> operator.apply(1.0));
    }

    @Test
    void testConstructorWithoutFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        assertInstanceOf(ArrayTabulatedFunctionFactory.class, operator.getFactory());
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        operator.setFactory(factory);
        assertSame(factory, operator.getFactory());
    }

    @Test
    public void testDeriveSynchronouslyWithNonSynchronizedFunction() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        TabulatedFunction derivedFunction = operator.deriveSynchronously(function);

        assertEquals(3.0, derivedFunction.getY(0), 1e-6);
        assertEquals(5.0, derivedFunction.getY(1), 1e-6);
        assertEquals(7.0, derivedFunction.getY(2), 1e-6);
        assertEquals(7.0, derivedFunction.getY(3), 1e-6);
    }

    @Test
    public void testDeriveSynchronouslyWithSynchronizedFunction() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction synchronizedFunction = new SynchronizedTabulatedFunction(function);
        TabulatedFunction derivedFunction = operator.deriveSynchronously(synchronizedFunction);

        assertEquals(3.0, derivedFunction.getY(0), 1e-6);
        assertEquals(5.0, derivedFunction.getY(1), 1e-6);
        assertEquals(7.0, derivedFunction.getY(2), 1e-6);
        assertEquals(7.0, derivedFunction.getY(3), 1e-6);
    }
}
