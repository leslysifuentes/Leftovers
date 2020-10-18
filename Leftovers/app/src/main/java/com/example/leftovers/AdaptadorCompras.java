package com.example.leftovers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorCompras extends BaseAdapter {
    ArrayList<GetCompras> arrayList;
    Context context;

    public AdaptadorCompras(Context context, ArrayList<GetCompras> arrayList){
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

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_compra, null);
        }

        CheckBox cb_elemento;
        Button b_borrar_compra;

        cb_elemento = convertView.findViewById(R.id.cb_elemento);
        b_borrar_compra = convertView.findViewById(R.id.b_borrar_compra);

        cb_elemento.setText(arrayList.get(position).getElemento());
        b_borrar_compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 BaseDatos baseDatos = new BaseDatos(context);
                 String compra = arrayList.get(position).getElemento();
                Toast.makeText(context, "Quitaste de la lista: "+compra, Toast.LENGTH_SHORT).show();
                baseDatos.eliminarElementoCompras(compra);

            }
        });

        return convertView;
    }

}

