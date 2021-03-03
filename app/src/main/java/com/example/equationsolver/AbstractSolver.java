package com.example.equationsolver;

/**
 * Базовый абстрактный класс решателя СЛАУ
 * @autor Pegov
 * @version 1.0
 */
public abstract class AbstractSolver {

    /**
     * Матрица коэффициентов при неизвестных
     */
    protected double[][] coeff;

    /**
     * Вектор свободных членов
     */
    protected double[] freeCoeff;

    /**
     * Конструктор класса
     * @param coeff Матрица коэффициентов при неизвестных
     * @param freeCoeff Вектор свободных членов
     */
    public AbstractSolver(double[][] coeff, double[] freeCoeff){
        this.coeff = coeff;
        this.freeCoeff = freeCoeff;
    }

    /**
     * Получение размерности матрицы коэффициентов при неизвестных (количества неизвестных)
     * @return
     */
    public int getRank(){
        return coeff.length;
    }

    /**
     * Поиск решения СЛАУ
     * @return Вектор со значениями найденных неизвестных - решение СЛАУ
     */
    public abstract double[] solve();
}
