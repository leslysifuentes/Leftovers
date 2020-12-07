package com.example.leftovers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecetaFragment extends Fragment {
    Recetas recetas;
    String receta_actual = "";
    BaseDatos baseDatos;
    TextView tv_rec,tv_tie_rec,tv_i1,tv_i2,tv_i3,tv_i4,tv_i5,tv_proc,tv_ele1,tv_ele2, tv_est;
    ImageView iv_ima_rec, iv_r_e1, iv_r_e2;
    LinearLayout ly_e1, ly_e2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recetas = (Recetas) getActivity();
        receta_actual = getArguments().getString("receta");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_receta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_rec = view.findViewById(R.id.tv_rec);
        tv_tie_rec = view.findViewById(R.id.tv_tie_rec);
        tv_i1 = view.findViewById(R.id.tv_i1);
        tv_i2 = view.findViewById(R.id.tv_i2);
        tv_i3 = view.findViewById(R.id.tv_i3);
        tv_i4 = view.findViewById(R.id.tv_i4);
        tv_i5 = view.findViewById(R.id.tv_i5);
        tv_proc = view.findViewById(R.id.tv_proc);
        tv_ele1 = view.findViewById(R.id.tv_ele1);
        tv_ele2 = view.findViewById(R.id.tv_ele2);
        iv_ima_rec = view.findViewById(R.id.iv_ima_rec);
        ly_e1 = view.findViewById(R.id.ly_e1);
        ly_e2 = view.findViewById(R.id.ly_e2);
        tv_est = view.findViewById(R.id.tv_est);
        iv_r_e1 = view.findViewById(R.id.iv_r_e1);
        iv_r_e2 = view.findViewById(R.id.iv_r_e2);

        baseDatos = new BaseDatos(recetas);


        GetRecetas obt_datos = baseDatos.obtenerReceta(receta_actual);

        tv_rec.setText(obt_datos.getR_nom());
        tv_tie_rec.setText(obt_datos.getTiempo());
        tv_i1.append(obt_datos.getI1());
        tv_i2.append(obt_datos.getI2());
        tv_i3.append(obt_datos.getI3());
        tv_i4.append(obt_datos.getI4());
        tv_i5.append(obt_datos.getI5());
        tv_proc.setText(obt_datos.getProc());
        tv_ele1.setText(obt_datos.getE1());
        tv_ele2.setText(obt_datos.getE2());


        if(obt_datos.getI1().equals("")){
            tv_i1.setVisibility(View.GONE);
        }
        if(obt_datos.getI2().equals("")){
            tv_i2.setVisibility(View.GONE);
        }
        if(obt_datos.getI3().equals("")){
            tv_i3.setVisibility(View.GONE);
        }
        if(obt_datos.getI4().equals("")){
            tv_i4.setVisibility(View.GONE);
        }
        if(obt_datos.getI5().equals("")){
            tv_i5.setVisibility(View.GONE);
        }
        if(obt_datos.getE1().equals("")){
            ly_e1.setVisibility(View.GONE);
        }
        else{
            GetElementos obt_e1 = baseDatos.obtenerElemento(obt_datos.getE1());
            final byte[] i_e1 = obt_e1.getImagen_ele();
            Bitmap bitmap_e1 = BitmapFactory.decodeByteArray(i_e1, 0, i_e1.length);
            iv_r_e1.setImageBitmap(bitmap_e1);
        }
        if(obt_datos.getE2().equals("")){
            ly_e2.setVisibility(View.GONE);
        }
        else{
            GetElementos obt_e2 = baseDatos.obtenerElemento(obt_datos.getE2());
            final byte[] i_e2 = obt_e2.getImagen_ele();
            Bitmap bitmap_e2 = BitmapFactory.decodeByteArray(i_e2, 0, i_e2.length);
            iv_r_e2.setImageBitmap(bitmap_e2);
        }
        if(obt_datos.getE1().equals("") & obt_datos.getE2().equals("")){
            tv_est.setText("Esta receta no requiere uso de elementos");
        }










        final byte[] imagen = obt_datos.getR_ima();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);
        iv_ima_rec.setImageBitmap(bitmap);


    }
}