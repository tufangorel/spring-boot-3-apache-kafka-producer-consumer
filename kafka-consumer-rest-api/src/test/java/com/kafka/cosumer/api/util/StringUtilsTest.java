package com.kafka.cosumer.api.util;

import com.kafka.consumer.api.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mockStatic;

public class StringUtilsTest {


    @Test
    void testIsEmpty() {

        // before mock scope, usual behavior.
        assertEquals(false, StringUtils.isEmpty("abc"));

        // Mock scope
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {

            // Mocking
            mocked.when(() -> StringUtils.isEmpty(anyString())).thenReturn(false);

            // Mocked behavior
            assertEquals(false, StringUtils.isEmpty("abc"));
        }

        // After mock scope returns to usual behavior.
        assertEquals(false, StringUtils.isEmpty("abc"));
    }


    @Test
    void testCapitalize() {

        // before mock scope, usual behavior.
        assertEquals("Abc", StringUtils.capitalize("abc"));

        // Mock scope
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {

            // Mocking
            mocked.when(() -> StringUtils.capitalize("abc")).thenReturn("Abc");

            // Mocked behavior
            assertEquals("Abc", StringUtils.capitalize("abc"));
        }

        // After mock scope returns to usual behavior.
        assertNotEquals("ABC", StringUtils.capitalize("abc"));
    }

    @Test
    void testContainsWhitespace() {

        // before mock scope, usual behavior.
        assertEquals(true, StringUtils.containsWhitespace("ab c"));

        // Mock scope
        try (MockedStatic<StringUtils> mocked = mockStatic(StringUtils.class)) {

            // Mocking
            mocked.when(() -> StringUtils.containsWhitespace("ab c")).thenReturn(true);

            // Mocked behavior
            assertEquals(true, StringUtils.containsWhitespace("ab c"));
        }

        // After mock scope returns to usual behavior.
        assertNotEquals(false, StringUtils.containsWhitespace("ab c"));
    }
}
