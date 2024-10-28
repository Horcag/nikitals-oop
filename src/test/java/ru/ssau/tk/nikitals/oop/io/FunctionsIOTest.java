package ru.ssau.tk.nikitals.oop.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.factory.impl.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.Point;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsIOTest {
    private static final Path TEMP_DIR = Paths.get("temp");
    double[] xValues = {0.0, 0.5, 1.0};
    double[] yValues = {0.0, 0.25, 1.0};

    @BeforeAll
    static void setUp() throws IOException {
        if (!Files.exists(TEMP_DIR)) {
            Files.createDirectory(TEMP_DIR);
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        try (Stream<Path> paths = Files.walk(TEMP_DIR)) {
            paths.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(file -> {
                        if (!file.delete()) {
                            System.err.println("Can't delete file: " + file);
                        }
                    });
        }
    }

    @Test
    void testWriteAndReadTabulatedFunctionBufferedWriter() throws IOException {
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        Path filePath = TEMP_DIR.resolve("function.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            FunctionsIO.writeTabulatedFunction(writer, function);
        }

        TabulatedFunction readFunction;
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            readFunction = FunctionsIO.readTabulatedFunction(reader, new ArrayTabulatedFunctionFactory());
        }

        assertEquals(function.getCount(), readFunction.getCount());
        Iterator<Point> expectedPoints = function.iterator();
        Iterator<Point> actualPoints = readFunction.iterator();
        for (int i = 0; i < function.getCount(); i++) {
            Point expected = expectedPoints.next();
            Point actual = actualPoints.next();
            assertEquals(expected.x, actual.x);
            assertEquals(expected.y, actual.y);
        }
    }

    @Test
    void testWriteAndReadTabulatedFunctionBufferedOutputStream() throws IOException {
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        Path filePath = TEMP_DIR.resolve("function.bin");

        try (BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(filePath))) {
            FunctionsIO.writeTabulatedFunction(outputStream, function);
        }

        TabulatedFunction readFunction;
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(filePath))) {
            readFunction = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
        }

        assertEquals(function.getCount(), readFunction.getCount());
        Iterator<Point> expectedPoints = function.iterator();
        Iterator<Point> actualPoints = readFunction.iterator();
        for (int i = 0; i < function.getCount(); i++) {
            Point expected = expectedPoints.next();
            Point actual = actualPoints.next();
            assertEquals(expected.x, actual.x);
            assertEquals(expected.y, actual.y);
        }
    }

    @Test
    void testSerializeAndDeserializeArrayTabulatedFunction() throws IOException, ClassNotFoundException {
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Path filePath = TEMP_DIR.resolve("arrayFunction.bin");
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath.toFile()))) {
            FunctionsIO.serialize(outputStream, function);
        }

        TabulatedFunction deserializedFunction;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath.toFile()))) {
            deserializedFunction = FunctionsIO.deserialize(inputStream);
        }

        assertEquals(function.toString(), deserializedFunction.toString());
    }

    @Test
    void testSerializeAndDeserializeLinkedListTabulatedFunction() throws IOException, ClassNotFoundException {
        TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        Path filePath = TEMP_DIR.resolve("linkedListFunction.bin");
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath.toFile()))) {
            FunctionsIO.serialize(outputStream, function);
        }

        TabulatedFunction deserializedFunction;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath.toFile()))) {
            deserializedFunction = FunctionsIO.deserialize(inputStream);
        }

        assertEquals(function.toString(), deserializedFunction.toString());
    }

    @Test
    void testSerializeAndDeserializeXml() throws IOException {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Path filePath = TEMP_DIR.resolve("arrayFunction.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            FunctionsIO.serializeXml(writer, function);
        }

        ArrayTabulatedFunction deserializedFunction;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            deserializedFunction = FunctionsIO.deserializeXml(reader);
        }

        assertEquals(function.toString(), deserializedFunction.toString());
    }

    @Test
    void testSerializeAndDeserializeJson() throws IOException {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Path filePath = TEMP_DIR.resolve("arrayFunction.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            FunctionsIO.serializeJson(writer, function);
        }

        ArrayTabulatedFunction deserializedFunction;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            deserializedFunction = FunctionsIO.deserializeJson(reader);
        }

        assertEquals(function.toString(), deserializedFunction.toString());
    }

}