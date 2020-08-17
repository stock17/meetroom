package ru.yurima.meetroom.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GrantedAuthorityCollectionConverterTest {

    GrantedAuthorityCollectionConverter converter;

    @BeforeEach
    void setUp() {
        converter = new GrantedAuthorityCollectionConverter();
    }

    @Test
    void convertToDatabaseColumn() {
        Collection<GrantedAuthority> collection = Arrays.asList(
                new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority("ADMIN"));
        String expected = "USER ADMIN";
        String actual = converter.convertToDatabaseColumn(collection);
        assertEquals(expected, actual);
    }

    @Test
    void convertToEntityAttribute() {
        String roles = "USER ADMIN";
        Collection<GrantedAuthority> collection = converter.convertToEntityAttribute(roles);
        assertEquals(2, collection.size());
        assertTrue(collection.contains(new SimpleGrantedAuthority("USER")));
        assertTrue(collection.contains(new SimpleGrantedAuthority("ADMIN")));
    }
}