package com.melvin.vostranca.view;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.melvin.vostranca.R;
import com.melvin.vostranca.model.Servicio;
import com.melvin.vostranca.util.GlideApp;

import java.util.Calendar;
import java.util.List;

public class AdapterServicios extends RecyclerView.Adapter {

    private List<Servicio> datos;
    private ServiciosInterface listener;

    public AdapterServicios(List<Servicio> datos, ServiciosInterface listener) {
        this.datos = datos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_item_servicios, viewGroup, false);
        ServiciosViewHolder holder = new ServiciosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ServiciosViewHolder) viewHolder).cargar(datos.get(i));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    private class ServiciosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView nombre;
        private TextView descripcion;

        public ServiciosViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.imagen = itemView.findViewById(R.id.imagenServicio);
            this.nombre = itemView.findViewById(R.id.nombreServicio);
            this.descripcion = itemView.findViewById(R.id.descripcionServicio);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Servicio servicio = datos.get(getAdapterPosition());
                    listener.servicioElegido(servicio.getNombre(), servicio.getDescripcion());
                }
            });
        }

        public void cargar(Servicio servicio){
            String nombreImagen = "servicios/"+servicio.getNombre().toLowerCase()+".png";
            StorageReference reference = FirebaseStorage.getInstance().getReference().child(nombreImagen);
            GlideApp.with(itemView.getContext()).load(reference).into(imagen);
            nombre.setText(servicio.getNombre());
            descripcion.setText(servicio.getDescripcion());
        }
    }

    public void setDatos(List<Servicio> datos) {
        this.datos = datos;
        notifyDataSetChanged();
    }

    public interface ServiciosInterface{
        void servicioElegido(String nombre, String descripcion);
    }
}
