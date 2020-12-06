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

public class AdaptadorElementos extends BaseAdapter {
    ArrayList<GetElementos> arrayList;
    Context context;

    public AdaptadorElementos(Context context, ArrayList<GetElementos> arrayList){
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
            convertView = layoutInflater.inflate(R.layout.layout_elemento, null);
        }



        final TextView tv_nombre_ele, tv_agregado_ele;
        Button b_agregar_ele;
        ImageView iv_imagen_ele;

        tv_nombre_ele = convertView.findViewById(R.id.tv_nombre_ele);
        tv_agregado_ele = convertView.findViewById(R.id.tv_agregado_ele);
        b_agregar_ele = convertView.findViewById(R.id.b_agregar_ele);
        iv_imagen_ele = convertView.findViewById(R.id.iv_imagen_ele);


        tv_nombre_ele.setText(arrayList.get(position).getNombre_ele());
        tv_agregado_ele.setText(""+arrayList.get(position).getAgregado_ele());

        if(arrayList.get(position).getAgregado_ele().equals("Agregado")){
            tv_agregado_ele.setTextColor(Color.parseColor("#6BB5B6"));
            b_agregar_ele.setEnabled(false);
        }



        b_agregar_ele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos baseDatos = new BaseDatos(context);
                String ele = arrayList.get(position).getNombre_ele();
                Toast.makeText(context, "Agregaste a tus elementos: "+arrayList.get(position).getAgregado_ele(), Toast.LENGTH_SHORT).show();
                baseDatos.agrQuitElemento(ele, arrayList.get(position).getAgregado_ele());
                tv_agregado_ele.setTextColor(Color.parseColor("#6BB5B6"));
                tv_agregado_ele.setText("Agregado");


            }
        });



        GetElementos elementos = arrayList.get(position);
        byte[] imagen = elementos.getImagen_ele();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
        iv_imagen_ele.setImageBitmap(bitmap);





        return convertView;
    }
}










