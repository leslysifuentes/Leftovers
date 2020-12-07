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

public class AdaptadorRecetas extends BaseAdapter {
    ArrayList<GetRecetas> arrayList;
    Context context;

    public AdaptadorRecetas(Context context, ArrayList<GetRecetas> arrayList){
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
            convertView = layoutInflater.inflate(R.layout.layout_receta, null);
        }


        ImageView iv_r_ima;
        TextView tv_r_nom,tv_tiempo;

        iv_r_ima = convertView.findViewById(R.id.iv_r_ima);
        tv_r_nom = convertView.findViewById(R.id.tv_r_nom);
        tv_tiempo = convertView.findViewById(R.id.tv_tiempo);

        tv_r_nom.setText(arrayList.get(position).getR_nom());
        tv_tiempo.setText(""+arrayList.get(position).getTiempo());


        GetRecetas recetas = arrayList.get(position);
        byte[] imagen = recetas.getR_ima();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);

        iv_r_ima.setImageBitmap(bitmap);



        return convertView;
    }
}
