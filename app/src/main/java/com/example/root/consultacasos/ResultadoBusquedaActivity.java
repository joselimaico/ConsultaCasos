package com.example.root.consultacasos;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoBusquedaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_busqueda);

        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE);
        String valorID =
                sharedPref.getString(getString(R.string.lastvalue),"None");
        TextView textViewID = (TextView) findViewById(R.id.textViewID);
        textViewID.setText(valorID);

        String valorCliente =
                sharedPref.getString(getString(R.string.value_cliente),"cliente");
        TextView textViewCliente = (TextView) findViewById(R.id.textViewCliente);
        textViewCliente.setText(valorCliente);

        String valorFechaInicio =
                sharedPref.getString(getString(R.string.value_fecha_inicio),"fechaInicio");
        TextView textViewfechaInicio = (TextView) findViewById(R.id.textViewFechaInicio);
        textViewfechaInicio.setText(valorFechaInicio);

        String valorFechaFin=
                sharedPref.getString(getString(R.string.value_fecha_fin),"fechaFin");
        TextView textViewfechaFin = (TextView) findViewById(R.id.textViewFechaFin);
        textViewfechaFin.setText(valorFechaFin);

        String valorEstado =
                sharedPref.getString(getString(R.string.value_estado),"estado");
        TextView textViewEstado = (TextView) findViewById(R.id.textViewEstado);
        textViewEstado.setText(valorEstado);


    }
}
