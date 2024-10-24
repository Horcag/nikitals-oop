package ru.ssau.tk.nikitals.oop.io;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};

        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);
        try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {

            FunctionsIO.writeTabulatedFunction(arrayWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListWriter, linkedListFunction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
