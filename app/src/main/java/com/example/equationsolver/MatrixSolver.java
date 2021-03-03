package com.example.equationsolver;

/**
 * Реализация решения СЛАУ матричным методом
 * @autor Pegov
 * @version 1.0
 */
public class MatrixSolver extends AbstractSolver {

    /**
     * Конструктор класса
     * @param coeff Матрица коэффициентов при неизвестных
     * @param freeCoeff Вектор свободных членов
     */
    public MatrixSolver(double[][] coeff, double[] freeCoeff){
        super(coeff, freeCoeff);
    }

    /**
     * Поиск решения СЛАУ
     * @return Вектор со значениями найденных неизвестных - решение СЛАУ
     */
    @Override
    public double[] solve() {
        int n = getRank();
        double[][] inverse = inverse(coeff);
        if(inverse==null) {
            return null;
        }
        double[] result = new double[n];
        for(int i=0; i<n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++) {
                result[i] += inverse[i][j] * freeCoeff[j];
            }
        }
        return result;
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

    /**
     * Вычисление обратной матрицы
     * @return  возвращает обратную матрицу для матрицы
     */
    double[][] inverse(double[][] matrix) {
        double[][] inverse = new double[matrix.length][matrix.length];
        double det = determinantOfMatrix(matrix, matrix.length);
        if (det == 0) {
            return null;
        }
        double[][] adj = new double[matrix.length][matrix.length];
        adjoint(matrix, adj);
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                inverse[i][j] = adj[i][j] / (double) det;
        return inverse;
    }

    /**
     * Функция вычисляет присоединенную матрицу для переданной матрицы
     * @param mat матрица
     * @param adj присоединенная матрица
     */
    void adjoint(double mat[][],double [][]adj)
    {
        int N = mat.length;
        if (N == 1)
        {
            adj[0][0] = 1;
            return;
        }
        int sign = 1;
        double [][]temp = new double[N][N];

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                cofactor(mat, temp, i, j, N);
                sign = ((i + j) % 2 == 0)? 1: -1;
                adj[j][i] = (sign)*(determinantOfMatrix(temp, N-1));
            }
        }
    }

    /**
     * Функция возвращает результат умножения 2 матриц
     * @param firstMatrix 1 умножаемая матрица
     * @param secondMatrix 2 умножаемая матрица
     * @return  возвращает результат умножения 2 матриц
     */
    double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = firstMatrix[row][col] * secondMatrix[row][col];
            }
        }
        return result;
    }
}
