package com.example.leftovers;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ListaCategoriasFragment extends Fragment {
    BaseDatos baseDatos;
    ListView lista_categorias;
    ArrayList<GetCategorias> categorias;
    AdaptadorCategorias adaptador;
    Categorias categorias1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categorias1 = (Categorias) getActivity();


    }

  NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lista_categorias, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        lista_categorias = view.findViewById(R.id.lista_categorias);


        baseDatos = new BaseDatos(categorias1);
        categorias= new ArrayList<>();
        categorias = baseDatos.llenarListaCategorias();
        adaptador = new AdaptadorCategorias(categorias1, categorias);
        lista_categorias.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();


        lista_categorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GetCategorias obtener = (GetCategorias) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString("seleccionado", obtener.getNombre_categoria());

                navController.navigate(R.id.action_listaCategoriasFragment_to_listaIngredientesFragment, bundle);

            }
        });
    }




}