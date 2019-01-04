package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oscar.app1pm.model.Usuario;

import java.util.ArrayList;

public class Activity_Login extends AppCompatActivity {

    private ArrayList<Usuario> Usuarios;
    EditText inputLogin;
    EditText inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        Usuarios = new ArrayList<Usuario>();

        final Button btnRegistro = (Button) findViewById(R.id.botonRegistrar);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Registro.class);
                startActivity(i);

            }
        });

        final Button btnEnviar = (Button) findViewById(R.id.botonEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText inputLogin = (EditText) findViewById(R.id.inputLogin);
                EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
                boolean res;
                res = comprobarUsuario();
                if (res == true) {
                    Intent envio = new Intent(v.getContext(), MainActivity.class);
                    startActivityForResult(envio, 1);
                }
            }
        });

    }


    private boolean comprobarUsuario () {
       return true;
    }




}