package com.example.worldskills.colorapp.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.worldskills.colorapp.R;

import java.util.ArrayList;
import java.util.Random;

public class JugarActivity extends AppCompatActivity {

    Button bnt1,bnt2,bnt3,bnt4;
    ArrayList<String> listaColores;
    ArrayList<String> listaPalabras;
    int numeros[];
    int resultados[];
    int n, k, numeroC, numeroP;
    Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        intanciar();
        llenarArray();
    }

    private void llenarArray() {
        listaColores=new ArrayList<>();
    }


    //Este metodo sirve para instanciar todas las variables botones y campos de texto de esta clase
    private void intanciar() {
        bnt1=findViewById(R.id.btn1);
        bnt2=findViewById(R.id.btn2);
        bnt3=findViewById(R.id.btn3);
        bnt4=findViewById(R.id.btn4);
    }
}
