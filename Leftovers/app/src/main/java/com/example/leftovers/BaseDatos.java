package com.example.leftovers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private static final String BD_LEFTOVERS = "leftovers_bd";
    private static final String TABLA_COMPRAS = "t_compras";
    private static final String TABLA_CATEGORIAS = "t_categorias";
    private static final String TABLA_INGREDIENTES = "t_ingredientes";


    BaseDatos(Context context){
        super(context, BD_LEFTOVERS,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTabla = "create table "+ TABLA_COMPRAS +
                "(id INTEGER PRIMARY KEY, elemento TEXT)";
        db.execSQL(crearTabla);

        String crearTabla2 = "create table "+ TABLA_CATEGORIAS +
                "(id INTEGER PRIMARY KEY, nombre_categoria TEXT, imagen_categoria BLOB, numero_ing INTEGER, numero_ing_seleccionados INTEGER)";
        db.execSQL(crearTabla2);

        String crearTabla3 = "create table "+ TABLA_INGREDIENTES +
                "(id INTEGER PRIMARY KEY, nombre_ing TEXT, ing_categoria TEXT, agregado TEXT)";
        db.execSQL(crearTabla3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_COMPRAS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_CATEGORIAS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_INGREDIENTES);
        onCreate(db);
    }



    //LISTA DE COMPRAS

    //llena la TABLA_COMPRAS en la clase ListaCompra
    public boolean llenarTablaCompras(String elemento){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("elemento", elemento);
        sqLiteDatabase.insert(TABLA_COMPRAS, null, contentValues);
        return true;
    }

    //Obtiene los registros de la TABLA_COMPRAS y los muestra en la clase ListaCompra
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

    //elimina el registro seleccionado de la TABLA_COMPRAS en la clase ListaCompra
     public void eliminarElementoCompras(String eliminarElemento){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLA_COMPRAS, "elemento = '"+eliminarElemento+"'",null);


    }


    //CATEGORÍAS DE INGREDIENTES

    //llena la TABLA_CATEGORIAS en la clase Categorias
   public void llenarTablaCategorias(String nombre_categoria, byte[] imagen_categoria, int numero_ing, int num_ing_seleccionados){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre_categoria", nombre_categoria);
        contentValues.put("imagen_categoria", imagen_categoria);
        contentValues.put("numero_ing", numero_ing);
        contentValues.put("numero_ing_seleccionados", num_ing_seleccionados);
        sqLiteDatabase.insert(TABLA_CATEGORIAS, null, contentValues);

    }

    //Obtiene los registros de la TABLA_CATEGORIAS y los muestra en el fragment ListaCategoriasFragment
    public ArrayList<GetCategorias> llenarListaCategorias(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<GetCategorias> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLA_CATEGORIAS,null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(new GetCategorias(
                    cursor.getString(cursor.getColumnIndex("nombre_categoria")),
                    cursor.getBlob(cursor.getColumnIndex("imagen_categoria")),
                    cursor.getInt(cursor.getColumnIndex("numero_ing")),
                    cursor.getInt(cursor.getColumnIndex("numero_ing_seleccionados"))

            ));
            cursor.moveToNext();
        }
        return  arrayList;
    }

    //Si la TABLA_CATEGORIAS está vacia regresa true. se usa en la clase Categorías
    public boolean tabla_vacia(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sentencia = "select count(*) from "+ TABLA_CATEGORIAS+ "";
        Cursor cursor = sqLiteDatabase.rawQuery(sentencia, null);
        cursor.moveToFirst();
        int num= cursor.getInt(0);

        if(num>0){
            return false;
        }
        else{
            return true;
        }


    }




    // INGREDIENTES
    public void llenarTablaIngredientes(String nombre_ing, String ing_categoria, String agregado){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre_ing", nombre_ing);
        contentValues.put("ing_categoria", ing_categoria);
        contentValues.put("agregado", agregado);
        sqLiteDatabase.insert(TABLA_INGREDIENTES, null, contentValues);

    }

    //Obtiene los registros de la TABLA_INGREDIENTES y los muestra en el fragment ListaIngredientesFragment
    public ArrayList<GetIngredientes> llenarListaIngredientes(String categoria){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<GetIngredientes> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLA_INGREDIENTES+ " where ing_categoria = '"+categoria+"'",null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(new GetIngredientes(
                    cursor.getString(cursor.getColumnIndex("nombre_ing")),
                    cursor.getString(cursor.getColumnIndex("ing_categoria")),
                    cursor.getString(cursor.getColumnIndex("agregado"))

            ));
            cursor.moveToNext();
        }
        return  arrayList;
    }

    //Si la TABLA_CATEGORIAS está vacia regresa true. se usa en la clase Categorías
    public boolean tabla_vacia_ing(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sentencia = "select count(*) from "+ TABLA_INGREDIENTES+ "";
        Cursor cursor = sqLiteDatabase.rawQuery(sentencia, null);
        cursor.moveToFirst();
        int num= cursor.getInt(0);

        if(num>0){
            return false;
        }
        else{
            return true;
        }


    }















}