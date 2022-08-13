package com.example.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class TimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String v) {
        if (Objects.nonNull(v)) {
            try {
                return LocalTime.parse(v);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Failed to parse time: " + v, e);
            }
        }
        return null;
    }

    @Override
    public String marshal(LocalTime v) {
        if (Objects.nonNull(v)) {
            return v.format(DateTimeFormatter.ISO_TIME);
        }
        return null;
    }
}
