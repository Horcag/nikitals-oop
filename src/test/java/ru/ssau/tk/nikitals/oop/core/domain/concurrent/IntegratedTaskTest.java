package ru.ssau.tk.nikitals.oop.core.domain.concurrent;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.core.domain.concurrent.impl.IntegratedTask;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

class IntegratedTaskTest {
    @Test
    public void testIntegratedTask() {

        TabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 1000);

        IntegratedTask task = new IntegratedTask(function, 0, 3, 100);
        double result = task.call();
        assertEquals(9.0, result, 0.1);
    }

}