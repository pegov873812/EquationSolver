package com.example.equationsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity экранной формы ввода коэффициентов для одного уравнения
 * @author Pegov
 */
public class EquationCoefficientsActivity extends AppCompatActivity {

    static int nEquations;
    static int equationNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_coefficients);

        Bundle arguments = getIntent().getExtras();
        nEquations = (int) arguments.get("nEquations");
        equationNumber = (int) arguments.get("equationNumber");
        double[] variableCoefficients = (double[]) arguments.get("variableCoefficients");
        double freeCoefficient = (double) arguments.get("freeCoefficient");

        ((EditText) findViewById(R.id.txbCoefficient1)).setText(String.valueOf(variableCoefficients[0]));
        ((EditText) findViewById(R.id.txbCoefficient2)).setText(String.valueOf(variableCoefficients[1]));
        ((EditText) findViewById(R.id.txbFreeCoefficient)).setText(String.valueOf(freeCoefficient));
        if (nEquations >= 3) {
            ((EditText) findViewById(R.id.txbCoefficient3)).setText(String.valueOf(variableCoefficients[2]));
        } else {
            ((EditText) findViewById(R.id.txbCoefficient3)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.lblCoefficient3)).setVisibility(View.INVISIBLE);
        }
        if (nEquations >= 4) {
            ((EditText) findViewById(R.id.txbCoefficient4)).setText(String.valueOf(variableCoefficients[3]));
        } else {
            ((EditText) findViewById(R.id.txbCoefficient4)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.lblCoefficient4)).setVisibility(View.INVISIBLE);
        }
        if (nEquations >= 5) {
            ((EditText) findViewById(R.id.txbCoefficient5)).setText(String.valueOf(variableCoefficients[4]));
        } else {
            ((EditText) findViewById(R.id.txbCoefficient5)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.lblCoefficient5)).setVisibility(View.INVISIBLE);
        }
        if (nEquations >= 6) {
            ((EditText) findViewById(R.id.txbCoefficient6)).setText(String.valueOf(variableCoefficients[5]));
        } else {
            ((EditText) findViewById(R.id.txbCoefficient6)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.lblCoefficient6)).setVisibility(View.INVISIBLE);
        }
    }

    public void btnOkClick(View view) {
        double[] coefficients = new double[nEquations];
        coefficients[0] = getDouble(R.id.txbCoefficient1);
        coefficients[1] = getDouble(R.id.txbCoefficient2);
        if (nEquations >= 3) {
            coefficients[2] = getDouble(R.id.txbCoefficient3);
        }
        if (nEquations >= 4) {
            coefficients[3] = getDouble(R.id.txbCoefficient4);
        }
        if (nEquations >= 5) {
            coefficients[4] = getDouble(R.id.txbCoefficient5);
        }
        if (nEquations >= 6) {
            coefficients[5] = getDouble(R.id.txbCoefficient6);
        }
        Intent data = new Intent();
        data.putExtra("equationNumber", equationNumber);
        data.putExtra("freeCoefficient", getDouble(R.id.txbFreeCoefficient));
        data.putExtra("variableCoefficients", coefficients);
        setResult(RESULT_OK, data);
        finish();
    }

    double getDouble(int textBoxId) {
        String text = ((EditText) findViewById(textBoxId)).getText().toString();
        if ("".equals(text)) {
            return 0;
        } else {
            try {
                return Double.parseDouble(text.trim());
            } catch (Exception e) {
                return 0;
            }
        }
    }
}