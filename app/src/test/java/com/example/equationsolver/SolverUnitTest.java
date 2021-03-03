package com.example.equationsolver;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Модульные тесты для классов решения системы уравнений
 * @author Pegov
 */
public class SolverUnitTest {

    /**
    * Решение СЛАУ методом Гаусса: система имеет решение
    */
    @Test
    public void GaussSolverTest_hasSolve() {
        double[][] coeffs = new double[][]{
                {3, 8},
                {-1, 4}
        };
        double[] free = new double[]{46, 18};
        AbstractSolver solver = new GaussSolver(coeffs, free);
        double[] result = solver.solve();
        assertEquals(2, result.length);
        assertEquals(2.0, result[0], 0.0001);
        assertEquals(5.0, result[1], 0.0001);
    }

    /**
     * Решение СЛАУ методом Крамера: система имеет решение
     */
    @Test
    public void KramerSolverTest_hasSolve(){
        double[][] coeffs = new double[][]{
                {3, 8},
                {-1, 4}
        };
        double[] free = new double[]{46, 18};
        AbstractSolver solver = new KramerSolver(coeffs, free);
        double[] result = solver.solve();
        assertEquals(2, result.length);
        assertEquals(2.0, result[0], 0.0001);
        assertEquals(5.0, result[1], 0.0001);
    }

    /**
     * Решение СЛАУ матричным методом: система имеет решение
     */
    @Test
    public void MatrixSolverTest_hasSolve(){
        double[][] coeffs = new double[][]{
                {3, 8},
                {-1, 4}
        };
        double[] free = new double[]{46, 18};
        AbstractSolver solver = new MatrixSolver(coeffs, free);
        double[] result = solver.solve();
        assertEquals(2, result.length);
        assertEquals(2.0, result[0], 0.0001);
        assertEquals(5.0, result[1], 0.0001);
    }

    /**
     * Решение СЛАУ методом Гаусса: система не имеет решения
     */
    @Test
    public void GaussSolverTest_noSolve() {
        double[][] coeffs = new double[][]{
                {1, 1},
                {1, 1}
        };
        double[] free = new double[]{1, 1};
        AbstractSolver solver = new GaussSolver(coeffs, free);
        double[] result = solver.solve();
        assertEquals(2, result.length);
        assertTrue(Double.isNaN(result[0]));
        assertTrue(Double.isNaN(result[1]));
    }

    /**
     * Решение СЛАУ методом Крамера: система не имеет решения
     */
    @Test
    public void KramerSolverTest_noSolve(){
        double[][] coeffs = new double[][]{
                {1, 1},
                {1, 1}
        };
        double[] free = new double[]{1, 1};
        AbstractSolver solver = new KramerSolver(coeffs, free);
        double[] result = solver.solve();
        assertEquals(null, result);
    }

    /**
     * Решение СЛАУ матричным методом: система не имеет решения
     */
    @Test
    public void MatrixSolverTest_noSolve(){
        double[][] coeffs = new double[][]{
                {1, 1},
                {1, 1}
        };
        double[] free = new double[]{1, 1};
        AbstractSolver solver = new MatrixSolver(coeffs, free);
        double[] result = solver.solve();
        assertEquals(null, result);
    }
}
