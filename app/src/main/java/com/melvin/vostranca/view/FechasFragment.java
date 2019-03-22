package com.melvin.vostranca.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.melvin.vostranca.R;
import com.melvin.vostranca.controller.ControllerFechas;
import com.melvin.vostranca.model.Fecha;
import com.melvin.vostranca.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FechasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FechasFragment extends Fragment implements AdapterFechas.FechasInterface{

    private OnFragmentInteractionListener mListener;
    private AdapterFechas adapter;
    private List<Fecha> datos = new ArrayList<>();

    public FechasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fechas, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerFechas);
        adapter = new AdapterFechas(datos, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        new ControllerFechas().obtenerFechas(getContext(), new ResultListener<Fecha>() {
            @Override
            public void finish(Fecha resultado) {
                datos.add(resultado);
                adapter.setDatos(datos);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void pasarFecha(String fecha);
    }

    @Override
    public void fechaSeleccionada(String fecha) {
        mListener.pasarFecha(fecha);
    }
}
