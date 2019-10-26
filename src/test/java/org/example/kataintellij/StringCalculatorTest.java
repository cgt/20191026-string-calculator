package org.example.kataintellij;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    @Test
    public void given_empty_string_returns_zero() {
        assertEquals(0, add(""));
    }

    @Test
    public void given_any_one_single_digit_returns_same_digit() {
        assertEquals(0, add("0"));
        assertEquals(1, add("1"));
        assertEquals(2, add("2"));
        assertEquals(3, add("3"));
    }

    @Test
    public void given_two_numbers_separated_by_comma_return_their_sum() {
        assertEquals(2, add("1,1"));
        assertEquals(1262, add("0,1262"));
    }

    @Test
    public void three_numbers() {
        assertEquals(6, add("1,2,3"));
    }

    @Test
    public void use_newline_as_separator() {
        assertEquals(2, add("1\n1"));
    }

    @Test
    public void allow_mixing_separators() {
        assertEquals(6, add("1\n2,3"));
    }

    @Test
    public void support_custom_delimiters() {
        assertEquals(3, add("//\n\n1\n2"));
        assertEquals(3, add("//;\n1;2"));
    }

    @Test
    public void does_not_support_negatives() {
        try {
            add("-1");
        } catch (Exception e) {
            assertTrue(e.getMessage().startsWith("negatives not allowed"));
        }

        assertThrows(Exception.class, () -> {
            add("-1,-1");
        });
    }

    @Test
    public void includes_negative_numbers_in_exception() {
        try {
            add("-1");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("-1"));
        }
    }

    private int add(String s) {
        if (s.startsWith("//")) {
            final var delimiter = String.valueOf(s.charAt(2));
            return add(trimCustomDelimiter(s).replaceAll(delimiter, ","));
        }
        if (s.equals("")) {
            return 0;
        }
        if (s.contains(",") || s.contains("\n")) {
            final var split = s.split("[,\n]");

            return Arrays
                .stream(split)
                .map(this::add)
                .collect(Collectors.toList()).stream()
                .reduce(Integer::sum)
                .orElse(0);
        }
        final var i = parseInt(s);
        if (i < 0) {
            throw new RuntimeException("negatives not allowed: " + i);
        }
        return i;
    }

    private String trimCustomDelimiter(String s) {
        return s.substring(4);
    }

}
