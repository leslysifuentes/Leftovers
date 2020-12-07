package com.example.leftovers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorMisElementos extends BaseAdapter {
    ArrayList<GetElementos> arrayList;
    Context context;

    public AdaptadorMisElementos(Context context, ArrayList<GetElementos> arrayList){
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
            convertView = layoutInflater.inflate(R.layout.layout_mi_elemento, null);
        }

        final TextView tv_nom_mi_ele;
        Button b_quitar_ele;
        ImageView iv_ima_mi_ele;

        tv_nom_mi_ele = convertView.findViewById(R.id.tv_nom_mi_ele);
        b_quitar_ele = convertView.findViewById(R.id.b_quitar_ele);
        iv_ima_mi_ele = convertView.findViewById(R.id.iv_ima_mi_ele);

        tv_nom_mi_ele.setText(arrayList.get(position).getNombre_ele());

        b_quitar_ele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos baseDatos = new BaseDatos(context);
                String ele = arrayList.get(position).getNombre_ele();
                Toast.makeText(context, "Quitaste de tus elementos: "+ele, Toast.LENGTH_SHORT).show();
                baseDatos.agrQuitElemento(ele, arrayList.get(position).getAgregado_ele());
                tv_nom_mi_ele.setTextColor(Color.parseColor("red"));

            }
        });


        GetElementos elementos = arrayList.get(position);
        byte[] imagen = elementos.getImagen_ele();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
        iv_ima_mi_ele.setImageBitmap(bitmap);



        return convertView;

    }
}
