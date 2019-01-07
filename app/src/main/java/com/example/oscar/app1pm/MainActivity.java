package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oscar.app1pm.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btnModificar;
    FirebaseUser usuarioActual;
    Usuario usuario;
    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recuperarUsuario();
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ModifcarDatos.class);
                i.putExtra("Usuario",usuario);
                startActivityForResult(i, 0);
            }
        });

    }

    private void recuperarUsuario() {

        usuarioActual = getIntent().getParcelableExtra("Usuario");

        bbdd = FirebaseDatabase.getInstance().getReference("usuarios");
        Query q = bbdd.orderByChild("userID").equalTo(usuarioActual.getUid());

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()) {
                    usuario = i.getValue(Usuario.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}