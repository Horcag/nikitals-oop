package ru.ssau.tk.nikitals.oop.core.ports.io.tools.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;

import java.io.IOException;
import java.io.Serial;

public class LinkedListTabulatedFunctionSerializer extends StdSerializer<LinkedListTabulatedFunction> {

    @Serial
    private static final long serialVersionUID = -4249915277792540256L;

    public LinkedListTabulatedFunctionSerializer() {
        this(null);
    }

    public LinkedListTabulatedFunctionSerializer(Class<LinkedListTabulatedFunction> t) {
        super(t);
    }

    @Override
    public void serialize(LinkedListTabulatedFunction value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("xValues");
        for (int i = 0; i < value.getCount(); i++) {
            gen.writeNumber(value.getX(i));
        }
        gen.writeEndArray();
        gen.writeArrayFieldStart("yValues");
        for (int i = 0; i < value.getCount(); i++) {
            gen.writeNumber(value.getY(i));
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}