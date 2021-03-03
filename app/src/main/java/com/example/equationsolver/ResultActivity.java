package com.example.equationsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Класс Activity экрана отображающего результаты решения СЛАУ
 * @author Pegov
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle arguments = getIntent().getExtras();
        double[][] variableCoefficients = (double[][]) arguments.get("variableCoefficients");
        double[] freeCoefficients = (double[]) arguments.get("freeCoefficient");
        double[] solveGauss = (double[]) arguments.get("solveGauss");
        double[] solveKramer = (double[]) arguments.get("solveKramer");
        double[] solveMatrix = (double[]) arguments.get("solveMatrix");

        String result = "Система уравнений:\r\n\r\n";
        result += EquationFormatter.formatEquations(variableCoefficients, freeCoefficients);
        result += "\r\n\r\nРешение методом Гаусса:\r\n\r\n";
        result+=EquationFormatter.formatEquationSolve(solveGauss);
        result += "\r\n\r\nРешение методом Крамера:\r\n\r\n";
        result+=EquationFormatter.formatEquationSolve(solveKramer);
        result += "\r\n\r\nРешение матричным методом:\r\n\r\n";
        result+=EquationFormatter.formatEquationSolve(solveMatrix);

        ((TextView) findViewById(R.id.txbResult)).setText(result);
    }
}