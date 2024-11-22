package ru.ssau.tk.nikitals.oop.core.ports.io.demo;

import org.springframework.aot.generate.Generated;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;
import ru.ssau.tk.nikitals.oop.core.domain.operations.impl.TabulatedDifferentialOperator;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO.readTabulatedFunction;

@Generated
public class TabulatedFunctionFileInputStreamDemo {
    private static final Logger logger = Logger.getLogger(TabulatedFunctionFileInputStreamDemo.class.getName());
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            System.out.println(FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory()).toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred during reading from file", e);
        }

        System.out.println("Введите размер и значения функции:");
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(consoleReader, new LinkedListTabulatedFunctionFactory());
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction derivativeFunction = differentialOperator.derive(linkedListFunction);
            System.out.println(derivativeFunction.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred during reading from console", e);
        }
    }
}
