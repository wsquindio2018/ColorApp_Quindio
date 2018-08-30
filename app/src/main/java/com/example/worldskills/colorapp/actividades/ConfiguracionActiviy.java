package com.example.worldskills.colorapp.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.entidades.Tipo;

import java.util.ArrayList;

public class ConfiguracionActiviy extends AppCompatActivity {

    //Esta clase permite al usuario configurar la modalidad de juego, las modalidades son(Intentos,Tiempo)

    ImageButton btnEmpezar;
    Spinner tipoJuego;
    int posicion;
    EditText campoIntentos, campoTiempo, campoTiempoPalabra;
    ArrayList arrayTipoJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_activiy);
        arrayTipoJuego = new ArrayList();
        arrayTipoJuego.add("Seleccione el tipo de juego");
        arrayTipoJuego.add("Juego por intentos");
        arrayTipoJuego.add("Juego por tiempo");

        tipoJuego = findViewById(R.id.spinnerTipoJuego);
        ArrayAdapter<CharSequence> adapterTipoJuego = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayTipoJuego);
        tipoJuego.setAdapter(adapterTipoJuego);
        tipoJuego.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    tipoJuego(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        campoIntentos = findViewById(R.id.campoCantidadIntentos);
        campoTiempo = findViewById(R.id.campoTiempoMilisegundos);
        campoTiempoPalabra = findViewById(R.id.campoTiempoPalabra);

    }

    //Este metodo sirve para ocultar o mostrarlos los campos, dependiendo lo que se seleccione en el spinner
    private void tipoJuego(int position) {
        switch (position) {
            case 1:
                campoTiempo.setVisibility(View.INVISIBLE);
                campoIntentos.setVisibility(View.VISIBLE);
                posicion = 1;
                break;

            case 2:
                campoTiempo.setVisibility(View.VISIBLE);
                campoIntentos.setVisibility(View.INVISIBLE);
                posicion = 2;
                break;
        }
    }

    //realiza el on click del Image button de la calse configuracion
    public void onClick(View view) {
        Intent miIntent = new Intent(ConfiguracionActiviy.this, JugarActivity.class);
        if (posicion == 1 && !campoIntentos.getText().toString().equals("")) {
            Tipo.intentos = Integer.parseInt(campoIntentos.getText().toString());
            Tipo.tiempoPalabra = Integer.parseInt(campoTiempoPalabra.getText().toString());
            Tipo.tipo = posicion;
            startActivity(miIntent);
            finish();
        } else if (posicion == 2 && !campoTiempo.getText().toString().equals("")) {
            Tipo.tiempoPartida = Integer.parseInt(campoTiempo.getText().toString());
            Tipo.tiempoPalabra = Integer.parseInt(campoTiempoPalabra.getText().toString());
            Tipo.tipo = posicion;
            startActivity(miIntent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}
