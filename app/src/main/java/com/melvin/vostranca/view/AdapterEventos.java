package com.melvin.vostranca.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.melvin.vostranca.R;
import com.melvin.vostranca.model.Evento;
import com.melvin.vostranca.util.GlideApp;

import java.util.List;

public class AdapterEventos extends RecyclerView.Adapter {

    private List<Evento> datos;
    private EventosInterface listener;

    public AdapterEventos(List<Evento> datos, EventosInterface listener) {
        this.datos = datos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_item_evento, viewGroup, false);
        EventosViewHolder viewHolder = new EventosViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((EventosViewHolder) viewHolder).cargar(datos.get(i));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    private class EventosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView nombre;
        private TextView fecha;

        public EventosViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagenEvento);
            nombre = itemView.findViewById(R.id.nombreEvento);
            fecha = itemView.findViewById(R.id.fechaEvento);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(DetalleFragment.KEY_NOMBRE, datos.get(getAdapterPosition()).getNombre());
                    bundle.putString(DetalleFragment.KEY_DESCRIPCION, datos.get(getAdapterPosition()).getDescripcion());
                    bundle.putString(DetalleFragment.KEY_FECHA, datos.get(getAdapterPosition()).getFecha());
                    listener.eventoSeleccionado(bundle);
                }
            });
        }

        public void cargar(Evento evento){
            String nombreImagen = "eventos/"+evento.getNombre().toLowerCase()+".png";
            StorageReference reference = FirebaseStorage.getInstance().getReference().child(nombreImagen);
            nombre.setText(evento.getNombre());
            fecha.setText(evento.getFecha());
            GlideApp.with(itemView.getContext()).load(reference).into(imagen);
        }
    }

    public interface EventosInterface{
        void eventoSeleccionado(Bundle datos);
    }

    public void setDatos(List<Evento> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }
}
