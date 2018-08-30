package com.example.worldskills.colorapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.entidades.PuntajesVo;

import java.util.ArrayList;

public class PuntajesAdapter extends RecyclerView.Adapter<PuntajesAdapter.PuntajesHolder> {


    //Esta clase permite acomodar los datos de una lista, los cuales se adaptaran a un modelo creado previamente

    public PuntajesAdapter(ArrayList<PuntajesVo> listaPuntajes) {
        this.listaPuntajes = listaPuntajes;
    }

    ArrayList<PuntajesVo> listaPuntajes;

    @Override
    public PuntajesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_puntajes_adapter, null, false);
        return new PuntajesHolder(view);
    }

    @Override
    public void onBindViewHolder(PuntajesHolder holder, int position) {
        holder.desplegadas.setText("Desplegadas: " + listaPuntajes.get(position).getDesplegadas());
        holder.desplegadas.setText("Correctas: " + listaPuntajes.get(position).getCorrectas());
        holder.desplegadas.setText("Incorrectas: " + listaPuntajes.get(position).getIncorrectas());
    }

    @Override
    public int getItemCount() {
        return listaPuntajes.size();
    }

    public class PuntajesHolder extends RecyclerView.ViewHolder {
        TextView desplegadas, correctas, incorrectas;

        public PuntajesHolder(View itemView) {
            super(itemView);
            desplegadas = itemView.findViewById(R.id.desplegadasModelo);
            correctas = itemView.findViewById(R.id.correctasModelo);
            incorrectas = itemView.findViewById(R.id.incorrectasModelo);
        }
    }
}
