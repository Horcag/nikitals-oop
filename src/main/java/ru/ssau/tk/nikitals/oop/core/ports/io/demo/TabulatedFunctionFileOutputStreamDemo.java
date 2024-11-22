package ru.ssau.tk.nikitals.oop.core.ports.io.demo;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TabulatedFunctionFileOutputStreamDemo {
    private static final Logger logger = Logger.getLogger(TabulatedFunctionFileOutputStreamDemo.class.getName());
    public static void main(String[] args) {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};

        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        try (BufferedOutputStream arrayOutputStream = new BufferedOutputStream(new FileOutputStream("output/array function.bin"));
             BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"))) {

            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred during writing to file", e);
        }
    }
}
