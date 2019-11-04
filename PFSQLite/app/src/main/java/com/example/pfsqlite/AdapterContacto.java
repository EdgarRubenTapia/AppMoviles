package com.example.pfsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfsqlite.entidades.Contactos;

import java.util.ArrayList;


public class AdapterContacto extends RecyclerView.Adapter<AdapterContacto.ViewHolderContacto> implements View.OnClickListener{

    ArrayList<Contactos> listContacto;
    private View.OnClickListener listener;

    public AdapterContacto(ArrayList<Contactos> listContacto) {
        this.listContacto = listContacto;
    }

    @NonNull
    @Override
    public ViewHolderContacto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto,null,false);
        view.setOnClickListener(this);
        return new ViewHolderContacto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContacto holder, int position) {
        holder.asignarDatos(listContacto.get(position));
    }

    @Override
    public int getItemCount() {
        return listContacto.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderContacto extends RecyclerView.ViewHolder {

        TextView contacto;

        public ViewHolderContacto(@NonNull View itemView) {
            super(itemView);
            contacto = itemView.findViewById(R.id.ItemContacto);
        }

        public void asignarDatos(Contactos contactos) {
            contacto.setText(contactos.getUsuario());
        }
    }


}
