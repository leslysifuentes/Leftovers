package com.example.leftovers;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Recetas extends AppCompatActivity {

    BaseDatos baseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        baseDatos = new BaseDatos(Recetas.this);
        if(baseDatos.tabla_vacia_r()) {
            baseDatos.llenarTablaRecetas("Bistec", drawableAByte(getDrawable(R.drawable.bistec)), "20 minutos", "2 cdas. Aceite vegetal", "Cebolla", "1/2 kilos Res", "Sal", "Pimienta", getString(R.string.p_bistec), "Quemador de gas", "");
            baseDatos.llenarTablaRecetas("Caramel Macchiato", drawableAByte(getDrawable(R.drawable.caramel_machiato)), "15 minutos", "1/2 tazas Leche", "1/2 tazas Agua", "2 cds Café", "1 cda Jarabe de vainilla", "Caramelo", getString(R.string.p_caramel_macchiato), "Cafetera", "");
            baseDatos.llenarTablaRecetas("Corte de cerdo glaseado", drawableAByte(getDrawable(R.drawable.cerdo_gaseado)), "1 hora", "2 Kilos Puerco", "Sal", "1/2 taza Mantequilla", "Mermelada", "Cátsup", getString(R.string.p_cerdo_glaseado), "Horno", "");
            baseDatos.llenarTablaRecetas("Chocolate caliente", drawableAByte(getDrawable(R.drawable.chocolate_caliente)), "20 minutos", "4 tazas Leche", "1 taza Chocolate", "1/4 tazas Azúcar", "Canela", "Malvaviscos", getString(R.string.p_chocolate_caliente), "Quemador de gas", "");
            baseDatos.llenarTablaRecetas("Ensalada de espinacas", drawableAByte(getDrawable(R.drawable.ensalada)), "15 minutos", "Espinacas", "Lechuga", "Queso", "Nueces", "Aceite de oliva", getString(R.string.p_ensalada), "", "");
            baseDatos.llenarTablaRecetas("Galletas", drawableAByte(getDrawable(R.drawable.galletas)), "30 minutos", "180 g Crema de avellanas", "1 Huevo", "150 g Harina", "Nueces", "", getString(R.string.p_galletas), "Batidora", "Horno");
            baseDatos.llenarTablaRecetas("Palomitas con caramelo", drawableAByte(getDrawable(R.drawable.palomitas_caramelo)), "20 minutos", "Maíz palomero", "Mantequilla", "Miel de abeja", "1/2 cda Vainilla", "Sal", getString(R.string.p_palomitas_caramelo), "Máquina de palomitas", "Quemador de gas");
            baseDatos.llenarTablaRecetas("Pasta de queso", drawableAByte(getDrawable(R.drawable.pasta_queso)), "40 minutos", "1/2 tazas Leche", "2 cds. Crema", "Queso", "Pasta", "", getString(R.string.p_pasta_queso), "Quemador de gas", "");
            baseDatos.llenarTablaRecetas("Pay de limón", drawableAByte(getDrawable(R.drawable.pay_limon)), "1 hora", "1 1/2 tazas Galletas", "1/2 barra Mantequilla", "Leche condensada", "Queso crema", "Limón", getString(R.string.p_pay_limon), "Horno", "Batidora");
            baseDatos.llenarTablaRecetas("Pizza con aceitunas", drawableAByte(getDrawable(R.drawable.pizza_aceitunas)), "1 hora", "4 Aceitunas", "150 g Queso", "Masa de pizza", "Tomate", "", getString(R.string.p_pizza_aceitunas), "Horno", "");
            baseDatos.llenarTablaRecetas("Ravioles", drawableAByte(getDrawable(R.drawable.ravioles)), "30 minutos", "Ravioles", "Mayonesa", "Perejil", "Nueces", "Pimienta", getString(R.string.p_ravioles), "Quemador de gas", "");
            baseDatos.llenarTablaRecetas("Salmón a la plancha", drawableAByte(getDrawable(R.drawable.salmon_plancha)), "35 minutos", "Salmón", "Limón", "Aceite de oliva", "Sal", "Pimienta", getString(R.string.p_salmon_plancha), "", "");
            baseDatos.llenarTablaRecetas("Sándwich de garbanzos y arándanos", drawableAByte(getDrawable(R.drawable.sandwich)), "20 minutos", "Pan", "Garbanzos", "Aguacate", "Arándanos", "Limón", getString(R.string.p_sandwich), "Tostadora", "");
            baseDatos.llenarTablaRecetas("Sushi", drawableAByte(getDrawable(R.drawable.sushi)), "50 minutos", "Arroz para sushi", "Alga nori", "Salmón", "Aguacate", "Pepino", getString(R.string.p_sushi), "Quemador de gas", "");

        }
        else{
            Toast.makeText(this, "Tabla Recetas llena", Toast.LENGTH_SHORT).show();
        }

    /*    String rrr = getString(R.string.p_bistec);
        Toast.makeText(this, ""+rrr, Toast.LENGTH_SHORT).show();
*/

    }

    public  static  byte[] drawableAByte(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}