package com.example.examen2ejercicio2.GestionEventos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen2ejercicio2.R;
import java.util.List;

//Clase AdaptadorEvento que extiende RecyclerView.Adapter y se utiliza para mostrar la lista de eventos
public class AdaptadorEvento extends RecyclerView.Adapter<AdaptadorEvento.EventoViewHolder> {

    //Variables
    private Context context;
    private List<Evento> eventos;
    private OnItemClickListener onItemClickListener;

    //Constructor
    public AdaptadorEvento(Context context, List<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    //Interfaz OnItemClickListener que se utiliza para gestionar los eventos de click en los elementos de la lista
    public interface OnItemClickListener {
        void onItemClick(Evento evento);
    }

    //Metodo para establecer el listener de los eventos de click
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    //Metodo para crear una nueva vista
    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(view);
    }

    //Metodo para enlazar los datos de la lista con los elementos de la vista
    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.nombreTextView.setText(evento.getNombre());
        holder.descripcionTextView.setText(evento.getDescripcion());
        holder.precioTextView.setText(evento.getPrecio() + "€");

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(evento);
            }
        });
    }

    //Metodo para obtener el número de elementos en la lista
    @Override
    public int getItemCount() {
        return eventos.size();
    }

    //Clase EventoViewHolder que extiende RecyclerView.ViewHolder y se utiliza para mantener las referencias de los elementos de la vista
    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView descripcionTextView;
        TextView precioTextView;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombre_evento);
            descripcionTextView = itemView.findViewById(R.id.descripcion_evento);
            precioTextView = itemView.findViewById(R.id.precio_evento);
        }
    }
}