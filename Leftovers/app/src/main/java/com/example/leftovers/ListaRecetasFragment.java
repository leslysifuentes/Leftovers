package com.example.leftovers;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListaRecetasFragment extends Fragment {
    BaseDatos baseDatos;
    ListView lista_recetas;
    ArrayList<GetRecetas> recetas;
    AdaptadorRecetas adaptador;
    Recetas recetas1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recetas1 = (Recetas) getActivity();


    }

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lista_recetas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        lista_recetas = view.findViewById(R.id.lista_recetas);

        baseDatos = new BaseDatos(recetas1);
        recetas= new ArrayList<>();
        recetas = baseDatos.llenarListaRecetasFiltradas();
        adaptador = new AdaptadorRecetas(recetas1, recetas);
        lista_recetas.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();

        lista_recetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetRecetas obtener = (GetRecetas) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString("receta", obtener.getR_nom());

                Toast.makeText(recetas1, ""+ obtener.getR_nom(), Toast.LENGTH_SHORT).show();



                navController.navigate(R.id.action_listaRecetasFragment_to_recetaFragment,bundle);
            }
        });






    }
}










