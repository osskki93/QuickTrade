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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Activity_Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText inputNombreUsuario;
    private EditText inputNombre;
    private EditText inputApellidos;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputDireccion;

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registro);

        inputNombreUsuario = (EditText) findViewById(R.id.inputNombreUsuario);
        inputNombre = (EditText) findViewById(R.id.inputNombre);
        inputApellidos = (EditText) findViewById(R.id.inputApellidos);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputDireccion = (EditText) findViewById(R.id.inputDireccion);


        bbdd = FirebaseDatabase.getInstance().getReference("usuarios");

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

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                comprobar(inputNombreUsuario.getText().toString());
                setResult(RESULT_OK);
            }
        });

    }

    private boolean comprobarCampos() {

        boolean relleno = false;

        if (inputNombreUsuario.getText().toString() != "") {
            if(inputNombre.getText().toString() != ""){
                if (inputApellidos.getText().toString() != ""){
                    if(inputEmail.getText().toString() != ""){
                        if(inputPassword.getText().toString() != ""){
                            if(inputDireccion.getText().toString() != ""){
                                relleno = true;
                            }
                        }
                    }
                }
            }
        }
        return relleno;


    }

    private Usuario guardarUsuario(FirebaseUser user) {

        if (comprobarCampos() == true) {
            EditText inputNombreUsuario = (EditText) findViewById(R.id.inputNombreUsuario);
            EditText inputNombre = (EditText) findViewById(R.id.inputNombre);
            EditText inputApellidos = (EditText) findViewById(R.id.inputApellidos);
            EditText inputEmail = (EditText) findViewById(R.id.inputEmail);
            EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
            EditText inputDireccion = (EditText) findViewById(R.id.inputDireccion);
            Usuario u1 = new Usuario(inputNombreUsuario.getText().toString(), inputNombre.getText().toString(), inputApellidos.getText().toString(), inputDireccion.getText().toString(), user.getUid());
            bbdd.child(user.getUid()).setValue(u1);
            return u1;
        }

        return null;
    }

    private void registro(String email, String password){

        mAuth = FirebaseAuth.getInstance();

        final String nombreUsuario = inputNombreUsuario.getText().toString();
        final String nombre = inputNombre.getText().toString();
        final String apellidos = inputApellidos.getText().toString();
        final String direccion = inputDireccion.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String clave = bbdd.push().getKey();

                            Usuario u1 = new Usuario(nombreUsuario, nombre, apellidos, direccion, clave);

                            bbdd.child(clave).setValue(u1);

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Activity_Registro.this, "Autenticación con éxito", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Activity_Registro.this, "La autenticación no ha podido realizarse: " + task.getException(), Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    boolean res;
    private void comprobar(final String inputNombreUsuario) {

        res = false;
        Query q = bbdd.orderByChild("nombreUsuario");

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()) {
                    Usuario u = i.getValue(Usuario.class);
                    Log.d("Hola","Comprobando " + u.getNombreUsuario() + " con " + inputNombreUsuario + " y da: " + u.getNombreUsuario().compareToIgnoreCase(inputNombreUsuario));
                    if (u.getNombreUsuario().compareToIgnoreCase(inputNombreUsuario) == 0) {
                        res = true;
                    }
                }
                if(comprobarCampos() && !res) {
                    registro(inputEmail.getText().toString(),inputPassword.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Revise que todos los campos estén rellenados, y si es así cambie el nombre de usuario dado que ese ya existe", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}