package com.example.leftovers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaIngredientesFragment extends Fragment {
    Categorias categorias;
    String categoria_actual = "";
    BaseDatos baseDatos;
    ListView lista_ingredientes;
    ArrayList<GetIngredientes> ingredientes;
    AdaptadorIngredientes adaptador;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categorias = (Categorias) getActivity();
        categoria_actual = getArguments().getString("seleccionado");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_ingredientes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lista_ingredientes = view.findViewById(R.id.lista_ingredientes);

        baseDatos = new BaseDatos(categorias);
        ingredientes= new ArrayList<>();
        ingredientes = baseDatos.llenarListaIngredientes(categoria_actual);
        adaptador = new AdaptadorIngredientes(categorias, ingredientes);
        lista_ingredientes.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();


        Toast.makeText(categorias, categoria_actual, Toast.LENGTH_SHORT).show();
    }
}