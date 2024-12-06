package ru.ssau.tk.nikitals.oop.core.domain.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl.MultiplyingTask;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyingTaskTest {
    private TabulatedFunction function;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};
        function = new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testRun() throws InterruptedException {
        MultiplyingTask task = new MultiplyingTask(function);
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        assertEquals(20.0, function.getY(0));
        assertEquals(40.0, function.getY(1));
        assertEquals(60.0, function.getY(2));
    }


}