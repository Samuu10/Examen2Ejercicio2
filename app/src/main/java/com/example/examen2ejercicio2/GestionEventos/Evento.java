package com.example.examen2ejercicio2.GestionEventos;

import android.os.Parcel;
import android.os.Parcelable;

//Clase que representa un objeto Evento y que implementa Parcelable para pasar objetos entre actividades y fragmentos
public class Evento implements Parcelable {

    //Atributos
    private String nombre;
    private String descripcion;
    private String direccion;
    private String precio;
    private String fecha;
    private String aforo;

    //Constructor sin parámetros
    public Evento() {}

    //Constructor con parámetros
    public Evento(String nombre, String descripcion, String direccion, String precio, String fecha, String aforo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.precio = precio;
        this.fecha = fecha;
        this.aforo = aforo;
    }

    //Constructor Parcelable
    protected Evento(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        direccion = in.readString();
        precio = in.readString();
        fecha = in.readString();
        aforo = in.readString();
    }

    //Metodo CREATOR implementado por Parcelable
    public static final Parcelable.Creator<Evento> CREATOR = new Parcelable.Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getAforo() {
        return aforo;
    }
    public void setAforo(String aforo) {
        this.aforo = aforo;
    }

    //Metodo describeContents implementado por Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    //Metodo writeToParcel implementado por Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(direccion);
        dest.writeString(precio);
        dest.writeString(fecha);
        dest.writeString(aforo);
    }
}