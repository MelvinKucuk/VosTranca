package com.melvin.vostranca.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melvin.vostranca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {

    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_FECHA = "fecha";


    public DetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

}
