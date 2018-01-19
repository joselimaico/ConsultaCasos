package com.example.root.consultacasos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/19/17.
 */

public class DatabaseHandler  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    //Nombre de la base de datos
    private static final String DATABASE_NAME = "CasosManager";
    //Nombre de la tabla de contactos
    private static final String TABLE_CASO = "Caso";
    //Nombres de las columnas de la tabla
    private static final String KEY_ID = "id";
    private static final String KEY_CLIENTE = "cliente";
    private static final String KEY_FECHA_INICIO = "fecha_inicio";
    private static final String KEY_FECHA_FIN = "fecha_fin";
    private static final String KEY_ESTADO = "estado";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CASO_TABLE = "CREATE TABLE " + TABLE_CASO +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_CLIENTE + " TEXT," +
                KEY_FECHA_INICIO + " TEXT," +
                KEY_FECHA_FIN + " TEXT,"+
                KEY_ESTADO + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_CASO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addCaso(Caso caso){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CLIENTE, caso.getCliente());
        values.put(KEY_FECHA_INICIO,caso.getFechaInicio());
        values.put(KEY_FECHA_FIN,caso.getFechaFin());
        values.put(KEY_ESTADO,caso.getEstado());
        db.insert(TABLE_CASO,null,values);
        db.close();
    }

    public Caso getCaso(int id){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CASO, new
                String[]{KEY_ID,KEY_CLIENTE,KEY_FECHA_INICIO,KEY_FECHA_FIN,KEY_ESTADO}, KEY_ID+"=?", new
                String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Caso  caso = new Caso(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return caso;
    }

    public List<Caso> getAllCasos(){
        List<Caso> casosList = new ArrayList<Caso>();
        String sql_select = "SELECT * FROM " + TABLE_CASO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql_select, null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Caso caso = new Caso();
                caso.setId(Integer.parseInt(cursor.getString(0)));
                caso.setCliente(cursor.getString(1));
                caso.setFechaInicio(cursor.getString(2));
                caso.setFechaFin(cursor.getString(3));
                caso.setEstado(cursor.getString(4));
                casosList.add(caso);
            }
        }
        return casosList;
    }
}
