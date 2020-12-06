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
    private static final String TABLA_ELEMENTOS = "t_elementos";
    private static final String TABLA_RECETAS = "t_recetas";




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

        String crearTabla4 = "create table "+ TABLA_ELEMENTOS +
                "(id INTEGER PRIMARY KEY, nombre_ele TEXT, agregado_ele TEXT, imagen_ele BLOB)";
        db.execSQL(crearTabla4);

        String crearTabla5 = "create table "+ TABLA_RECETAS +
                "(id INTEGER PRIMARY KEY, r_nom TEXT, r_ima BLOB, tiempo TEXT, i1 TEXT, i2 TEXT, i3 TEXT, i4 TEXT, i5 TEXT, proc TEXT, e1 TEXT, e2 TEXT)";
        db.execSQL(crearTabla5);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_COMPRAS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_CATEGORIAS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_INGREDIENTES);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_ELEMENTOS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLA_RECETAS);
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
    public int numAgregados(String categoria){

        int numero= 0;
        String sentencia = "select count(*) from "+ TABLA_INGREDIENTES+ " where agregado = 'Agregado' and ing_categoria = '"+categoria+"' ";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        Cursor cursor = sqLiteDatabase.rawQuery(sentencia, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            numero= cursor.getInt(0);
        }
        cursor.close();


        return numero;
    }

        public void actAgregados(String categoria){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("numero_ing", numAgregados(categoria));
            String[] args={categoria};
            sqLiteDatabase.update(TABLA_CATEGORIAS, values, "nombre_categoria = ?", args);
        }



//se usa para agregar o quitar un ingrediente de la lista
    public void agrQuitIngrediente(String ingrediente, String agregado){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(agregado.equals("No agregado")){
            values.put("agregado", "Agregado");
        }
        else {
            values.put("agregado", "No agregado");
        }
        String[] args={ingrediente};
        sqLiteDatabase.update(TABLA_INGREDIENTES, values, "nombre_ing = ?", args);

    }


    //MIS INGREDIENTES

    public ArrayList<GetIngredientes> llenarListaMisIng(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<GetIngredientes> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLA_INGREDIENTES+ " where agregado = 'Agregado'",null );
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


    //ELEMENTOS

    public void llenarTablaElementos(String nombre_ele, String agregado_ele, byte[] imagen_ele){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre_ele", nombre_ele);
        contentValues.put("agregado_ele", agregado_ele);
        contentValues.put("imagen_ele", imagen_ele);
        sqLiteDatabase.insert(TABLA_ELEMENTOS, null, contentValues);

    }

    public ArrayList<GetElementos> llenarListaElementos(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<GetElementos> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLA_ELEMENTOS,null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(new GetElementos(
                    cursor.getString(cursor.getColumnIndex("nombre_ele")),
                    cursor.getString(cursor.getColumnIndex("agregado_ele")),
                    cursor.getBlob(cursor.getColumnIndex("imagen_ele"))

            ));
            cursor.moveToNext();
        }
        return  arrayList;
    }

    public boolean tabla_vacia_ele(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sentencia = "select count(*) from "+ TABLA_ELEMENTOS+ "";
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

    public ArrayList<GetElementos> llenarListaMisEle(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<GetElementos> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLA_ELEMENTOS+ " where agregado_ele = 'Agregado'",null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(new GetElementos(
                    cursor.getString(cursor.getColumnIndex("nombre_ele")),
                    cursor.getString(cursor.getColumnIndex("agregado_ele")),
                    cursor.getBlob(cursor.getColumnIndex("imagen_ele"))
            ));
            cursor.moveToNext();
        }
        return  arrayList;

    }

    public void agrQuitElemento(String elemento, String agregado_el){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(agregado_el.equals("No agregado")){
            values.put("agregado_ele", "Agregado");
        }
        else {
            values.put("agregado_ele", "No agregado");
        }
        String[] args={elemento};
        sqLiteDatabase.update(TABLA_ELEMENTOS, values, "nombre_ele = ?", args);

    }


    public GetElementos obtenerElemento(String nombre_elemento){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        GetElementos elementos = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLA_ELEMENTOS+ " WHERE nombre_ele = ?",new String[]{nombre_elemento});
        if(cursor.moveToFirst()){
            elementos = new GetElementos(
                    cursor.getString(cursor.getColumnIndex("nombre_ele")),
                    cursor.getString(cursor.getColumnIndex("agregado_ele")),
                    cursor.getBlob(cursor.getColumnIndex("imagen_ele"))

            );
        }
        return elementos;
    }


    //RECETAS

    public void llenarTablaRecetas(String r_nom, byte[] r_ima, String tiempo, String i1, String i2, String i3, String i4, String i5, String proc, String e1, String e2){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("r_nom",r_nom);
        contentValues.put("r_ima", r_ima);
        contentValues.put("tiempo", tiempo);
        contentValues.put("i1", i1);
        contentValues.put("i2", i2);
        contentValues.put("i3", i3);
        contentValues.put("i4", i4);
        contentValues.put("i5", i5);
        contentValues.put("proc", proc);
        contentValues.put("e1", e1);
        contentValues.put("e2", e2);
        sqLiteDatabase.insert(TABLA_RECETAS, null, contentValues);

    }

    public ArrayList<GetRecetas> llenarListaRecetas(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<GetRecetas> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLA_RECETAS,null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(new GetRecetas(
                    cursor.getString(cursor.getColumnIndex("r_nom")),
                    cursor.getBlob(cursor.getColumnIndex("r_ima")),
                    cursor.getString(cursor.getColumnIndex("tiempo")),
                    cursor.getString(cursor.getColumnIndex("i1")),
                    cursor.getString(cursor.getColumnIndex("i2")),
                    cursor.getString(cursor.getColumnIndex("i3")),
                    cursor.getString(cursor.getColumnIndex("i4")),
                    cursor.getString(cursor.getColumnIndex("i5")),
                    cursor.getString(cursor.getColumnIndex("proc")),
                    cursor.getString(cursor.getColumnIndex("e1")),
                    cursor.getString(cursor.getColumnIndex("e2"))

            ));
            cursor.moveToNext();
        }
        return  arrayList;
    }

    public boolean tabla_vacia_r(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sentencia = "select count(*) from "+ TABLA_RECETAS+ "";
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

    public GetRecetas obtenerReceta(String nombre_receta){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        GetRecetas recetas = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLA_RECETAS+ " WHERE r_nom = ?",new String[]{nombre_receta});
        if(cursor.moveToFirst()){
            recetas = new GetRecetas(
                    cursor.getString(cursor.getColumnIndex("r_nom")),
                    cursor.getBlob(cursor.getColumnIndex("r_ima")),
                    cursor.getString(cursor.getColumnIndex("tiempo")),
                    cursor.getString(cursor.getColumnIndex("i1")),
                    cursor.getString(cursor.getColumnIndex("i2")),
                    cursor.getString(cursor.getColumnIndex("i3")),
                    cursor.getString(cursor.getColumnIndex("i4")),
                    cursor.getString(cursor.getColumnIndex("i5")),
                    cursor.getString(cursor.getColumnIndex("proc")),
                    cursor.getString(cursor.getColumnIndex("e1")),
                    cursor.getString(cursor.getColumnIndex("e2"))

            );
        }
        return recetas;
    }










}
