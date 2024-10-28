package ru.ssau.tk.nikitals.oop.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import ru.ssau.tk.nikitals.oop.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.functions.impl.ArrayTabulatedFunction;
import ru.ssau.tk.nikitals.oop.functions.impl.Point;
import ru.ssau.tk.nikitals.oop.tools.annotations.JaCoCoGenerated;

import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Класс, предоставляющий методы для записи и чтения табулированных функций.
 */
public final class FunctionsIO {
    @JaCoCoGenerated
    private FunctionsIO() {
        throw new UnsupportedOperationException("Instantiation of FunctionsIO is prohibited.");
    }

    /**
     * Метод, записывающий табулированную функцию в буферизованный символьный поток.
     *
     * @param writer   буферизованный символьный поток.
     * @param function табулированная функция.
     */
    static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        printWriter.println(function.getCount());
        for (Point point : function) {
//            printWriter.printf("%f %f\n", point.x, point.y);
            printWriter.printf("%s %s\n", numberFormat.format(point.x), numberFormat.format(point.y));
        }
        printWriter.flush();
    }

    /**
     * Метод, записывающий табулированную функцию в буферизованный байтовый поток.
     *
     * @param outputStream буферизованный байтовый поток.
     * @param function     табулированная функция.
     * @throws IOException если произошла ошибка ввода-вывода.
     */
    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(function.getCount());
        for (Point point : function) {
            dataOutputStream.writeDouble(point.x);
            dataOutputStream.writeDouble(point.y);
        }
        dataOutputStream.flush();
    }

    /**
     * Метод, считывающий табулированную функцию из буферизованного символьного потока.
     *
     * @param reader  буферизованный символьный поток.
     * @param factory фабрика для создания табулированных функций.
     * @return табулированная функция.
     * @throws IOException если произошла ошибка ввода-вывода.
     */
    static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int length = Integer.parseInt(reader.readLine());
        double[] xValues = new double[length];
        double[] yValues = new double[length];
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < length; i++) {
            String line = reader.readLine();
            String[] splitLine = line.split(" ");
            try {
                xValues[i] = numberFormat.parse(splitLine[0]).doubleValue();
                yValues[i] = numberFormat.parse(splitLine[1]).doubleValue();
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
        return factory.create(xValues, yValues);
    }

    static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int length = dataInputStream.readInt();
        double[] xValues = new double[length];
        double[] yValues = new double[length];
        for (int i = 0; i < length; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }
        return factory.create(xValues, yValues);
    }

    static void serialize(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(function);
        out.flush();
    }

    static TabulatedFunction deserialize(BufferedInputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        return (TabulatedFunction) in.readObject();
    }

    static void serializeXml(BufferedWriter writer, ArrayTabulatedFunction function) throws IOException {
        XStream xStream = new XStream();
        writer.write(xStream.toXML(function));
        writer.flush();
    }

    static ArrayTabulatedFunction deserializeXml(BufferedReader reader) {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        return (ArrayTabulatedFunction) xStream.fromXML(reader);
    }

    static void serializeJson(BufferedWriter writer, ArrayTabulatedFunction function) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(function));
        writer.flush();
    }

    static ArrayTabulatedFunction deserializeJson(BufferedReader reader) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readerFor(ArrayTabulatedFunction.class).readValue(reader);
    }
}
