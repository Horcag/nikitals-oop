package ru.ssau.tk.nikitals.oop.operations.api;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.SqrFunction;
import ru.ssau.tk.nikitals.oop.core.domain.operations.api.SteppingDifferentialOperator;
import ru.ssau.tk.nikitals.oop.core.domain.operations.impl.LeftSteppingDifferentialOperator;
import ru.ssau.tk.nikitals.oop.core.domain.operations.impl.MiddleSteppingDifferentialOperator;
import ru.ssau.tk.nikitals.oop.core.domain.operations.impl.RightSteppingDifferentialOperator;

import static org.junit.jupiter.api.Assertions.*;

class SteppingDifferentialOperatorTest {
    private static final double STEP = 1e-6;
    private static final double DELTA = 1e-5;

    @Test
    void testLeftSteppingDifferentialOperator() {
        MathFunction sqrFunction = new SqrFunction();
        LeftSteppingDifferentialOperator leftOperator = new LeftSteppingDifferentialOperator(STEP);
        MathFunction derivative = leftOperator.derive(sqrFunction);

        assertEquals(2.0, derivative.apply(1.0), DELTA);
        assertEquals(4.0, derivative.apply(2.0), DELTA);
        assertEquals(6.0, derivative.apply(3.0), DELTA);
    }

    @Test
    void testRightSteppingDifferentialOperator() {
        MathFunction sqrFunction = new SqrFunction();
        RightSteppingDifferentialOperator rightOperator = new RightSteppingDifferentialOperator(STEP);
        MathFunction derivative = rightOperator.derive(sqrFunction);

        assertEquals(2.0, derivative.apply(1.0), DELTA);
        assertEquals(4.0, derivative.apply(2.0), DELTA);
        assertEquals(6.0, derivative.apply(3.0), DELTA);
    }

    @Test
    void testMiddleSteppingDifferentialOperator() {
        MathFunction sqrFunction = new SqrFunction();
        MiddleSteppingDifferentialOperator middleOperator = new MiddleSteppingDifferentialOperator(STEP);
        MathFunction derivative = middleOperator.derive(sqrFunction);

        assertEquals(2.0, derivative.apply(1.0), DELTA);
        assertEquals(4.0, derivative.apply(2.0), DELTA);
        assertEquals(6.0, derivative.apply(3.0), DELTA);
    }

    @Test
    void testApplyException() {
        LeftSteppingDifferentialOperator leftOperator = new LeftSteppingDifferentialOperator(STEP);
        RightSteppingDifferentialOperator rightOperator = new RightSteppingDifferentialOperator(STEP);
        MiddleSteppingDifferentialOperator middleOperator = new MiddleSteppingDifferentialOperator(STEP);

        assertThrows(UnsupportedOperationException.class, () -> leftOperator.apply(1.0));
        assertThrows(UnsupportedOperationException.class, () -> rightOperator.apply(1.0));
        assertThrows(UnsupportedOperationException.class, () -> middleOperator.apply(1.0));
    }


    @Test
    void testValidStep() {
        SteppingDifferentialOperator operator = new LeftSteppingDifferentialOperator(1.0);
        assertEquals(1.0, operator.getStep());
        operator.setStep(2.0);
        assertEquals(2.0, operator.getStep());
    }

    @Test
    void testZeroStep() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new LeftSteppingDifferentialOperator(0.0)
        );
        assertEquals("Step is incorrect.", exception.getMessage());
    }

    @Test
    void testNegativeStep() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new LeftSteppingDifferentialOperator(-1.0));
        assertEquals("Step is incorrect.", exception.getMessage());
    }

    @Test
    void testInfiniteStep() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new LeftSteppingDifferentialOperator(Double.POSITIVE_INFINITY)
        );
        assertEquals("Step is incorrect.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
            new LeftSteppingDifferentialOperator(Double.NEGATIVE_INFINITY)
        );
        assertEquals("Step is incorrect.", exception.getMessage());
    }

    @Test
    void testNaNStep() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new LeftSteppingDifferentialOperator(Double.NaN)
        );
        assertEquals("Step is incorrect.", exception.getMessage());
    }
}