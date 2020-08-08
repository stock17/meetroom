package ru.yurima.meetingroom.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.*;
import java.util.ArrayList;

@Converter
public class CollectionGrantedAuthorityConverter implements AttributeConverter<Collection<GrantedAuthority>, String> {
    String delimiter = " ";

    @Override
    public String convertToDatabaseColumn(Collection<GrantedAuthority> collection) {
        StringBuilder builder = new StringBuilder();
        for (GrantedAuthority ga : collection) {
            builder.append(ga.getAuthority()).append(delimiter);
        }
        return builder.toString();
    }

    @Override
    public Collection<GrantedAuthority> convertToEntityAttribute(String joined) {
        String[] roles = joined.split(delimiter);
        Collection<GrantedAuthority> collection = new ArrayList<>();
        for (String role : roles) {
            collection.add(new SimpleGrantedAuthority(role));
        }
        return collection;
    }

}

