package ru.ssau.tk.nikitals.oop.operations.api;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.SqrFunction;
import ru.ssau.tk.nikitals.oop.operations.impl.LeftSteppingDifferentialOperator;
import ru.ssau.tk.nikitals.oop.operations.impl.MiddleSteppingDifferentialOperator;
import ru.ssau.tk.nikitals.oop.operations.impl.RightSteppingDifferentialOperator;

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

}