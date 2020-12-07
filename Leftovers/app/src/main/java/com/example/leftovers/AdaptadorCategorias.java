package com.example.leftovers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCategorias extends BaseAdapter {
    ArrayList<GetCategorias> arrayList;
    Context context;

    public AdaptadorCategorias(Context context, ArrayList<GetCategorias> arrayList){
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
            convertView = layoutInflater.inflate(R.layout.layout_categoria, null);
        }


        ImageView iv_imagen_categoria;
        TextView tv_nombre_categoria,tv_num_ing,tv_num_ing_sel;

        iv_imagen_categoria = convertView.findViewById(R.id.iv_imagen_categoria);
        tv_nombre_categoria = convertView.findViewById(R.id.tv_nombre_categoria);
        tv_num_ing = convertView.findViewById(R.id.tv_num_ing);
        tv_num_ing_sel = convertView.findViewById(R.id.tv_num_ing_sel);

        tv_nombre_categoria.setText(arrayList.get(position).getNombre_categoria());
        tv_num_ing.setText(""+arrayList.get(position).getNumero_ing());
        tv_num_ing_sel.setText(""+arrayList.get(position).getNumero_ing_seleccionados());




        GetCategorias categorias = arrayList.get(position);
        byte[] imagen = categorias.getImagen_categoria();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);

        iv_imagen_categoria.setImageBitmap(bitmap);


        BaseDatos baseDatos = new BaseDatos(context);
        baseDatos.actnumIng(arrayList.get(position).getNombre_categoria());

        return convertView;
    }
}
