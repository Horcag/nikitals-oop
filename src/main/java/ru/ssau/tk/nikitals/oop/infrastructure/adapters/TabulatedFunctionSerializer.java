//package ru.ssau.tk.nikitals.oop.infrastructure.adapters;
//
//import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
//import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;
//
//import java.io.*;
//
//public class TabulatedFunctionSerializer {
//    public static String serialize(TabulatedFunction function) {
//        BufferedOutputStream writer = new BufferedOutputStream(new StringBufferInputStream());
//        FunctionsIO.serialize(writer, function);
//        return writer.toString();
//    }
//
//    public static TabulatedFunction deserialize(String data) {
//        StringReader reader = new StringReader(data);
//        return FunctionsIO.deserialize(reader);
//    }
//}
