package com.example.root.consultacasos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ConsultarCasoActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    Caso caso = new Caso();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_caso);
    }

    public void mostrarBusqueda(View view){
        Intent intent = new Intent(this,ResultadoBusquedaActivity.class);
        consultarCaso();
        List<Caso> caso = db.getAllCasos();
        for(Caso cn:caso){
            String log = null;
            log = "Id: " + cn.getId()+" ,Name: " + cn.getCliente() + " ,F1 " +
                    cn.getFechaInicio();
            Log.d("Caso: ", log);
        }
        startActivity(intent);

    }

    public void consultarCaso(){
        EditText EditTextNumeroCaso = (EditText) findViewById(R.id.textNumeroCaso);
        caso.setId(Integer.parseInt(EditTextNumeroCaso.getText().toString()));
        System.out.println("numero caso "+caso.getId());
        Caso c = db.getCaso(caso.getId());

        SharedPreferences sh1 = getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sh1.edit();
        editor1.putString(getString(R.string.lastvalue),String.valueOf(c.getId()));
        editor1.commit();

        SharedPreferences sharedPref2 =
                getSharedPreferences(getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putString(getString(R.string.value_cliente), c.getCliente());
        editor2.commit();

        SharedPreferences sharedPrefFechaInicio =
                getSharedPreferences(getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editorFechaInicio = sharedPrefFechaInicio.edit();
        editorFechaInicio.putString(getString(R.string.value_fecha_inicio), c.getFechaInicio());
        editorFechaInicio.commit();

        SharedPreferences sharedPrefFechaFin =
                getSharedPreferences(getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editorFechaFin = sharedPrefFechaFin.edit();
        editorFechaFin.putString(getString(R.string.value_fecha_fin), c.getFechaFin());
        editorFechaFin.commit();

        SharedPreferences sharedPrefEstado =
                getSharedPreferences(getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editorEstado = sharedPrefEstado.edit();
        editorEstado.putString(getString(R.string.value_estado), c.getEstado());
        editorEstado.commit();


    }
}
