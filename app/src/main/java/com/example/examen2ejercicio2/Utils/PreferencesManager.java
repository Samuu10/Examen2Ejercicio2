package com.example.examen2ejercicio2.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.examen2ejercicio2.GestionEventos.Evento;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//Clase PreferencesManager que se utiliza para gestionar las preferencias de la aplicación y guardar los eventos en SharedPreferences
public class PreferencesManager {

    //Variables
    private static final String PREF_NAME = "event_preferences";
    private static final String KEY_EVENTOS = "eventos";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    //Constructor
    public PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    //Metodo para guardar un evento en SharedPreferences
    public void guardarEvento(Evento evento) {
        List<Evento> eventos = cargarEventos();
        eventos.add(evento);
        guardarEventos(eventos);
    }

    //Metodo para cargar la lista de eventos desde SharedPreferences
    public List<Evento> cargarEventos() {
        String json = sharedPreferences.getString(KEY_EVENTOS, null);
        Type type = new TypeToken<ArrayList<Evento>>() {}.getType();
        return json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }

    //Metodo para guardar la lista de eventos en SharedPreferences
    private void guardarEventos(List<Evento> eventos) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(eventos);
        editor.putString(KEY_EVENTOS, json);
        editor.apply();
    }

    //Metodo para eliminar un evento de sharedPreferences
    public void eliminarEvento(Evento evento) {
        List<Evento> eventos = cargarEventos();
        eventos.removeIf(e -> e.getNombre().equals(evento.getNombre()) &&
                e.getDescripcion().equals(evento.getDescripcion()) &&
                e.getDireccion().equals(evento.getDireccion()) &&
                e.getPrecio().equals(evento.getPrecio()) &&
                e.getFecha().equals(evento.getFecha()) &&
                e.getAforo().equals(evento.getAforo()));
        guardarEventos(eventos);
    }
}