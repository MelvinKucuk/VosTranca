package com.melvin.vostranca.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.melvin.vostranca.R;

public class MainActivity extends AppCompatActivity implements ServiciosFragment.OnFragmentInteractionListener,
            ReservaFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiciosFragment fragment = new ServiciosFragment();

        ReservaFragment fragment2 = new ReservaFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
