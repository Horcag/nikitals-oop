package ru.ssau.tk.nikitals.oop.operations.impl;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.factory.impl.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.*;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {
    double[] xValues = {1.0, 2.0, 3.0};
    double[] yValues = {2.0, 4.0, 6.0};
    @Test
    void testAsPointsWithArrayTabulatedFunction() {

        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        Point[] points = TabulatedFunctionOperationService.asPoints(arrayFunction);

        assertEquals(3, points.length);
        int i = 0;
        for (Point point : points) {
            assertEquals(point, points[i++]);
        }
    }

    @Test
    void testAsPointsWithLinkedListTabulatedFunction() {
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);
        Point[] points = TabulatedFunctionOperationService.asPoints(linkedListFunction);

        assertEquals(3, points.length);
        int i = 0;
        for (Point point : points) {
            assertEquals(point, points[i++]);
        }
    }

    @Test
    void testSumWithDifferentFactories() {
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        TabulatedFunction result = service.sum(arrayFunction, linkedListFunction);

        assertEquals(3, result.getCount());
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(xValues[i], result.getX(i));
            assertEquals(yValues[i] + yValues[i], result.getY(i));
        }
    }

    @Test
    void testSubtractWithDifferentFactories() {
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction result = service.subtract(arrayFunction, linkedListFunction);

        assertEquals(3, result.getCount());
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(xValues[i], result.getX(i));
            assertEquals(0.0, result.getY(i));
        }
    }

    @Test
    void testMultiplyWithDifferentFactories() {
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        TabulatedFunction result = service.multiply(arrayFunction, linkedListFunction);

        assertEquals(3, result.getCount());
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(xValues[i], result.getX(i));
            assertEquals(yValues[i] * yValues[i], result.getY(i));
        }
    }

    @Test
    void testDivideWithDifferentFactories() {
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction result = service.divide(arrayFunction, linkedListFunction);

        assertEquals(3, result.getCount());
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(xValues[i], result.getX(i));
            assertEquals(1.0, result.getY(i));
        }
    }

    @Test
    void testDifferentLengthException() {
        double[] differentXValues = {1.0, 2.0};
        double[] differentYValues = {2.0, 4.0};
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(differentXValues, differentYValues);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());

        assertThrows(InconsistentFunctionsException.class, () -> service.sum(arrayFunction, linkedListFunction));
    }

    @Test
    void testDifferentXValuesException() {
        double[] differentXValues = {1.0, 2.5, 3.0};
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(differentXValues, yValues);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());

        assertThrows(InconsistentFunctionsException.class, () -> service.sum(arrayFunction, linkedListFunction));
    }

    @Test
    void testConstructorWithoutFactory() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        assertEquals(ArrayTabulatedFunctionFactory.class, service.getFactory().getClass());
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        service.setFactory(factory);
        assertSame(factory, service.getFactory());
    }

}