package ru.ssau.tk.nikitals.oop.io;

import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.operations.impl.TabulatedDifferentialOperator;

import java.io.*;

import static ru.ssau.tk.nikitals.oop.io.FunctionsIO.readTabulatedFunction;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            System.out.println(readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Введите размер и значения функции:");
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            TabulatedFunction linkedListFunction = readTabulatedFunction(consoleReader, new LinkedListTabulatedFunctionFactory());
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction derivativeFunction = differentialOperator.derive(linkedListFunction);
            System.out.println(derivativeFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
