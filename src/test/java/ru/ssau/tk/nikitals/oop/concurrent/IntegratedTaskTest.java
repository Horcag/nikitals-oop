package ru.ssau.tk.nikitals.oop.concurrent;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

class IntegratedTaskTest {
    @Test
    public void testIntegratedTask() throws Exception {

        TabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 1000);

        IntegratedTask task = new IntegratedTask(function, 0, 3, 100);
        double result = task.call();
        assertEquals(9.0, result, 0.1);
    }

}