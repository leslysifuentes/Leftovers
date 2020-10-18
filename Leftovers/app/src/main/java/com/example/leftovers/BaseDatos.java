package com.example.leftovers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private static final String BD_LEFTOVERS = "leftovers_bd";
    private static final String TABLA_COMPRAS = "t_compras";

    BaseDatos(Context context){
        super(context, BD_LEFTOVERS,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTabla = "create table "+ TABLA_COMPRAS +
                "(id INTEGER PRIMARY KEY, elemento TEXT)";
        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_COMPRAS);
        onCreate(db);
    }



    //LISTA DE COMPRAS

    //
    public boolean llenarTablaCompras(String elemento){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("elemento", elemento);
        sqLiteDatabase.insert(TABLA_COMPRAS, null, contentValues);
        return true;
    }

    public ArrayList<GetCompras> llenarListaCompras(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<GetCompras> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLA_COMPRAS,null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(new GetCompras(
                    cursor.getString(cursor.getColumnIndex("elemento"))
            ));
            cursor.moveToNext();
        }
        return  arrayList;
    }

    public GetCompras registroCompra(String elemento){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        GetCompras compras = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLA_COMPRAS+ " WHERE elemento = ?",new String[]{elemento});
        if(cursor.moveToFirst()){
            compras = new GetCompras(
                    cursor.getString(cursor.getColumnIndex("elemento"))
            );
        }
        return compras;
    }
     public void eliminarElementoCompras(String eliminarElemento){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLA_COMPRAS, "elemento = '"+eliminarElemento+"'",null);


    }










}
