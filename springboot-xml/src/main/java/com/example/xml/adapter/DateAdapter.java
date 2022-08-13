package com.example.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) {
        if (Objects.nonNull(v)) {
            try {
                return LocalDate.parse(v);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Failed to parse date: " + v, e);
            }
        }
        return null;
    }

    @Override
    public String marshal(LocalDate v) {
        if (Objects.nonNull(v)) {
            return v.format(DateTimeFormatter.ISO_DATE);
        }
        return null;
    }
}
