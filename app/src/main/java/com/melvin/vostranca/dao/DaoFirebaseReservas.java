package com.melvin.vostranca.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.melvin.vostranca.model.Reserva;
import com.melvin.vostranca.util.ResultListener;


public class DaoFirebaseReservas {

    public void obtenerReservas(final ResultListener<Reserva> listenerController){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("reservas");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Reserva reserva = dataSnapshot.getValue(Reserva.class);
                listenerController.finish(reserva);
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


    public void grabarReserva(Reserva reserva, final ResultListener<Boolean> listenerController){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("reservas").push();

        String key = reference.getKey();

        reference.setValue(reserva).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                listenerController.finish(true);
            }
        });

    }

}
