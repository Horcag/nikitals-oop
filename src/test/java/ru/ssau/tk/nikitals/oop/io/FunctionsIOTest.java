package ru.ssau.tk.nikitals.oop.io;

import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.LinkedListTabulatedFunction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsIOTest {
    private static final Path TEMP_DIR = Paths.get("temp");

    @BeforeClass
    public void setUp() throws IOException {
        if (!Files.exists(TEMP_DIR)) {
            Files.createDirectory(TEMP_DIR);
        }
    }

    @AfterClass
    public void tearDown() throws IOException {
        Files.walk(TEMP_DIR)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void testSerializeAndDeserializeArrayTabulatedFunction() throws IOException, ClassNotFoundException {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
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
    public void testSerializeAndDeserializeLinkedListTabulatedFunction() throws IOException, ClassNotFoundException {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
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
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Path filePath = Paths.get("temp/arrayFunction.xml");
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
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Path filePath = Paths.get("temp/arrayFunction.json");
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