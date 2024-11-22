package ru.ssau.tk.nikitals.oop.core.ports.io.demo;

import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;
import ru.ssau.tk.nikitals.oop.core.domain.operations.impl.TabulatedDifferentialOperator;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArrayTabulatedFunctionSerializationDemo {
    private static final Logger logger = Logger.getLogger(ArrayTabulatedFunctionSerializationDemo.class.getName());
    public static void main(String[] args) {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};

        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction firstDerivative = differentialOperator.derive(arrayFunction);
        TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))) {
            FunctionsIO.serialize(outputStream, arrayFunction);
            FunctionsIO.serialize(outputStream, firstDerivative);
            FunctionsIO.serialize(outputStream, secondDerivative);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred during serialization", e);
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))) {
            TabulatedFunction deserializedArrayFunction = FunctionsIO.deserialize(inputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(inputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(inputStream);

            System.out.println(deserializedArrayFunction.toString());
            System.out.println(deserializedFirstDerivative.toString());
            System.out.println(deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "An error occurred during deserialization", e);
        }
    }
}
