package com.example.leftovers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaMisIngredientes extends AppCompatActivity {
    ListView lista_mis_ingredientes;
    BaseDatos baseDatos;
    ArrayList<GetIngredientes> mis_ingredientes;
    AdaptadorMisIngredientes adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mis_ingredientes);
        lista_mis_ingredientes = findViewById(R.id.lista_mis_ingredientes);


        baseDatos = new BaseDatos(ListaMisIngredientes.this);
        mis_ingredientes= new ArrayList<>();
        mis_ingredientes = baseDatos.llenarListaMisIng();
        adaptador = new AdaptadorMisIngredientes(ListaMisIngredientes.this, mis_ingredientes);
        lista_mis_ingredientes.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
    }
}