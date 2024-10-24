package ru.ssau.tk.nikitals.oop.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionFactoryTest {
    private final double[] xValues = {1.0, 2.0, 3.0};
    private final double[] yValues = {2.0, 4.0, 6.0};
    private final TabulatedFunctionFactory factory = ArrayTabulatedFunction::new;

    @Test
    void testCreate() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        assertTrue(arrayFactory.create(xValues, yValues) instanceof ArrayTabulatedFunction);
        assertTrue(linkedListFactory.create(xValues, yValues) instanceof LinkedListTabulatedFunction);
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