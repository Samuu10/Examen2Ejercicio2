package com.example.examen2ejercicio2.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.examen2ejercicio2.GestionEventos.Evento;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Class to manage shared preferences for storing events
public class PreferencesManager {

    // Constants
    private static final String PREF_NAME = "event_preferences";
    private static final String KEY_EVENTOS = "eventos";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    // Constructor
    public PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Method to save an event in shared preferences
    public void guardarEvento(Evento evento) {
        List<Evento> eventos = cargarEventos();
        eventos.add(evento);
        guardarEventos(eventos);
    }

    // Method to load events from shared preferences
    public List<Evento> cargarEventos() {
        String json = sharedPreferences.getString(KEY_EVENTOS, null);
        Type type = new TypeToken<ArrayList<Evento>>() {}.getType();
        return json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }

    // Method to save the list of events in shared preferences
    private void guardarEventos(List<Evento> eventos) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(eventos);
        editor.putString(KEY_EVENTOS, json);
        editor.apply();
    }

    // Method to delete an event from shared preferences
    public void eliminarEvento(Evento evento) {
        List<Evento> eventos = cargarEventos();
        eventos.remove(evento);
        guardarEventos(eventos);
    }
}