package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.oscar.app1pm.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListaUsuarios extends AppCompatActivity implements View.OnClickListener{

    final static int DetalleUsuarioActivity = 0;

    private DatabaseReference database;
    private FirebaseUser currentUser;
    private ImageView btnFavoritos;

    private RecyclerView recyclerUsuarios;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorRecyclerUsuarios adaptadorRecyclerUsuarios;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        recyclerUsuarios = findViewById(R.id.recyclerUsuarios);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerUsuarios.setLayoutManager(linearLayoutManager);
        btnFavoritos = findViewById(R.id.btnFavoritos);
        btnFavoritos.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        listarUsuarios();
    }

    private void listarUsuarios() {
        final ArrayList<Usuario> listadoUsuarios = new ArrayList<Usuario>();
        DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference("usuarios");
        bbdd.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()) {
                    Usuario u = i.getValue(Usuario.class);
                    listadoUsuarios.add(u);
                }
                adaptadorRecyclerUsuarios = new AdaptadorRecyclerUsuarios(listadoUsuarios);
                recyclerUsuarios.setAdapter(adaptadorRecyclerUsuarios);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerUsuarios.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerUsuarios, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Usuario u = adaptadorRecyclerUsuarios.getPosProducto(position);
                Intent i = new Intent(getApplicationContext(), DetalleUsuarioActivity.class);
                i.putExtra("usuarioActual", currentUser);
                i.putExtra("usuarioLeido", u);
                startActivityForResult(i, DetalleUsuarioActivity);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onClick(View view) {

    }
}