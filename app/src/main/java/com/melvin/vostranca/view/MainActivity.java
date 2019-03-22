package com.melvin.vostranca.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.melvin.vostranca.R;

public class MainActivity extends AppCompatActivity implements ServiciosFragment.OnFragmentInteractionListener,
            ReservaFragment.OnFragmentInteractionListener, FechasFragment.OnFragmentInteractionListener{

    private String fecha = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiciosFragment fragment = new ServiciosFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

    }


    @Override
    public void irFechasFragment() {
        FechasFragment fragment = new FechasFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public void pasarFecha(String fecha) {
        this.fecha = fecha;
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void servicioSeleccionado(String nombre, String descripcion) {
        ReservaFragment fragment = new ReservaFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ReservaFragment.KEY_NOMBRE, nombre);
        bundle.putString(ReservaFragment.KEY_DESCRIPCION, descripcion);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }


}
