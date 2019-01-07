package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oscar.app1pm.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModifcarDatos extends AppCompatActivity{


    private EditText inputNombreUsuario;
    private EditText inputNombre;
    private EditText inputApellidos;
    private EditText inputDireccion;
    private Button botonCancelar;
    private Button botonAcceptar;

    private Usuario usuario;

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifcar_datos);

        bbdd = FirebaseDatabase.getInstance().getReference("usuarios");
        usuario = getIntent().getParcelableExtra("Usuario");
        if(usuario == null) {
            Log.d("Oscar", "El usuario está vacío");
        } else {
            Log.d("Oscar", "El usuario es: " + usuario.getNombreUsuario());
        }

        inputNombreUsuario = findViewById(R.id.inputNombreUsuario);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellidos = findViewById(R.id.inputApellidos);
        inputDireccion = findViewById(R.id.inputDireccion);
        botonCancelar = findViewById(R.id.botonCancelar);
        botonAcceptar = findViewById(R.id.botonAcceptar);


        inputNombreUsuario.setText(usuario.getNombreUsuario());
        inputNombre.setText(usuario.getNombre());
        inputApellidos.setText(usuario.getApellidos());
        inputDireccion.setText(usuario.getDireccion());

        botonAcceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query q = bbdd.orderByChild("id").equalTo(usuario.getId());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        bbdd.child(usuario.getId()).setValue(usuario);
                        Intent i = new Intent();
                        i.putExtra("Usuario", usuario);
                        setResult(RESULT_OK, i);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                });
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }



    private void actualizar() {
        usuario.setNombre(inputNombre.getText().toString());
        usuario.setApellidos(inputApellidos.getText().toString());
        usuario.setDireccion(inputDireccion.getText().toString());
    }
}