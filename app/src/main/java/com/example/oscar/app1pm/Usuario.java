package com.example.oscar.app1pm;

import android.content.Intent;
import android.os.Bundle;

public class Usuario {

    int id;
    String nom;
    String cognoms;
    String email;
    String password;
    String telefon;

    public Usuario (){

    }

    Intent i = new Intent();
    Bundle b = new Bundle();




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
}