package com.melvin.vostranca.controller;

import android.content.Context;

import com.melvin.vostranca.dao.DaoFirebaseFechas;
import com.melvin.vostranca.model.Fecha;
import com.melvin.vostranca.util.ResultListener;
import com.melvin.vostranca.util.Util;


public class ControllerFechas {

    public void obtenerFechas(Context context, final ResultListener<Fecha> listenerView){

        if (Util.isOnline(context)){
            new DaoFirebaseFechas().obtenerFechas(new ResultListener<Fecha>() {
                @Override
                public void finish(Fecha resultado) {
                    listenerView.finish(resultado);
                }
            });
        }
    }
}
