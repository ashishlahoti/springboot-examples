package com.example.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String v) {
        if (Objects.nonNull(v)) {
            try {
                return LocalDateTime.parse(v);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Failed to parse datetime: " + v, e);
            }
        }
        return null;
    }

    @Override
    public String marshal(LocalDateTime v) {
        if (Objects.nonNull(v)) {
            return v.format(DateTimeFormatter.ISO_DATE_TIME);
        }
        return null;
    }
}
