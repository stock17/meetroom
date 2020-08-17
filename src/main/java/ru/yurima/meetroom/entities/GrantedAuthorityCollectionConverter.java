package ru.yurima.meetroom.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.*;
import java.util.stream.Collectors;

@Converter
public class GrantedAuthorityCollectionConverter implements AttributeConverter<Collection<GrantedAuthority>, String> {
    private final String DELIMITER = " ";

    @Override
    public String convertToDatabaseColumn(Collection<GrantedAuthority> collection) {
        return collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Collection<GrantedAuthority> convertToEntityAttribute(String joined) {
        return Arrays.stream(joined.split(DELIMITER)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}

