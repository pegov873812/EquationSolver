package com.example.equationsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Random;

/**
 * Класс Activity начального экрана приложения
 * @author Pegov
 */
public class MainActivity extends AppCompatActivity {

    static int EDIT_EQUATION_REQUEST = 1;
    static double[][] variableCoefficients = new double[0][0];
    static double[] freeCoefficients = new double[0];
    static int nEquations = 0;

    public MainActivity(){
        setNumberOfEquations(3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayEquations();
    }

    void setNumberOfEquations(int n) {
        double v[][] = new double[n][n];
        double r[] = new double[n];
        Random rnd=new Random(0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < nEquations && j < nEquations) {
                    v[i][j] = variableCoefficients[i][j];
                    r[i] = freeCoefficients[i];
                } else {
                    v[i][j] = rnd.nextInt(20) - 10;
                    r[i] = rnd.nextInt(20) - 10;
                }
            }
        }
        variableCoefficients = v;
        freeCoefficients = r;
        nEquations = n;
    }

    void displayEquations() {
        ((TextView)findViewById(R.id.textEquation1)).setText(formatEquation(0));
        ((TextView)findViewById(R.id.textEquation2)).setText(formatEquation(1));
        ((TextView)findViewById(R.id.textEquation3)).setText(formatEquation(2));
        ((TextView)findViewById(R.id.textEquation4)).setText(formatEquation(3));
        ((TextView)findViewById(R.id.textEquation5)).setText(formatEquation(4));
        ((TextView)findViewById(R.id.textEquation6)).setText(formatEquation(5));
        ((TextView)findViewById(R.id.textEquation3)).setVisibility(nEquations >=3 ? View.VISIBLE : View.INVISIBLE);
        ((TextView)findViewById(R.id.textEquation4)).setVisibility(nEquations >=4 ? View.VISIBLE : View.INVISIBLE);
        ((TextView)findViewById(R.id.textEquation5)).setVisibility(nEquations >=5 ? View.VISIBLE : View.INVISIBLE);
        ((TextView)findViewById(R.id.textEquation6)).setVisibility(nEquations >=6 ? View.VISIBLE : View.INVISIBLE);
        ((Button)findViewById(R.id.btnAddEquation)).setEnabled(nEquations < 6);
        ((Button)findViewById(R.id.btnRemoveEquation)).setEnabled(nEquations > 2);
    }

    String formatEquation(int equationNumber) {
        if (equationNumber >= nEquations) {
            return "";
        }
        double[] v = new double[nEquations];
        double f = freeCoefficients[equationNumber];
        for (int i = 0; i < nEquations; i++) {
            v[i] = variableCoefficients[equationNumber][i];
        }
        return EquationFormatter.formatEquation(v, f);
    }

    public void btnRemoveEquationClick(View view) {
        if(nEquations>2){
            setNumberOfEquations(nEquations-1);
            displayEquations();
        }
    }

    public void btnAddEquationClick(View view) {
        if (nEquations < 6) {
            setNumberOfEquations(nEquations + 1);
            displayEquations();
        }
    }

    public void textEquationClick(View view) {
        int n = Integer.parseInt((String)view.getTag());
        double[] v = new double[nEquations];
        double f = freeCoefficients[n-1];
        for (int i = 0; i < nEquations; i++) {
            v[i] = variableCoefficients[n-1][i];
        }
        Intent intent = new Intent(this, EquationCoefficientsActivity.class);
        intent.putExtra("nEquations", nEquations);
        intent.putExtra("equationNumber", n);
        intent.putExtra("variableCoefficients", v);
        intent.putExtra("freeCoefficient", f);
        startActivityForResult(intent, EDIT_EQUATION_REQUEST);
    }

    public void btnSolveClick(View view) {

        try {
            GaussSolver gaussSolver = new GaussSolver(variableCoefficients, freeCoefficients);
            KramerSolver kramerSolver = new KramerSolver(variableCoefficients, freeCoefficients);
            MatrixSolver matrixSolver = new MatrixSolver(variableCoefficients, freeCoefficients);
            double[] solveGauss = gaussSolver.solve();
            double[] solveKramer = kramerSolver.solve();
            double[] solveMatrix = matrixSolver.solve();

            if (solveMatrix != null) {

                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("nEquations", nEquations);
                intent.putExtra("variableCoefficients", variableCoefficients);
                intent.putExtra("freeCoefficient", freeCoefficients);
                intent.putExtra("solveGauss", solveGauss);
                intent.putExtra("solveKramer", solveKramer);
                intent.putExtra("solveMatrix", solveMatrix);
                startActivity(intent);

            } else {
                showMessage("Внимание!", "Система уравнений не имеет решения.");
                return;
            }
        } catch (Exception ex) {
            showMessage("Ошибка", ex.getLocalizedMessage());
        }
    }

    void showMessage(String title, String text){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(text);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_EQUATION_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle arguments = data.getExtras();
                int equationNumber = (int) arguments.get("equationNumber");
                double[] coefficients = (double[]) arguments.get("variableCoefficients");
                double freeCoefficient = (double) arguments.get("freeCoefficient");
                for (int i = 0; i < nEquations; i++) {
                    variableCoefficients[equationNumber - 1][i] = coefficients[i];
                }
                freeCoefficients[equationNumber - 1] = freeCoefficient;
                displayEquations();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}