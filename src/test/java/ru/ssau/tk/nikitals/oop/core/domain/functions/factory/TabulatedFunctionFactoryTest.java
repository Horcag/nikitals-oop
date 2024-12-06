package ru.ssau.tk.nikitals.oop.core.domain.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.LinkedListTabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionFactoryTest {
    private final double[] xValues = {1.0, 2.0, 3.0};
    private final double[] yValues = {2.0, 4.0, 6.0};
    private final TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

    @Test
    void testCreate() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        assertInstanceOf(ArrayTabulatedFunction.class, arrayFactory.create(xValues, yValues));
        assertInstanceOf(LinkedListTabulatedFunction.class, linkedListFactory.create(xValues, yValues));
    }


    @Test
    void testCreateStrict() {
        TabulatedFunction function = factory.createStrict(xValues, yValues);

        // Check that the function is strict
        assertThrows(UnsupportedOperationException.class, () -> function.apply(1.5));

        // Check that the function returns correct values for existing x
        assertEquals(2.0, function.apply(1.0));
        assertEquals(4.0, function.apply(2.0));
        assertEquals(6.0, function.apply(3.0));
    }

    @Test
    void testCreateUnmodifiable() {
        TabulatedFunction function = factory.createUnmodifiable(xValues, yValues);

        // Check that the function is unmodifiable
        assertThrows(UnsupportedOperationException.class, () -> function.setY(0, 10.0));

        // Check that the function returns correct values for existing x
        assertEquals(2.0, function.apply(1.0));
        assertEquals(4.0, function.apply(2.0));
        assertEquals(6.0, function.apply(3.0));
    }

    @Test
    void testCreateStrictUnmodifiable() {
        TabulatedFunction function = factory.createStrictUnmodifiable(xValues, yValues);

        // Check that the function is strict
        assertThrows(UnsupportedOperationException.class, () -> function.apply(1.5));

        // Check that the function is unmodifiable
        assertThrows(UnsupportedOperationException.class, () -> function.setY(0, 10.0));

        // Check that the function returns correct values for existing x
        assertEquals(2.0, function.apply(1.0));
        assertEquals(4.0, function.apply(2.0));
        assertEquals(6.0, function.apply(3.0));
    }

}