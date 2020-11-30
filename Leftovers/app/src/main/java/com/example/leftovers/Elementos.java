package com.example.leftovers;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Elementos extends AppCompatActivity {
    BaseDatos baseDatos;
    String no = "No agregado";
    ListView lista_elementos;
    ArrayList<GetElementos> elementos;
    AdaptadorElementos adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementos);
        baseDatos = new BaseDatos(Elementos.this);
        lista_elementos = findViewById(R.id.lista_elementos);

        if(baseDatos.tabla_vacia_ele()) {
            baseDatos.llenarTablaElementos("Batidora",no,drawableAByte(getDrawable(R.drawable.batidora)));
            baseDatos.llenarTablaElementos("Cafetera",no,drawableAByte(getDrawable(R.drawable.cafetera)));
            baseDatos.llenarTablaElementos("Horno",no,drawableAByte(getDrawable(R.drawable.horno)));
            baseDatos.llenarTablaElementos("Horno tostador",no,drawableAByte(getDrawable(R.drawable.horno_tostador)));
            baseDatos.llenarTablaElementos("Licuadora",no,drawableAByte(getDrawable(R.drawable.licuadora)));
            baseDatos.llenarTablaElementos("Máquina de palomitas",no,drawableAByte(getDrawable(R.drawable.maquina_palomitas)));
            baseDatos.llenarTablaElementos("Microondas",no,drawableAByte(getDrawable(R.drawable.microondas)));
            baseDatos.llenarTablaElementos("Parrilla",no,drawableAByte(getDrawable(R.drawable.parrilla)));
            baseDatos.llenarTablaElementos("Picadora de carne",no,drawableAByte(getDrawable(R.drawable.picadora_carne)));
            baseDatos.llenarTablaElementos("Quemador de gas",no,drawableAByte(getDrawable(R.drawable.quemador_gas)));
            baseDatos.llenarTablaElementos("Tetera eléctrica",no,drawableAByte(getDrawable(R.drawable.tetera_electrica)));
            baseDatos.llenarTablaElementos("Tostadora",no,drawableAByte(getDrawable(R.drawable.tostadora)));


        }
        else{
            Toast.makeText(this, "Tabla Elementos llena", Toast.LENGTH_SHORT).show();
        }





        baseDatos = new BaseDatos(Elementos.this);
        elementos= new ArrayList<>();
        elementos = baseDatos.llenarListaElementos();
        adaptador = new AdaptadorElementos(Elementos.this, elementos);
        lista_elementos.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();



    }



    public  static  byte[] drawableAByte(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}