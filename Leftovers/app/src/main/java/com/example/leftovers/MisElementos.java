package com.example.leftovers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MisElementos extends AppCompatActivity {
    ListView lista_mis_elementos;
    BaseDatos baseDatos;
    ArrayList<GetElementos> mis_elementos;
    AdaptadorMisElementos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_elementos);
        lista_mis_elementos = findViewById(R.id.lista_mis_elementos);




        baseDatos = new BaseDatos(MisElementos.this);
        mis_elementos= new ArrayList<>();
        mis_elementos = baseDatos.llenarListaMisEle();
        adaptador = new AdaptadorMisElementos(MisElementos.this, mis_elementos);
        lista_mis_elementos.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();

    }
}