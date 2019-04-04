package com.melvin.vostranca.controller;

import android.content.Context;

import com.melvin.vostranca.dao.DaoFirebaseReservas;
import com.melvin.vostranca.model.Reserva;
import com.melvin.vostranca.util.ResultListener;
import com.melvin.vostranca.util.Util;

public class ControllerReservas {

    public void obtenerReservas(Context context, final ResultListener<Reserva> listenerView){

        if (Util.isOnline(context)){
            new DaoFirebaseReservas().obtenerReservas(new ResultListener<Reserva>() {
                @Override
                public void finish(Reserva resultado) {
                    listenerView.finish(resultado);
                }
            });
        }
    }

    public void grabarReserva(Context context, String nombre, String servicio,
                              Integer telefono, Integer cantidadPersonas,
                              String direccion, String fecha, final ResultListener<Boolean> listenerView){

        if (Util.isOnline(context)){
            Reserva reserva = new Reserva(nombre, servicio, telefono, cantidadPersonas, direccion, fecha);
            new DaoFirebaseReservas().grabarReserva(reserva, new ResultListener<Boolean>() {
                @Override
                public void finish(Boolean resultado) {
                    listenerView.finish(resultado);
                }
            });
        }
    }
}
