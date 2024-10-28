package ru.ssau.tk.nikitals.oop.io;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.factory.impl.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TabulatedFunctionFileReaderDemo {
    private static final Logger logger = Logger.getLogger(TabulatedFunctionFileReaderDemo.class.getName());
    public static void main(String[] args) {
        try (BufferedReader arrayReader = new BufferedReader(new FileReader("input/function.txt"));
             BufferedReader linkedListReader = new BufferedReader(new FileReader("input/function.txt"))) {

            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayReader, new ArrayTabulatedFunctionFactory());
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(linkedListReader, new LinkedListTabulatedFunctionFactory());

            System.out.println(arrayFunction.toString());
            System.out.println(linkedListFunction.toString());

        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred during reading from file", e);
        }
    }
}
