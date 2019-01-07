package com.example.oscar.app1pm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    String nombre;
    String apellidos;
    String nombreUsuario;
    String direccion;
    String id;

    public Usuario(){}

    public Usuario(String nombreUsuario, String nombre, String apellidos, String direccion, String id) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreUsuario = nombreUsuario;
        this.direccion = direccion;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


    protected Usuario(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        nombreUsuario = in.readString();
        direccion = in.readString();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(nombreUsuario);
        dest.writeString(direccion);
        dest.writeString(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}