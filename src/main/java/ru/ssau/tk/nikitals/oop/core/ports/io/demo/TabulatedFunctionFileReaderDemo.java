package ru.ssau.tk.nikitals.oop.core.ports.io.demo;

import lombok.extern.slf4j.Slf4j;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.JaCoCoGenerated;
import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@JaCoCoGenerated
public class TabulatedFunctionFileReaderDemo {

    public static void main(String[] args) {
        try (BufferedReader arrayReader = new BufferedReader(new FileReader("input/function.txt"));
             BufferedReader linkedListReader = new BufferedReader(new FileReader("input/function.txt"))) {

            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayReader, new ArrayTabulatedFunctionFactory());
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(linkedListReader, new LinkedListTabulatedFunctionFactory());

            System.out.println(arrayFunction.toString());
            System.out.println(linkedListFunction.toString());

        } catch (IOException e) {
            log.error("An error occurred during reading from file", e);
        }
    }
}
