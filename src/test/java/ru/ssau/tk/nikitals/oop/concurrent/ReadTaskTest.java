package ru.ssau.tk.nikitals.oop.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReadTaskTest {
    private TabulatedFunction function;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {10.0, 20.0, 30.0};
        function = new ArrayTabulatedFunction(xValues, yValues);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testRun() {
        ReadTask readTask = new ReadTask(function);
        readTask.run();

        String expectedOutput = String.format(
                "After read: i = 0, x = 1.000000, y = 10.000000\n" +
                        "After read: i = 1, x = 2.000000, y = 20.000000\n" +
                        "After read: i = 2, x = 3.000000, y = 30.000000\n"
        );

        assertEquals(expectedOutput, outputStream.toString());
    }
}