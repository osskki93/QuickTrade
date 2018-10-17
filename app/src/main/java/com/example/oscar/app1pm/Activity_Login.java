package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;

public class Activity_Login extends AppCompatActivity {

    private ArrayList<Usuario> Usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        Usuarios = new ArrayList<Usuario>();

        Usuario u1 = new Usuario(1, "Manel", "Viel", "mviel@florida-uni.es", "1234", "654321098");
        Usuarios.add(u1);

        final Button btnRegistro = (Button) findViewById(R.id.botonRegistrar);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent registro = new Intent(v.getContext(),Activity_Registro.class);
                startActivityForResult(registro,0);
            }
        });

        final Button btnEnviar = (Button) findViewById(R.id.botonEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText inputLogin = (EditText) findViewById(R.id.inputLogin);
                EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
                boolean res;
                res = comprobarUsuario(inputLogin.getText().toString(), inputPassword.getText().toString());
                if (res == true) {
                    Intent envio = new Intent(v.getContext(), MainActivity.class);
                    startActivity(envio);
                }
            }
        });

    }

    private boolean comprobarUsuario (String email, String password) {
        boolean correcto = false;
        Iterator<Usuario> i = Usuarios.iterator();
        while (i.hasNext()){
            Usuario u1 = (Usuario) i.next();
            if (u1.getEmail().compareTo(email) == 0  && u1.getPassword().compareTo(password) == 0){
                correcto = true;
            }
            else{
                correcto = false;
            }
        }

        return correcto;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        switch (requestCode) {

            case 0:
                if (resultCode == RESULT_OK) {
                    Usuario u1 = i.getExtras().getParcelable("Usuario");
                    Usuarios.add(u1);
                } else {
                    Log.d("Testa", "Fallo en la vuelta del Activity");
                }
        }
    }



}
