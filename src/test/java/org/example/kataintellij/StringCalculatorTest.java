package org.example.kataintellij;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        try {
            add("-1,-2");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("-1") && e.getMessage().contains("-2"));
        }
    }

    @Test
    public void numbers_above_1000_are_not_included_in_sum() {
        assertEquals(2, add("2,1001"));
    }

    @Test
    public void supports_multicharacter_delimiters() {
        assertEquals(6, add("//[***]\n1***2***3"));
    }

    private int add(String s) {
        if (s.startsWith("//[")) {
            final var startOfDelimiter = 3;
            final var endOfDelimiter = s.indexOf("]\n");
            final var delimiter = s.substring(startOfDelimiter, endOfDelimiter);
            final var s2 = s.substring(endOfDelimiter + 2).replace(delimiter, ",");
            return add(s2);
        }
        if (s.startsWith("//")) {
            final var delimiter = String.valueOf(s.charAt(2));
            return add(trimCustomDelimiter(s).replaceAll(delimiter, ","));
        }
        if (s.equals("")) {
            return 0;
        }

        final var split = s.split("[,\n]");
        final var integers = Arrays
            .stream(split)
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        final var negatives = integers.stream().filter((x) -> x < 0).collect(Collectors.toList());
        if (!negatives.isEmpty()) {
            final var negativeNumbers =
                negatives
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            var message = "negatives not allowed: " + negativeNumbers;
            throw new RuntimeException(message);
        }
        return integers
            .stream()
            .filter(i -> i <= 1000)
            .reduce(Integer::sum)
            .orElse(0);
    }

    private String trimCustomDelimiter(String s) {
        return s.substring(4);
    }

}
