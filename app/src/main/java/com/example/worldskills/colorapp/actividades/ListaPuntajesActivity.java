package com.example.worldskills.colorapp.actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.adapter.PuntajesAdapter;
import com.example.worldskills.colorapp.entidades.PuntajesVo;
import com.example.worldskills.colorapp.utilidades.Conexion;
import com.example.worldskills.colorapp.utilidades.Utilidades;

import java.util.ArrayList;

public class ListaPuntajesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PuntajesAdapter adapter;
    ArrayList<PuntajesVo> listaPuntajes;
    PuntajesVo puntajesVo;
    Conexion conn;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_puntajes);
        conn = new Conexion(getApplicationContext(), "Puntos", null, 1);
        recyclerView = findViewById(R.id.recyclerPuntajes);

        puntajesVo = new PuntajesVo();
        listaPuntajes = new ArrayList<>();


        consultar();
        adapter = new PuntajesAdapter(listaPuntajes);
        recyclerView.setAdapter(adapter);
    }


    //Consulta la bases de datos sql
    private void consultar() {
        bd=conn.getReadableDatabase();

        Cursor cursor=bd.rawQuery(" SELECT * FROM "+ Utilidades.TABLA_PUNTAJES+" ORDER BY "+Utilidades.CORRECTAS+" DESC ",null);

        int a=0;
        while (cursor.moveToNext()){
            puntajesVo=new PuntajesVo();
            puntajesVo.setDesplegadas(cursor.getInt(0));
            puntajesVo.setCorrectas(cursor.getInt(1));
            puntajesVo.setIncorrectas(cursor.getInt(2));
            if (a!=4){
                listaPuntajes.add(puntajesVo);
                a++;
            }
        }
    }
}
