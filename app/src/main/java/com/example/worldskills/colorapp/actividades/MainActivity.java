package com.example.worldskills.colorapp.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.entidades.Tipo;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //Controla el evento onclick de los 3 botones de la clase MainActivity
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.jugar:
                Tipo.tipo=1;
                Tipo.tiempoPalabra=4000;
                Tipo.intentos=3;
                intent=new Intent(MainActivity.this,JugarActivity.class);
                break;
            case R.id.puntajes:
                intent=new Intent(MainActivity.this,ListaPuntajesActivity.class);
                break;
            case R.id.configuracion:
                intent=new Intent(MainActivity.this,ConfiguracionActiviy.class);
                break;
        }
        startActivity(intent);
    }

}
