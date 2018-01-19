package com.example.root.consultacasos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ingresarCaso(View view){
        Intent intent = new Intent(this,IngresarCasoActivity.class);
        startActivity(intent);

    }

    public void consultarCaso(View view){
        Intent intent = new Intent(this,ListViewActivity.class);
        startActivity(intent);

    }


}
