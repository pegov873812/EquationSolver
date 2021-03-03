package com.example.equationsolver;

import java.text.NumberFormat;

/**
 * Реализация форматирования уравнений
 * @author Pegov
 */
public class EquationFormatter {

    static String[] letters = new String[]{"a", "b", "c", "d", "e", "f"};

    /**
     * Форматирование одного уравнения
     * @param variableCoeffs коэффициенты при неизвестных
     * @param freeCoeff свободный коэффициент
     * @return возвращает строковое представление уравнения
     */
    public static String formatEquation(double[] variableCoeffs, double freeCoeff) {
        String result = "";
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(0);
        for (int i = 0; i < variableCoeffs.length; i++) {
            double v = variableCoeffs[i];
            String t = "";
            if (i != 0) {
                if (v >= 0) {
                    result += " + ";
                } else {
                    result += " - ";
                }
            }else {
                if (v < 0) {
                    result += "-";
                }
            }
            if (Math.abs(v) != 1.0) {
                result += nf.format(Math.abs(v)) + letters[i];
            } else {
                result += letters[i];
            }
        }
        result += " = " + nf.format(freeCoeff);
        return result;
    }

    /**
     * Форматировние системы уравнений
     * @param variableCoeffs коэффициенты при неизвестных
     * @param freeCoeffs свободные коэффициенты
     * @return возвращает строковое представление системы уравнений
     */
    public static String formatEquations(double[][] variableCoeffs, double[] freeCoeffs) {
        String result = "";
        for (int i = 0; i < freeCoeffs.length; i++) {
            double[] v = new double[freeCoeffs.length];
            double f = freeCoeffs[i];
            for (int j = 0; j < variableCoeffs[i].length; j++) {
                v[j] = variableCoeffs[i][j];
            }
            if (i > 0) {
                result += "\r\n";
            }
            result += formatEquation(v, f);
        }
        return result;
    }

    /**
     * Форматирование результата решения системы уравнений
     * @param solve значения неизвестных
     * @return возвращает строку с именами и значениями неизвестных через знак '='
     */
    public static String formatEquationSolve(double[] solve) {
        String result = "";
        for (int i = 0; i < solve.length; i++) {
            if (i > 0) {
                result += "\r\n";
            }
            result += letters[i] + " = " + String.valueOf(solve[i]);
        }
        return result;
    }

}
