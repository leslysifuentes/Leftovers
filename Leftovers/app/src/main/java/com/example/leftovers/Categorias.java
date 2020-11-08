package com.example.leftovers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Categorias extends AppCompatActivity {
    BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        baseDatos = new BaseDatos(Categorias.this);

        if(baseDatos.tabla_vacia()) {

            baseDatos.llenarTablaCategorias("Bebidas", drawableAByte(getDrawable(R.drawable.bebidas)), 0, 0);
            baseDatos.llenarTablaCategorias("Carnes", drawableAByte(getDrawable(R.drawable.carnes)), 0, 0);
            baseDatos.llenarTablaCategorias("Condimentos", drawableAByte(getDrawable(R.drawable.condimentos)), 0, 0);
            baseDatos.llenarTablaCategorias("Dulces", drawableAByte(getDrawable(R.drawable.dulces)), 0, 0);
            baseDatos.llenarTablaCategorias("Endulzantes", drawableAByte(getDrawable(R.drawable.endulzantes)), 0, 0);
            baseDatos.llenarTablaCategorias("Frutas", drawableAByte(getDrawable(R.drawable.frutas)), 0, 0);
            baseDatos.llenarTablaCategorias("Hornear", drawableAByte(getDrawable(R.drawable.hornear)), 0, 0);
            baseDatos.llenarTablaCategorias("Mariscos", drawableAByte(getDrawable(R.drawable.mariscos)), 0, 0);
            baseDatos.llenarTablaCategorias("Salsas", drawableAByte(getDrawable(R.drawable.salsas)), 0, 0);
            baseDatos.llenarTablaCategorias("Semillas", drawableAByte(getDrawable(R.drawable.semillas)), 0, 0);
            baseDatos.llenarTablaCategorias("Verduras", drawableAByte(getDrawable(R.drawable.verduras)), 0, 0);
        }
        else{
            Toast.makeText(this, "Tabla llena", Toast.LENGTH_SHORT).show();
        }

    }

    public  static  byte[] drawableAByte(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}