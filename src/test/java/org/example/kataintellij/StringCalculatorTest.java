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

    private int add(String s) {
        return 0;
    }

}
