package com.melvin.vostranca.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.melvin.vostranca.model.Servicio;
import com.melvin.vostranca.util.ResultListener;

public class DaoFirebaseEventos {

    public void obtenerServicios(final ResultListener<Servicio> lisetnerController){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("eventos");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // TODO Hacer clase evento
                Servicio servicio = dataSnapshot.getValue(Servicio.class);
                lisetnerController.finish(servicio);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
