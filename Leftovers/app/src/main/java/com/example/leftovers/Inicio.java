package com.example.leftovers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {
    CardView cv_recetas, cv_ingredientes, cv_elementos, cv_lista_compras, cv_agregar_ingrediente, cv_agregar_elemento;
    Intent in_recetas, in_ingredientes, in_elementos, in_lista_compras, in_agregar_ingrediente, in_agregar_elemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        cv_recetas = findViewById(R.id.cv_recetas);
        cv_ingredientes = findViewById(R.id.cv_ingredientes);
        cv_elementos = findViewById(R.id.cv_elementos);
        cv_lista_compras = findViewById(R.id.cv_lista_compras);
        cv_agregar_ingrediente = findViewById(R.id.cv_agregar_ingrediente);
        cv_agregar_elemento = findViewById(R.id.cv_agregar_elemento);

        in_recetas = new Intent(this, ListaCompra.class);
        in_ingredientes = new Intent(this, ListaCompra.class);
        in_elementos = new Intent(this, ListaCompra.class);
        in_lista_compras = new Intent(this,ListaCompra.class);
        in_agregar_ingrediente = new Intent(this, Categorias.class);
        in_agregar_elemento = new Intent(this, ListaCompra.class);


        cv_lista_compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in_lista_compras);
            }
        });

        cv_agregar_ingrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in_agregar_ingrediente);
            }
        });




    }
}