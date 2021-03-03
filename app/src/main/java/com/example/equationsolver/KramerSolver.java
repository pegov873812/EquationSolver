package com.example.equationsolver;

/**
 * Реализация решения СЛАУ методом Крамера
 * @autor Pegov
 * @version 1.0
 */
public class KramerSolver extends AbstractSolver {

    /**
     * Конструктор класса
     * @param coeff Матрица коэффициентов при неизвестных
     * @param freeCoeff Вектор свободных членов
     */
    public KramerSolver(double[][] coeff, double[] freeCoeff){
        super(coeff, freeCoeff);
    }

    /**
     * Поиск решения СЛАУ
     * @return Вектор со значениями найденных неизвестных - решение СЛАУ
     */
    @Override
    public double[] solve() {
        int n = getRank();
        double mainDeterminant = determinantOfMatrix(coeff, n);
        if (mainDeterminant == 0) {
            return null;
        }
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            double determinant = cramerDeretminant(i);
            result[i] = determinant / mainDeterminant;
        }
        return result;
    }

    /**
     * Вычисление определителя матрицы коэффициентов,
     * в которой заданная колонка заменена на столбец свободных коэффициентов
     * @param column Номер колонки для замены на столбец свободных коэффициентов
     * @return Значение определителя
     */
    double cramerDeretminant(int column) {
        int n = getRank();
        double[][] mat = new double[n][n];
        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == column) {
                    mat[i][j] = freeCoeff[i];
                } else {
                    mat[i][j] = coeff[i][j];
                }
            }
        }
        return determinantOfMatrix(mat, n);
    }

    /**
     * Функция вычисляет алгебраическое дополнение матрицы
     * @param mat матрица
     * @param temp алгебраическое дополнение
     * @param p количество строк
     * @param q количество столбцов
     * @param n текущая размерность матрицы
     */
    void cofactor(double mat[][], double temp[][], int p, int q, int n)    {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /**
     * Функция рекурсивно вычисляет определитель переданной матрицы
     * @param mat матрица
     * @param n размерность матрицы
     * @return  возвращает определитель переданной матрицы
     */
    double determinantOfMatrix(double mat[][], int n)
    {
        double D = 0;
        if (n == 1)
            return mat[0][0];
        double temp[][] = new double[n][n];
        int sign = 1;
        for (int f = 0; f < n; f++)
        {
            cofactor(mat, temp, 0, f, n);
            D += sign * mat[0][f] * determinantOfMatrix(temp, n - 1);
            sign = -sign;
        }
        return D;
    }
}
