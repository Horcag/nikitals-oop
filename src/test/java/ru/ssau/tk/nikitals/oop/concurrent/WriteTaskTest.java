package ru.ssau.tk.nikitals.oop.concurrent;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class WriteTaskTest {
    @Test
    public void testWriteTask() throws InterruptedException {
        double[] xValues = {1, 2, 3};
        double[] yValues = {10, 20, 30};
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        WriteTask task = new WriteTask(function, 100);

        Thread thread = new Thread(task);
        thread.start();
        thread.join();

        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(100, function.getY(i));
        }
    }
}