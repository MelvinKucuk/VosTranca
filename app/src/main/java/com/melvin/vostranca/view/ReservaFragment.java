package com.melvin.vostranca.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.melvin.vostranca.R;
import com.melvin.vostranca.controller.ControllerReservas;
import com.melvin.vostranca.util.GlideApp;
import com.melvin.vostranca.util.ResultListener;

public class ReservaFragment extends Fragment {

    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_DESCRIPCION = "descripcion";

    private OnFragmentInteractionListener mListener;
    private TextView textoFecha;

    public ReservaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_reserva, container, false);

        Button botonFechas = view.findViewById(R.id.botonFechas);
        botonFechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.irFechasFragment();
            }
        });
        textoFecha = view.findViewById(R.id.textoFechaSeleccionada);

        final EditText nombre = view.findViewById(R.id.editTextNombre);
        final EditText direccion = view.findViewById(R.id.editTextDireccion);
        final EditText personas = view.findViewById(R.id.editTextPersonas);
        final EditText telefono = view.findViewById(R.id.editTextTelefono);
        final TextInputLayout inputNombre = view.findViewById(R.id.inputLayoutNombre);
        final TextInputLayout inputDireccion = view.findViewById(R.id.inputLayoutDireccion);
        final TextInputLayout inputPersonas = view.findViewById(R.id.inputLayoutPersonas);
        final TextInputLayout inputTelefono = view.findViewById(R.id.inputLayoutTelefono);
        LinearLayout servicio = view.findViewById(R.id.servicioSeleccionado);
        TextView textNombre = servicio.findViewById(R.id.nombreServicio);
        TextView textDescripcion = servicio.findViewById(R.id.descripcionServicio);
        ImageView imagenServicio = servicio.findViewById(R.id.imagenServicio);

        Bundle datos = getArguments();

        final String nombreServicio = datos.getString(KEY_NOMBRE);
        String descripcion = datos.getString(KEY_DESCRIPCION);


        // Cargar View del servicio
        textNombre.setText(nombreServicio);
        textDescripcion.setText(descripcion);
        String nombreImagen = "servicios/"+nombreServicio.toLowerCase()+".png";
        StorageReference reference = FirebaseStorage.getInstance().getReference().child(nombreImagen);
        GlideApp.with(getContext()).load(reference).into(imagenServicio);


        // Funcionamiento botones
        Button botonCancelar = view.findViewById(R.id.botonCancelar);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        Button botonConfirmar = view.findViewById(R.id.botonConfirmar);
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nombre.getText().toString().isEmpty()){
                    inputNombre.setError(null);
                    inputNombre.setErrorEnabled(false);
                    if (!telefono.getText().toString().isEmpty()){
                        inputTelefono.setError(null);
                        inputTelefono.setErrorEnabled(false);
                        if (!personas.getText().toString().isEmpty()){
                            inputPersonas.setError(null);
                            inputPersonas.setErrorEnabled(false);
                            if (!direccion.getText().toString().isEmpty()){
                                inputDireccion.setError(null);
                                inputDireccion.setErrorEnabled(false);
                                inputDireccion.clearFocus();
                                if (!textoFecha.getText().toString().isEmpty()){

                                    String nombreTexto = nombre.getText().toString();
                                    String direccionTexto = direccion.getText().toString();
                                    Integer personasTexto = Integer.parseInt(personas.getText().toString());
                                    Integer telefonoTexto = Integer.parseInt(telefono.getText().toString());
                                    String fecha = textoFecha.getText().toString();

                                    new ControllerReservas().grabarReserva(getContext(), nombreTexto, nombreServicio, telefonoTexto,
                                            personasTexto, direccionTexto, fecha, new ResultListener<Boolean>() {
                                                @Override
                                                public void finish(Boolean resultado) {
                                                    if (resultado){
                                                        nombre.setText(null);
                                                        direccion.setText(null);
                                                        telefono.setText(null);
                                                        personas.setText(null);

                                                        Toast.makeText(getContext(), "Reserva Exitosa", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });


                                } else {
                                    Toast.makeText(getContext(), "Debes seleccionar una fecha", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                generarError(direccion, inputDireccion);
                            }
                        } else {
                            generarError(personas, inputPersonas);
                        }
                    } else{
                        generarError(telefono, inputTelefono);
                    }
                } else{
                    generarError(nombre, inputNombre);
                }
            }
        });





        return view;
    }

    private void generarError(EditText editText, TextInputLayout textInputLayout){
        textInputLayout.requestFocus();
        mostrarTeclado(editText);
        textInputLayout.setError(getResources().getText(R.string.campoVacio));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void irFechasFragment();
    }

    @Override
    public void onResume() {
        super.onResume();

        String fechaSeleccionada = ((MainActivity)getActivity()).getFecha();
        textoFecha.setText(fechaSeleccionada);
    }


    public void mostrarTeclado(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
