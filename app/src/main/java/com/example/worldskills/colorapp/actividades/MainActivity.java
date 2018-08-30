package com.example.worldskills.colorapp.actividades;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.entidades.Tipo;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.jugar:
                Tipo.tipo=1;
                Tipo.tiempoPalabra=3;
                Tipo.intentos=3;
                intent=new Intent(MainActivity.this,JugarActivity.class);
                break;
            case R.id.puntajes:
                break;
            case R.id.configuracion:
                intent=new Intent(MainActivity.this,ConfiguracionActiviy.class);
                break;
        }
        startActivity(intent);
    }

}
