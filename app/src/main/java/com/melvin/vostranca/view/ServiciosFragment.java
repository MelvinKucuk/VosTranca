package com.melvin.vostranca.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.melvin.vostranca.R;
import com.melvin.vostranca.controller.ControllerServicios;
import com.melvin.vostranca.model.Servicio;
import com.melvin.vostranca.util.ResultListener;

import java.util.ArrayList;
import java.util.List;


public class ServiciosFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<Servicio> datos = new ArrayList<>();
    private AdapterServicios adapter;

    public ServiciosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_servicios, container, false);

        new ControllerServicios().obtenerServicios(getContext(), new ResultListener<Servicio>() {
            @Override
            public void finish(Servicio resultado) {
                datos.add(resultado);
                adapter.setDatos(datos);
            }
        });

        RecyclerView recycler = view.findViewById(R.id.recyclerServicios);
        adapter = new AdapterServicios(datos);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
