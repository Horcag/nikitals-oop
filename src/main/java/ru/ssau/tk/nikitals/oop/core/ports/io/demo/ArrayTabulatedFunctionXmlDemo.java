package ru.ssau.tk.nikitals.oop.core.ports.io.demo;

import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.JaCoCoGenerated;
import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;

import java.io.*;
@JaCoCoGenerated
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
