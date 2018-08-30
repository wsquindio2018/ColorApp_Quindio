package com.example.worldskills.colorapp.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.adapter.PuntajesAdapter;
import com.example.worldskills.colorapp.entidades.PuntajesVo;

import java.util.ArrayList;

public class ListaPuntajesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PuntajesAdapter adapter;
    ArrayList<PuntajesVo>listaPuntajes;
    PuntajesVo puntajesVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_puntajes);
        recyclerView = findViewById(R.id.recyclerPuntajes);

        puntajesVo = new PuntajesVo();
        listaPuntajes = new ArrayList<>();
        for (int i = 0;i<12;i++){
            puntajesVo.setDesplegadas(100);
            puntajesVo.setCorrectas(100);
            puntajesVo.setIncorrectas(100);
            listaPuntajes.add(puntajesVo);
        }

        adapter = new PuntajesAdapter(listaPuntajes);
        recyclerView.setAdapter(adapter);
    }
}
