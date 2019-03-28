package com.melvin.vostranca.view;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.melvin.vostranca.R;
import com.melvin.vostranca.model.Servicio;

public class MainActivity extends AppCompatActivity implements ServiciosFragment.OnFragmentInteractionListener,
            ReservaFragment.OnFragmentInteractionListener, FechasFragment.OnFragmentInteractionListener,
            EventosFragment.OnFragmentInteractionListener {

    private String fecha = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);

        final ServiciosFragment fragmentServicios = new ServiciosFragment();
        final EventosFragment fragmentEventos = new EventosFragment();

        cargarFragment(fragmentServicios);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNaviagtionMain);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.itemServicios:
                        limpiarBackStack();
                        cargarFragment(fragmentServicios);
                        return true;
                    case R.id.itemEventos:
                        limpiarBackStack();
                        cargarFragment(fragmentEventos);
                        return true;
                }

                return false;
            }
        });

        FirebaseMessaging.getInstance().unsubscribeFromTopic("push");

    }

    private void cargarFragment(Fragment unFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, unFragment).commit();
    }

    private void limpiarBackStack(){
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); i++){
            fm.popBackStack();
        }
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

    @Override
    public void onFragmentInteraction() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);
    }

}
