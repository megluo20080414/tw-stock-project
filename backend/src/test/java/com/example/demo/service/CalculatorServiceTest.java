package com.example.demo.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    @Test
    public void testAdd() {
        CalculatorService calculator = new CalculatorService();
        int result = calculator.add(3, 5);
        assertEquals(8, result);
    }
}