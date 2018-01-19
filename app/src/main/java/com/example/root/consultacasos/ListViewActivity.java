package com.example.root.consultacasos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    private ListView lstView;
    private ArrayList<String> listViewItems = new ArrayList<String>();
    private List<Caso> listacasos = new ArrayList<>();
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listacasos = db.getAllCasos();


        lstView = (ListView) findViewById(R.id.mainListView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listViewItems);
        lstView.setAdapter(adapter);
        for (int i = 0;i<listacasos.size();i++){
            adapter.add(listacasos.get(i).getCliente().toString());
        }

        adapter.notifyDataSetChanged();
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(view.getContext(),ResultadoBusquedaActivity.class);

                SharedPreferences sharedPref1 = getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPref1.edit();
                editor1.putString(getString(R.string.lastvalue),String.valueOf(listacasos.get(position).getId()));
                editor1.commit();

                SharedPreferences sharedPref2 =
                        getSharedPreferences(getString(R.string.preference_file_key),
                                Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPref2.edit();
                editor2.putString(getString(R.string.value_cliente), listacasos.get(position).getCliente());
                editor2.commit();

                SharedPreferences sharedPrefFechaInicio =
                        getSharedPreferences(getString(R.string.preference_file_key),
                                Context.MODE_PRIVATE);
                SharedPreferences.Editor editorFechaInicio = sharedPrefFechaInicio.edit();
                editorFechaInicio.putString(getString(R.string.value_fecha_inicio), listacasos.get(position).getFechaInicio());
                editorFechaInicio.commit();

                SharedPreferences sharedPrefFechaFin =
                        getSharedPreferences(getString(R.string.preference_file_key),
                                Context.MODE_PRIVATE);
                SharedPreferences.Editor editorFechaFin = sharedPrefFechaFin.edit();
                editorFechaFin.putString(getString(R.string.value_fecha_fin), listacasos.get(position).getFechaFin());
                editorFechaFin.commit();

                SharedPreferences sharedPrefEstado =
                        getSharedPreferences(getString(R.string.preference_file_key),
                                Context.MODE_PRIVATE);
                SharedPreferences.Editor editorEstado = sharedPrefEstado.edit();
                editorEstado.putString(getString(R.string.value_estado), listacasos.get(position).getEstado());
                editorEstado.commit();

                startActivity(intent);

            }
        });
    }
}
