package com.example.oscar.app1pm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oscar.app1pm.model.Usuario;

import java.util.ArrayList;


public class AdaptadorRecyclerUsuarios extends RecyclerView.Adapter<AdaptadorRecyclerUsuarios.MiViewHolder>{

    ArrayList<Usuario> listadoUsuarios;

    public AdaptadorRecyclerUsuarios (ArrayList<Usuario> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    public static class MiViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNombreUsuario, txtNombre, txtApellidos;


        public MiViewHolder (View view) {
            super(view);
            this.txtNombreUsuario = (TextView) view.findViewById(R.id.txtNombreUsuario);
            this.txtNombre = (TextView) view.findViewById(R.id.txtNombre);
            this.txtApellidos = (TextView) view.findViewById(R.id.txtApellidos);
        }

    }

    @Override
    public AdaptadorRecyclerUsuarios.MiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_recycler, parent, false);
        return new AdaptadorRecyclerUsuarios.MiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorRecyclerUsuarios.MiViewHolder holder, int position) {
        if(!listadoUsuarios.isEmpty()) {
            holder.txtNombreUsuario.setText(listadoUsuarios.get(position).getNombreUsuario());
            holder.txtNombre.setText(listadoUsuarios.get(position).getNombre());
            holder.txtApellidos.setText(listadoUsuarios.get(position).getApellidos());
        }
    }

    @Override
    public int getItemCount() {
        return listadoUsuarios.size();
    }

    public Usuario getPosProducto (int pos) {
        return listadoUsuarios.get(pos);
    }
}