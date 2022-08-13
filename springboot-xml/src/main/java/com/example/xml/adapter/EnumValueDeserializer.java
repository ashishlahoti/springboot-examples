package com.example.xml.adapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.jvnet.jaxb2_commons.lang.EnumValue;

import java.io.IOException;

public class EnumValueDeserializer extends JsonSerializer<EnumValue> {

    @Override
    public void serialize(EnumValue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.enumValue().toString());
    }
}
