package ru.ssau.tk.nikitals.oop.core.ports.io.tools.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.LinkedListTabulatedFunction;

import java.io.IOException;

public class LinkedListTabulatedFunctionDeserializer extends JsonDeserializer<LinkedListTabulatedFunction> {

    @Override
    public LinkedListTabulatedFunction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode xValuesNode = node.get("xValues");
        JsonNode yValuesNode = node.get("yValues");

        double[] xValues = new double[xValuesNode.size()];
        double[] yValues = new double[yValuesNode.size()];

        for (int i = 0; i < xValuesNode.size(); i++) {
            xValues[i] = xValuesNode.get(i).asDouble();
            yValues[i] = yValuesNode.get(i).asDouble();
        }

        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
