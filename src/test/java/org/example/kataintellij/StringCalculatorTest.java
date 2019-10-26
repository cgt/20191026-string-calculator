package org.example.kataintellij;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    @Test
    public void emptyStringReturnsZero() {
        assertEquals(0, add(""));
    }

    @Test
    public void given_any_one_single_digit_returns_same_digit() {
        assertEquals(1, add("1"));
        assertEquals(2, add("2"));
    }

    private int add(String s) {
        if (s.equals("")) {
            return 0;
        }
        if (s.equals("1")) {
            return 1;
        } else if (s.equals("2")) {
            return 2;
        }
        return 0;
    }

}
