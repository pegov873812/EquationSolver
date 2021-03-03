package com.example.equationsolver;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Модульные тесты для класса форматирования уравнений
 * @author Pegov
 */
public class EquationFormatterUnitTest {

    /**
     * Форматирование уравнения с отрицательными и нулевыми коэффициентами
     */
    @Test
    public void formatEquation_negativeMembers() {
        double[] c = new double[]{-2, -1, -3, 0};
        double f = -5;
        String formatted = EquationFormatter.formatEquation(c, f);
        assertEquals("-2a - b - 3c + 0d = -5", formatted);
    }

    /**
     * Форматирование уравнения с положительными и нулевыми коэффициентами
     */
    @Test
    public void formatEquation_positiveMembers() {
        double[] c = new double[]{2, 1, 3, 0};
        double f = 5;
        String formatted = EquationFormatter.formatEquation(c, f);
        assertEquals("2a + b + 3c + 0d = 5", formatted);
    }

    /**
     * Форматирование решения СЛАУ
     */
    @Test
    public void formatEquationSolve() {
        double[] result = new double[]{1, 2, 3, 4, 5, 6};
        String formatted = EquationFormatter.formatEquationSolve(result);
        assertEquals("a = 1.0\r\nb = 2.0\r\nc = 3.0\r\nd = 4.0\r\ne = 5.0\r\nf = 6.0", formatted);
    }
}