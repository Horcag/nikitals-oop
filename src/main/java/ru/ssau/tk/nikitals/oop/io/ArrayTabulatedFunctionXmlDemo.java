package ru.ssau.tk.nikitals.oop.io;

import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;

import java.io.*;

public class ArrayTabulatedFunctionXmlDemo {
    public static void main(String[] args) {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/arrayFunction.xml"))) {
            FunctionsIO.serializeXml(writer, function);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader reader = new BufferedReader(new FileReader("output/arrayFunction.xml"))) {
            ArrayTabulatedFunction deserializedFunction = FunctionsIO.deserializeXml(reader);
            System.out.println(deserializedFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
