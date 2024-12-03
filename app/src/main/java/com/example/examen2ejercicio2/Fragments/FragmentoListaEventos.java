package com.example.examen2ejercicio2.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen2ejercicio2.GestionEventos.AdaptadorEvento;
import com.example.examen2ejercicio2.GestionEventos.Evento;
import com.example.examen2ejercicio2.R;
import com.example.examen2ejercicio2.Utils.PreferencesManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentoListaEventos extends Fragment {

    // Variables
    private RecyclerView recyclerView;
    private AdaptadorEvento adaptadorEvento;
    private List<Evento> listaEventos;
    private PreferencesManager preferencesManager;

    // Method to create the fragment view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_lista_eventos, container, false);

        // Initialize variables
        recyclerView = view.findViewById(R.id.recycler_view_clases);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        preferencesManager = new PreferencesManager(getContext());

        return view;
    }

    // Method that runs when the fragment is resumed
    @Override
    public void onResume() {
        super.onResume();
        cargarEventos();
    }

    // Method to load the events into the RecyclerView
    private void cargarEventos() {
        listaEventos = preferencesManager.cargarEventos();
        if (listaEventos == null) {
            listaEventos = new ArrayList<>();
        } else {
            Collections.sort(listaEventos, (evento1, evento2) -> evento1.getFecha().compareTo(evento2.getFecha()));
        }

        adaptadorEvento = new AdaptadorEvento(getContext(), listaEventos);
        recyclerView.setAdapter(adaptadorEvento);

        adaptadorEvento.setOnItemClickListener(evento -> mostrarDialogoConfirmacion(evento));
    }

    // Method to show a confirmation dialog when deleting an event
    private void mostrarDialogoConfirmacion(Evento evento) {
        new AlertDialog.Builder(getContext())
                .setTitle("Eliminar Evento")
                .setMessage("¿Estás seguro de que deseas eliminar este evento?")
                .setPositiveButton("Aceptar", (dialog, which) -> eliminarEvento(evento))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    // Method to delete an event
    private void eliminarEvento(Evento evento) {
        listaEventos.remove(evento);
        preferencesManager.eliminarEvento(evento);
        adaptadorEvento.notifyDataSetChanged();
    }
}