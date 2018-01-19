package com.example.root.consultacasos;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class IngresarCasoActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView dateView;
    private TextView dateViewFin;
    private int year, month, day;
    private Spinner spinner1;
    DatabaseHandler db = new DatabaseHandler(this);
    Caso caso = new Caso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_caso);
        dateView = (EditText) findViewById(R.id.textFechaInicio);
        dateViewFin= (EditText) findViewById(R.id.textFechaFin);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate("Inicio",year,month,day);
        showDate("Fin",year,month,day);
    }

    @SuppressWarnings("deprecation")
    public void setDate_FechaInicio(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(),"Ejemplo de calendario",
                Toast.LENGTH_SHORT).show();
    }
    public void setDate_FechaFin(View view) {
        showDialog(1000);
        Toast.makeText(getApplicationContext(),"Ejemplo de calendario",
                Toast.LENGTH_SHORT).show();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListenerInicio, year, month, day);
        }
        if (id ==1000){
            return new DatePickerDialog(this, myDateListenerFin, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListenerInicio = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
                {
                    showDate("Inicio",arg1, arg2+1, arg3);
                }
            };
    private DatePickerDialog.OnDateSetListener myDateListenerFin = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
                {
                    showDate("Fin",arg1, arg2+1, arg3);
                }
            };
    private void showDate(String context, int year, int month, int day) {
        if (context.equals("Inicio")){
            dateView.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
        }
        if(context.equals("Fin")){
            dateViewFin.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
        }

    }
    public void setList(View view) {
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        Toast.makeText(getApplicationContext(), "OnClickListener : " +
                        "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                Toast.LENGTH_SHORT).show();
    }

    public void ingresarCaso(View view){
        EditText editTextCliente = (EditText) findViewById(R.id.textCliente);
        EditText editTextfechaInicio = (EditText) findViewById(R.id.textFechaInicio);
        EditText editTextfechaFin = (EditText) findViewById(R.id.textFechaFin);
        Spinner Estado= (Spinner) findViewById(R.id.spinner1);
        caso.setCliente( editTextCliente.getText().toString());
        caso.setFechaInicio(editTextfechaInicio.getText().toString());
        caso.setFechaFin(editTextfechaFin.getText().toString()) ;
        caso.setEstado( Estado.getSelectedItem().toString()) ;

        db.addCaso(new Caso(caso.getCliente(),caso.getFechaInicio(),caso.getFechaFin(),caso.getEstado()));


    }
}
