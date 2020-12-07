package com.example.leftovers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaCompra extends AppCompatActivity {
    EditText et_agregar_compra;
    Button b_agregar_compra, b_borrar_compra;
    BaseDatos baseDatos;
    ListView lista_compras;
    ArrayList<GetCompras> compras;
    AdaptadorCompras adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);
        et_agregar_compra = findViewById(R.id.et_agregar_compra);
        b_agregar_compra = findViewById(R.id.b_agregar_compra);
        b_borrar_compra = findViewById(R.id.b_borrar_compra);
        lista_compras = findViewById(R.id.lista_compras);

        b_agregar_compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String elemento_compra = et_agregar_compra.getText().toString();
                if(!elemento_compra.isEmpty()){
                        if (baseDatos.llenarTablaCompras(elemento_compra)) {
                            Toast.makeText(getApplicationContext(), "Agregado", Toast.LENGTH_SHORT).show();
                            adaptador.notifyDataSetChanged();
                            et_agregar_compra.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "No Agregado", Toast.LENGTH_SHORT).show();
                        }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Agrega un elemento de compra", Toast.LENGTH_SHORT).show();
                }
            }
        });

      /*  lista_compras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetCompras obtenerCompra = (GetCompras) parent.getItemAtPosition(position);
                String compra = obtenerCompra.getElemento();
                Toast.makeText(ListaCompra.this, "Borraste "+compra+" de tu lista", Toast.LENGTH_SHORT).show();
                baseDatos.eliminarElementoCompras(compra);
            }
        });*/





        baseDatos = new BaseDatos(ListaCompra.this);
        compras= new ArrayList<>();
        compras = baseDatos.llenarListaCompras();
        adaptador = new AdaptadorCompras(ListaCompra.this, compras);
        lista_compras.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();


    }
}