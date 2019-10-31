package com.example.pfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfinal.entidades.*;

import java.util.ArrayList;

public class AdapterNotas extends RecyclerView.Adapter<AdapterNotas.ViewHolderNotas> {

    ArrayList<notas> listNotas;

    public AdapterNotas(ArrayList<notas> listNotas) {
        this.listNotas = listNotas;
    }

    @NonNull
    @Override
    public ViewHolderNotas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota,null,false);
        return new ViewHolderNotas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNotas holder, int position) {
        holder.asignarDatos(listNotas.get(position));
    }

    @Override
    public int getItemCount() {
        return listNotas.size();
    }

    public class ViewHolderNotas extends RecyclerView.ViewHolder {

        TextView nota;

        public ViewHolderNotas(@NonNull View itemView) {
            super(itemView);
            nota = itemView.findViewById(R.id.ItemNota);
        }

        public void asignarDatos(notas notas) {
            nota.setText(notas.getTitulo());
        }
    }
}
