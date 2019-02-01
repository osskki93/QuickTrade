package com.example.oscar.app1pm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscar.app1pm.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class DetalleUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lblNombreUsuario;
    private TextView lblNombre;
    private TextView lblApellidos;
    private FirebaseUser currentUser;
    private Usuario usuarioLeido;
    private CheckBox cbFavorito;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        lblNombreUsuario = findViewById(R.id.lblNombreUsuario);
        lblNombre = findViewById(R.id.lblNombre);
        lblApellidos= findViewById(R.id.lblApellidos);
        cbFavorito = findViewById(R.id.cbFavorito);
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);

        currentUser = getIntent().getParcelableExtra("usuarioActual");
        usuarioLeido = getIntent().getParcelableExtra("usuarioLeido");

        comprobarFavorito();
        cargarDatos();


    }

    private void cargarDatos() {
        lblNombreUsuario.setText(usuarioLeido.getNombreUsuario());
        lblNombre.setText(usuarioLeido.getNombre());
        lblApellidos.setText(usuarioLeido.getApellidos());
    }

    private void comprobarFavorito() {
        DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference("usuarios").child(currentUser.getUid()).child("usuariosFavoritos");
        Query q = bbdd.orderByChild("idUsuario").equalTo(usuarioLeido.getUserID());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i: dataSnapshot.getChildren()) {
                    cbFavorito.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void estadoFavorito() {
        DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference("usuarios").child(currentUser.getUid());
        if(cbFavorito.isChecked()) {
            bbdd.child("usuariosFavoritos").child(usuarioLeido.getUserID()).child("idUsuario").setValue(usuarioLeido.getUserID());
        } else {
            bbdd.child("usuariosFavoritos").child(usuarioLeido.getUserID()).removeValue();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSalir:
                estadoFavorito();
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}