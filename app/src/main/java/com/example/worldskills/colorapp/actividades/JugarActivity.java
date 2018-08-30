package com.example.worldskills.colorapp.actividades;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.entidades.Tipo;

import java.util.ArrayList;
import java.util.Random;

public class JugarActivity extends AppCompatActivity {

    TextView palabra, txtDesplegadas, txtCorretas, txtIncorrectas, txtIntentos, txtTiempo,txtTiempoPalabra;
    Button bnt1, bnt2, bnt3, bnt4;
    ArrayList<String> listaColores;
    ArrayList<String> listaPalabras;
    int numeros[];
    int resultados[];
    int n, k, numeroC, numeroP, desplegadas, intentos, correctas, tiempoTotal, tiempoPalabra;
    Random rnd;
    CountDownTimer timerPalabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);


        intanciar();
        comprobarTipoJuego();
        llenarArray();

        bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentos == 0) {
                    termina();
                } else {
                    comprobar(1);
                }
            }
        });
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentos == 0) {
                    termina();
                } else {
                    comprobar(1);
                }
            }
        });
        bnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentos == 0) {
                    termina();
                } else {
                    comprobar(1);
                }
            }
        });
        bnt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentos == 0) {
                    termina();
                } else {
                    comprobar(1);
                }
            }
        });
    }

    //Metodo que se encarga de mostrar una ventana de dialogo con los datos del usuario
    private void termina() {
    }

    //Verifica si esta correcta o incorrecta la tinta de la palabra con el boton que selecciono el usuario
    private void comprobar(int boton) {
        desplegadas++;
        switch (boton) {
            case 1:
                if (listaColores.get(resultados[0] - 1).equals(listaColores.get(numeroC))) {
                    correctas++;
                } else {
                    intentos--;
                }
                break;
            case 2:
                if (listaColores.get(resultados[1] - 1).equals(listaColores.get(numeroC))) {
                    correctas++;
                } else {
                    intentos--;
                }
                break;
            case 3:
                if (listaColores.get(resultados[2] - 1).equals(listaColores.get(numeroC))) {
                    correctas++;
                } else {
                    intentos--;
                }
                break;
            case 4:
                if (listaColores.get(resultados[3] - 1).equals(listaColores.get(numeroC))) {
                    correctas++;
                } else {
                    intentos--;
                }
                break;
            case 5:

                break;
            case 6:
                break;
        }
        generarNumeros();
        txtDesplegadas.setText("Desplegadas " + desplegadas);
        txtCorretas.setText("Correctas " + correctas);
        txtIntentos.setText("Desplegadas " + intentos);
    }

    //se comprueba el tipo de juego
    private void comprobarTipoJuego() {

    }

    //se almacena en los array todos los datos
    private void llenarArray() {
        listaColores = new ArrayList<>();
        listaPalabras = new ArrayList<>();

        listaColores.add("#FFF9E974");
        listaColores.add("#FF4885FF");
        listaColores.add("#FF3FB560");
        listaColores.add("#FFFF4D50");

        listaPalabras.add("Amarillo");
        listaPalabras.add("Azul");
        listaPalabras.add("Verde");
        listaPalabras.add("Rojo");

        generarNumeros();
        empezarTiempoPalabra();
    }

    //Controla el tiempo por cada palabra
    private void empezarTiempoPalabra() {
        timerPalabra=new CountDownTimer(Tipo.tiempoPalabra,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTiempoPalabra.setText("Tiempo "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

            }
        };
        timerPalabra.start();
    }

    //Genera los numero aletoriamente
    private void generarNumeros() {
        numeroC = (int) Math.floor(Math.random() * 4);
        numeroP = (int) Math.floor(Math.random() * 4);

        n = 4;
        k = n;

        numeros = new int[n];
        resultados = new int[n];

        for (int i = 0; i < n; i++) {
            numeros[i] = i + 1;
        }

        int res;
        rnd = new Random();

        for (int i = 0; i < n; i++) {
            res = rnd.nextInt(k);
            resultados[i] = numeros[res];
            numeros[res] = numeros[k - 1];
            k--;
        }

        bnt1.setBackgroundColor(Color.parseColor(listaColores.get(resultados[0] - 1)));
        bnt2.setBackgroundColor(Color.parseColor(listaColores.get(resultados[1] - 1)));
        bnt3.setBackgroundColor(Color.parseColor(listaColores.get(resultados[2] - 1)));
        bnt4.setBackgroundColor(Color.parseColor(listaColores.get(resultados[3] - 1)));
        palabra.setTextColor(Color.parseColor(listaColores.get(numeroC)));
        palabra.setText(listaPalabras.get(numeroP));
    }


    //Este metodo sirve para instanciar todas las variables botones y campos de texto de esta clase
    private void intanciar() {
        bnt1 = findViewById(R.id.btn1);
        bnt2 = findViewById(R.id.btn2);
        bnt3 = findViewById(R.id.btn3);
        bnt4 = findViewById(R.id.btn4);
        palabra = findViewById(R.id.palabra);
        txtDesplegadas = findViewById(R.id.desplegadas);
        txtCorretas = findViewById(R.id.correctas);
        txtIntentos = findViewById(R.id.intentos);
        txtTiempo = findViewById(R.id.tiempoTotal);
        txtTiempoPalabra = findViewById(R.id.tiempo);


        intentos = Tipo.intentos;


        txtDesplegadas.setText("Desplegadas " + desplegadas);
        txtCorretas.setText("Correctas " + correctas);
        txtIntentos.setText("Desplegadas " + intentos);
    }
}
