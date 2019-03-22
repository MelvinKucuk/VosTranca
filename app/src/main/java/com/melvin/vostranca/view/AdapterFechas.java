package com.melvin.vostranca.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melvin.vostranca.R;
import com.melvin.vostranca.model.Fecha;

import java.util.Calendar;
import java.util.List;

public class AdapterFechas extends RecyclerView.Adapter {

    private List<Fecha> datos;
    private FechasInterface listener;

    public AdapterFechas(List<Fecha> datos, FechasInterface listener) {
        this.datos = datos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_fecha, viewGroup, false);
        FechasViewHolder holder = new FechasViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((FechasViewHolder) viewHolder).cargar(datos.get(i));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    private class FechasViewHolder extends RecyclerView.ViewHolder{

        private TextView numero;
        private TextView mes;

        public FechasViewHolder(@NonNull View itemView) {
            super(itemView);
            this.numero = itemView.findViewById(R.id.textoNumero);
            this.mes = itemView.findViewById(R.id.textoMes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fecha fecha = datos.get(getAdapterPosition());
                    String fechaTexto = formatFecha(fecha);
                    listener.fechaSeleccionada(fechaTexto);
                }
            });
        }

        public void cargar(Fecha fecha){
            numero.setText(fecha.getNumero().toString());
            mes.setText(fecha.getMes());
        }
    }

    public interface FechasInterface{
        void fechaSeleccionada(String fecha);
    }

    private String formatFecha(Fecha fecha){
        String resultado = fecha.getNumero().toString();
        Integer year = Calendar.getInstance().get(Calendar.YEAR);

        switch (fecha.getMes()){
            case "ene":
                resultado = resultado+"/01/"+year;
                break;
            case "feb":
                resultado = resultado+"/02/"+year;
                break;
            case "mar":
                resultado = resultado+"/03/"+year;
                break;
            case "abr":
                resultado = resultado+"/04/"+year;
                break;
            case "may":
                resultado = resultado+"/05/"+year;
                break;
            case "jun":
                resultado = resultado+"/06/"+year;
                break;
            case "jul":
                resultado = resultado+"/07/"+year;
                break;
            case "ago":
                resultado = resultado+"/08/"+year;
                break;
            case "sep":
                resultado = resultado+"/09/"+year;
                break;
            case "oct":
                resultado = resultado+"/10/"+year;
                break;
            case "nov":
                resultado = resultado+"/11/"+year;
                break;
            case "dic":
                resultado = resultado+"/12/"+year;
                break;
        }

        return resultado;

    }

    public void setDatos(List<Fecha> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }
}
