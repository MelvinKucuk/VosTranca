package com.melvin.vostranca.controller;

import android.content.Context;

import com.melvin.vostranca.dao.DaoFirebaseEventos;
import com.melvin.vostranca.model.Servicio;
import com.melvin.vostranca.util.ResultListener;
import com.melvin.vostranca.util.Util;

public class ControllerEventos {

    public void obtenerServicios(Context context, final ResultListener<Servicio> listenerView){

        if (Util.isOnline(context)){

            new DaoFirebaseEventos().obtenerServicios(new ResultListener<Servicio>() {
                @Override
                public void finish(Servicio resultado) {
                    listenerView.finish(resultado);
                }
            });
        }
    }
}
