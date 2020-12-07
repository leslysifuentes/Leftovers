package com.example.leftovers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorMisIngredientes extends BaseAdapter {
    ArrayList<GetIngredientes> arrayList;
    Context context;

    public AdaptadorMisIngredientes(Context context, ArrayList<GetIngredientes> arrayList){
        this.context=context;
        this.arrayList =arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_mi_ingrediente, null);
        }

        final TextView tv_mi_ingrediente;
        Button b_quitar_ing;

        tv_mi_ingrediente = convertView.findViewById(R.id.tv_mi_ingrediente);
        b_quitar_ing = convertView.findViewById(R.id.b_quitar_ing);

        tv_mi_ingrediente.setText(arrayList.get(position).getNombre_ing());

        b_quitar_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos baseDatos = new BaseDatos(context);
                String ing = arrayList.get(position).getNombre_ing();
                Toast.makeText(context, "Quitaste de tus ingredientes: "+ing, Toast.LENGTH_SHORT).show();
                baseDatos.agrQuitIngrediente(ing, arrayList.get(position).getAgregado());
                tv_mi_ingrediente.setTextColor(Color.parseColor("red"));

                baseDatos.actAgregados(arrayList.get(position).getIng_categoria());
            }
        });




        return convertView;
    }
}








