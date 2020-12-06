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

import java.awt.font.TextAttribute;
import java.util.ArrayList;

public class AdaptadorIngredientes extends BaseAdapter {
    ArrayList<GetIngredientes> arrayList;
    Context context;

    public AdaptadorIngredientes(Context context, ArrayList<GetIngredientes> arrayList){
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
            convertView = layoutInflater.inflate(R.layout.layout_ingrediente, null);
        }

        final TextView tv_ingrediente, tv_agregado;
        final Button b_agregar_ing;

        tv_ingrediente = convertView.findViewById(R.id.tv_ingrediente);
        tv_agregado = convertView.findViewById(R.id.tv_agregado);
        b_agregar_ing = convertView.findViewById(R.id.b_agregar_ing);

        tv_ingrediente.setText(arrayList.get(position).getNombre_ing());
        tv_agregado.setText(arrayList.get(position).getAgregado());

        if(arrayList.get(position).getAgregado().equals("Agregado")){
            tv_agregado.setTextColor(Color.parseColor("#6BB5B6"));
            b_agregar_ing.setEnabled(false);
        }

        b_agregar_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos baseDatos = new BaseDatos(context);
                String ing = arrayList.get(position).getNombre_ing();
                Toast.makeText(context, "Agregaste a tus ingredientes: "+ing, Toast.LENGTH_SHORT).show();
                baseDatos.agrQuitIngrediente(ing, arrayList.get(position).getAgregado());
                tv_agregado.setTextColor(Color.parseColor("#6BB5B6"));
                tv_agregado.setText("Agregado");


                baseDatos.actAgregados(arrayList.get(position).getIng_categoria());




            }
        });


        return convertView;
    }
}
