package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Activity_Registro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registro);

        final Button btnCancelar = (Button) findViewById(R.id.botonCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cancelar = new Intent(v.getContext(), Activity_Login.class);
                startActivityForResult(cancelar, 1);
            }
        });

        final Button btnAcceptar = (Button) findViewById(R.id.botonAcceptar);
        btnAcceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Usuario usuario1 = guardarUsuario();
                if (usuario1 != null) {
                    Intent i = new Intent();
                    i.putExtra;
                    setResult(RESULT_OK);
                }
                else{
                    setResult(RESULT_CANCELED);
                }
            }
        });

    }

    private boolean comprobarCampos() {

        EditText inputUserID = (EditText) findViewById(R.id.inputUserID);
        EditText inputNom = (EditText) findViewById(R.id.inputNom);
        EditText inputCognoms = (EditText) findViewById(R.id.inputCognoms);
        EditText inputEmail = (EditText) findViewById(R.id.inputEmail);
        EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
        EditText inputTelefon = (EditText) findViewById(R.id.inputTelefon);

        boolean relleno = false;

        if (inputUserID.getText().toString() != "") {
            if(inputNom.getText().toString() != ""){
                if (inputCognoms.getText().toString() != ""){
                    if(inputEmail.getText().toString() != ""){
                        if(inputPassword.getText().toString() != ""){
                            if(inputTelefon.getText().toString() != ""){
                                relleno = true;
                            }
                        }
                    }
                }
            }
        }
        return relleno;


    }

    private Usuario guardarUsuario() {

        if (comprobarCampos() == true) {
            EditText inputUserID = (EditText) findViewById(R.id.inputUserID);
            EditText inputNom = (EditText) findViewById(R.id.inputNom);
            EditText inputCognoms = (EditText) findViewById(R.id.inputCognoms);
            EditText inputEmail = (EditText) findViewById(R.id.inputEmail);
            EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
            EditText inputTelefon = (EditText) findViewById(R.id.inputTelefon);
            Usuario u1 = new Usuario(Integer.parseInt(inputUserID.getText().toString()), inputNom.getText().toString(), inputCognoms.getText().toString(), inputEmail.getText().toString(), inputPassword.getText().toString(), inputTelefon.getText().toString());
            return u1;
        }

        return null;
    }
}
