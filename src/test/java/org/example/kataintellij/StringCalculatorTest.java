package org.example.kataintellij;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    @Test
    public void nothing() {
    }

    @Test
    public void emptyStringReturnsZero() {
        assertEquals(0, add(""));
    }


    @Test
    public void given_1_returns_1() {
        assertEquals(1, add("1"));
    }

    private int add(String s) {
        if (s.equals("1")) {
            return 1;
        }
        return 0;
    }

}
