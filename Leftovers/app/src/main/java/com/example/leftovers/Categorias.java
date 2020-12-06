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

            baseDatos.llenarTablaCategorias("Bebidas", drawableAByte(getDrawable(R.drawable.bebidas)), 0, 3);
            baseDatos.llenarTablaCategorias("Carnes", drawableAByte(getDrawable(R.drawable.carnes)), 0, 3);
            baseDatos.llenarTablaCategorias("Condimentos", drawableAByte(getDrawable(R.drawable.condimentos)), 0, 3);
            baseDatos.llenarTablaCategorias("Dulces", drawableAByte(getDrawable(R.drawable.dulces)), 0, 3);
            baseDatos.llenarTablaCategorias("Endulzantes", drawableAByte(getDrawable(R.drawable.endulzantes)), 0, 3);
            baseDatos.llenarTablaCategorias("Frutas", drawableAByte(getDrawable(R.drawable.frutas)), 0, 3);
            baseDatos.llenarTablaCategorias("Hornear", drawableAByte(getDrawable(R.drawable.hornear)), 0, 3);
            baseDatos.llenarTablaCategorias("Mariscos", drawableAByte(getDrawable(R.drawable.mariscos)), 0, 3);
            baseDatos.llenarTablaCategorias("Salsas", drawableAByte(getDrawable(R.drawable.salsas)), 0, 3);
            baseDatos.llenarTablaCategorias("Semillas", drawableAByte(getDrawable(R.drawable.semillas)), 0, 3);
            baseDatos.llenarTablaCategorias("Verduras", drawableAByte(getDrawable(R.drawable.verduras)), 0, 3);
        }
        else{
            Toast.makeText(this, "Tabla Categorias llena", Toast.LENGTH_SHORT).show();
        }




        if(baseDatos.tabla_vacia_ing()){
            baseDatos.llenarTablaIngredientes("Leche", "Bebidas", "No agregado");
            baseDatos.llenarTablaIngredientes("Jugo", "Bebidas", "No agregado");
            baseDatos.llenarTablaIngredientes("Té", "Bebidas", "No agregado");
            baseDatos.llenarTablaIngredientes("Pollo", "Carnes", "No agregado");
            baseDatos.llenarTablaIngredientes("Puerco", "Carnes", "No agregado");
            baseDatos.llenarTablaIngredientes("Res", "Carnes", "No agregado");
            baseDatos.llenarTablaIngredientes("Pimienta", "Condimentos", "No agregado");
            baseDatos.llenarTablaIngredientes("Sal", "Condimentos", "No agregado");
            baseDatos.llenarTablaIngredientes("Canela", "Condimentos", "No agregado");
            baseDatos.llenarTablaIngredientes("Chocolate", "Dulces", "No agregado");
            baseDatos.llenarTablaIngredientes("Mermelada", "Dulces", "No agregado");
            baseDatos.llenarTablaIngredientes("Caramelo", "Dulces", "No agregado");
            baseDatos.llenarTablaIngredientes("Azúcar", "Endulzantes", "No agregado");
            baseDatos.llenarTablaIngredientes("Miel", "Endulzantes", "No agregado");
            baseDatos.llenarTablaIngredientes("Jarabe de maíz", "Endulzantes", "No agregado");
            baseDatos.llenarTablaIngredientes("Manzana", "Frutas", "No agregado");
            baseDatos.llenarTablaIngredientes("Piña", "Frutas", "No agregado");
            baseDatos.llenarTablaIngredientes("Naranja", "Frutas", "No agregado");
            baseDatos.llenarTablaIngredientes("Harina", "Hornear", "No agregado");
            baseDatos.llenarTablaIngredientes("Pasta", "Hornear", "No agregado");
            baseDatos.llenarTablaIngredientes("Polvo para hornear", "Hornear", "No agregado");
            baseDatos.llenarTablaIngredientes("Pescado", "Mariscos", "No agregado");
            baseDatos.llenarTablaIngredientes("Cangrejo", "Mariscos", "No agregado");
            baseDatos.llenarTablaIngredientes("Pulpo", "Mariscos", "No agregado");
            baseDatos.llenarTablaIngredientes("Salsa de tomate", "Salsas", "No agregado");
            baseDatos.llenarTablaIngredientes("Salsa de soja", "Salsas", "No agregado");
            baseDatos.llenarTablaIngredientes("Salsa picante", "Salsas", "No agregado");
            baseDatos.llenarTablaIngredientes("Almendras", "Semillas", "No agregado");
            baseDatos.llenarTablaIngredientes("Nueces", "Semillas", "No agregado");
            baseDatos.llenarTablaIngredientes("Manies", "Semillas", "No agregado");
            baseDatos.llenarTablaIngredientes("Tomate", "Verduras", "No agregado");
            baseDatos.llenarTablaIngredientes("Lechuga", "Verduras", "No agregado");
            baseDatos.llenarTablaIngredientes("Cebolla", "Verduras", "No agregado");

        }
        else{
            Toast.makeText(this, "Tabla Ingredientes llena", Toast.LENGTH_SHORT).show();
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