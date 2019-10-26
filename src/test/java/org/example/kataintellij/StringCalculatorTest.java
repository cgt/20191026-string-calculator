package org.example.kataintellij;

import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private int add(String s) {
        if (s.equals("")) {
            return 0;
        }
        return parseInt(s);
    }

}
