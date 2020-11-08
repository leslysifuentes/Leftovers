package com.example.leftovers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_ingrediente, null);
        }

        TextView tv_ingrediente, tv_agregado;
        Button b_agregar_ing;

        tv_ingrediente = convertView.findViewById(R.id.tv_ingrediente);
        tv_agregado = convertView.findViewById(R.id.tv_agregado);
        b_agregar_ing = convertView.findViewById(R.id.b_agregar_ing);

        tv_ingrediente.setText(arrayList.get(position).getNombre_ing());
        tv_agregado.setText(arrayList.get(position).getAgregado());

        b_agregar_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }
}
