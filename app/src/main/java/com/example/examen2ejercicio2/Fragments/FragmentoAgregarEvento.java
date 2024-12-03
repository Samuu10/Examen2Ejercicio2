package com.example.examen2ejercicio2.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.examen2ejercicio2.GestionEventos.Evento;
import com.example.examen2ejercicio2.R;
import com.example.examen2ejercicio2.Utils.PreferencesManager;

public class FragmentoAgregarEvento extends Fragment {

    // Variables
    private EditText nombre;
    private EditText descripcion;
    private EditText direccion;
    private EditText precio;
    private EditText fecha;
    private EditText aforo;
    private PreferencesManager preferencesManager;

    // Method to create the fragment view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_agregar_evento, container, false);

        // Initialize variables
        nombre = view.findViewById(R.id.nombre_evento);
        descripcion = view.findViewById(R.id.descripcion_evento);
        direccion = view.findViewById(R.id.direccion_evento);
        precio = view.findViewById(R.id.precio_evento);
        fecha = view.findViewById(R.id.fecha_evento);
        aforo = view.findViewById(R.id.aforo_evento);
        preferencesManager = new PreferencesManager(getContext());

        // Configure buttons
        Button btnAgregar = view.findViewById(R.id.btn_agregar);
        Button btnCancelar = view.findViewById(R.id.btn_cancelar);
        btnAgregar.setOnClickListener(v -> agregarEvento());
        btnCancelar.setOnClickListener(v -> cancelar());

        return view;
    }

    // Method to add a new event
    private void agregarEvento() {
        String nombreEvento = nombre.getText().toString().trim();
        String descripcionEvento = descripcion.getText().toString().trim();
        String direccionEvento = direccion.getText().toString().trim();
        String precioEvento = precio.getText().toString().trim();
        String fechaEvento = fecha.getText().toString().trim();
        String aforoEvento = aforo.getText().toString().trim();

        // If any required field is empty, show an error message
        if (nombreEvento.isEmpty() || descripcionEvento.isEmpty() || precioEvento.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        Evento nuevoEvento = new Evento(nombreEvento, descripcionEvento, direccionEvento, precioEvento, fechaEvento, aforoEvento);
        new SaveEventoTask().execute(nuevoEvento);
    }

    // AsyncTask class to save the new event in shared preferences in the background
    private class SaveEventoTask extends AsyncTask<Evento, Void, Void> {
        @Override
        protected Void doInBackground(Evento... eventos) {
            preferencesManager.guardarEvento(eventos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getContext(), "Evento agregado", Toast.LENGTH_SHORT).show();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentoListaEventos())
                    .commit();
        }
    }

    // Method to cancel the action and return to the initial fragment
    private void cancelar() {
        getParentFragmentManager().popBackStack();
    }
}