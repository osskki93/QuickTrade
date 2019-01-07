package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oscar.app1pm.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Activity_Login extends AppCompatActivity {

    private ArrayList<Usuario> Usuarios;
    EditText inputLogin;
    EditText inputPassword;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        mAuth.signOut();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        mAuth = FirebaseAuth.getInstance();

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
                comprobarUsuario();

            }
        });

    }

    private void comprobarUsuario() {

        EditText inputLogin = (EditText) findViewById(R.id.inputLogin);
        EditText inputPassword = (EditText) findViewById(R.id.inputPassword);

        // Comprobamos que ninguno de los campos sea una cadena vacia, en caso de serlo devolvemos false;
        if((inputLogin.getText().toString().isEmpty()) || (inputPassword.getText().toString().isEmpty())) {
            Log.d("Hola","Hola");
            // Alguna cadena vacía
            Toast.makeText(getApplicationContext(), "ERROR: Revisa los campos.", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(inputLogin.getText().toString(), inputPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("Usuario", mAuth.getCurrentUser());
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "ERROR: Email o contraseña incorrectas.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }}





}