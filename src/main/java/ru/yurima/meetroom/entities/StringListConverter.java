package ru.yurima.meetroom.entities;

import javax.persistence.AttributeConverter;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String> {
    private final String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return String.join(DELIMITER, strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return List.of(s.split(DELIMITER));
    }
}
