package com.melvin.vostranca.controller;

import android.content.Context;

import com.melvin.vostranca.dao.DaoFirebaseServicios;
import com.melvin.vostranca.model.Servicio;
import com.melvin.vostranca.util.ResultListener;
import com.melvin.vostranca.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ControllerServicios {

    public void obtenerServicios(Context context, final ResultListener<Servicio> listenerView){

        if (Util.isOnline(context)){

            new DaoFirebaseServicios().obtenerServicios(new ResultListener<Servicio>() {
                @Override
                public void finish(Servicio resultado) {
                    listenerView.finish(resultado);
                }
            });
        }
    }
}
