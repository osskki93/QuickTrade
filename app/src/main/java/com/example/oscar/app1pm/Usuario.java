package com.example.oscar.app1pm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Parcel;


public class Usuario implements Parcelable {

    int id;
    String nom;
    String cognoms;
    String email;
    String password;
    String telefon;

    public Usuario (){

    }
    

    public Usuario(int id, String nom, String cognoms, String email, String password, String telefon) {

        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.password = password;
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        cognoms = in.readString();
        email = in.readString();
        password = in.readString();
        telefon = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(cognoms);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(telefon);
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