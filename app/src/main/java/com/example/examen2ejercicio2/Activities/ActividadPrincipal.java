package com.example.examen2ejercicio2.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.examen2ejercicio2.Fragments.FragmentoAgregarEvento;
import com.example.examen2ejercicio2.Fragments.FragmentoListaEventos;
import com.example.examen2ejercicio2.R;

//Actividad principal de la aplicación en la que se cargarán los fragmentos y desde la que se podrá navegar entre ellos
public class ActividadPrincipal extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        //Establecemos el fragmento de la lista de eventos como el principal
        if (savedInstanceState == null) {
            loadFragment(new FragmentoListaEventos());
        }

        //Configuramos el botón para agregar un evento
        findViewById(R.id.btn_agregar_evento).setOnClickListener(v -> loadFragment(new FragmentoAgregarEvento()));
    }

    //Metodo para cargar fragmentos en el contenedor de fragmentos
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
