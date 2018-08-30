package com.example.worldskills.colorapp.actividades;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldskills.colorapp.R;
import com.example.worldskills.colorapp.entidades.Tipo;
import com.example.worldskills.colorapp.utilidades.Conexion;
import com.example.worldskills.colorapp.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Random;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

public class JugarActivity extends AppCompatActivity {

    TextView palabra, txtDesplegadas, txtCorretas, txtIntentos, txtTiempo, txtTiempoPalabra;
    FloatingActionButton play;
    Button bnt1, bnt2, bnt3, bnt4;
    ArrayList<String> listaColores;
    ArrayList<String> listaPalabras;
    int numeros[];
    int resultados[];
    int n, k, numeroC, numeroP, desplegadas, intentos, correctas, tiempoTotal, tiempoPalabra, pausa, tiempoPausa, numeroPausas = 0, tiempoTo = 1, pausaTo, tiempoPausaTo;
    boolean activo = false;
    Random rnd;
    CountDownTimer timerPalabra, timerPartida;
    Conexion conn;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        conn=new Conexion(getApplicationContext(),"Puntos",null,1);

        intanciar();
        comprobarTipoJuego();
        llenarArray();

        bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tipo.tipo == 2) {
                    if (tiempoTo == 0) {
                        termina();
                    } else {
                        comprobar(1);
                    }
                } else if (Tipo.tipo == 1) {
                    if (intentos == 1) {
                        termina();
                    } else {
                        comprobar(1);
                    }
                }
            }
        });
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tipo.tipo == 2) {
                    if (tiempoTo == 0) {
                        termina();
                    } else {
                        comprobar(2);
                    }
                } else if (Tipo.tipo == 1) {
                    if (intentos == 1) {
                        termina();
                    } else {
                        comprobar(2);
                    }
                }
            }
        });
        bnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tipo.tipo == 2) {
                    if (tiempoTo == 0) {
                        termina();
                    } else {
                        comprobar(3);
                    }
                } else if (Tipo.tipo == 1) {
                    if (intentos == 1) {
                        termina();
                    } else {
                        comprobar(3);
                    }
                }
            }
        });
        bnt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tipo.tipo == 2) {
                    if (tiempoTo == 0) {
                        termina();
                    } else {
                        comprobar(4);
                    }
                } else if (Tipo.tipo == 1) {
                    if (intentos == 1) {
                        termina();
                    } else {
                        comprobar(4);
                    }
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tipo.tipo == 2) {
                    if (numeroPausas >= 2) {
                        Toast.makeText(getApplicationContext(), "Ya utilizaste tus 2 oportunidades de pausar", Toast.LENGTH_SHORT).show();
                        activo = false;
                        desactivar();
                        generarNumeros();
                        play.setImageResource(R.drawable.pausa);
                        play.setEnabled(false);
                    } else {
                        if (activo == false) {
                            pausaTo = tiempoPausaTo;
                            pausa = tiempoPausa;
                            timerPalabra.cancel();
                            txtTiempo.setText("Tiempo Total " + pausaTo);
                            txtTiempoPalabra.setText("Tiempo  " + pausa);
                            timerPartida.cancel();
                            play.setImageResource(R.drawable.play);
                            activo = true;
                            numeroPausas++;
                        } else {
                            play.setImageResource(R.drawable.pausa);
                            activo = false;
                            generarNumeros();
                            desplegadas++;
                        }
                        desactivar();
                    }
                } else if (Tipo.tipo == 1) {
                    if (numeroPausas >= 2) {
                        Toast.makeText(getApplicationContext(), "Ya utilizaste tus 2 oportunidades de pausar", Toast.LENGTH_SHORT).show();
                        activo = false;
                        desactivar();
                        generarNumeros();
                        play.setImageResource(R.drawable.pausa);
                        play.setEnabled(false);
                    } else {
                        if (activo == false) {
                            pausa = tiempoPausa;
                            txtTiempoPalabra.setText("Tiempo " + pausa);
                            timerPalabra.cancel();
                            play.setImageResource(R.drawable.play);
                            activo = true;
                            numeroPausas++;
                        } else {
                            play.setImageResource(R.drawable.pausa);
                            activo = false;
                            generarNumeros();
                            desplegadas++;
                        }
                        desactivar();
                    }
                }

            }
        });
    }

    //Desactiva los botones
    private void desactivar() {
        if (activo == false) {
            bnt1.setEnabled(true);
            bnt2.setEnabled(true);
            bnt3.setEnabled(true);
            bnt4.setEnabled(true);
        } else {
            bnt1.setEnabled(false);
            bnt2.setEnabled(false);
            bnt3.setEnabled(false);
            bnt4.setEnabled(false);
        }
    }

    //Metodo que se encarga de mostrar una ventana de dialogo con los datos del usuario
    private void termina() {
        try{
            timerPalabra.cancel();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Finaliza");
            String mensaje = "";
            mensaje += "Palabras Correctas: " + correctas + "\n";
            if (Tipo.tipo==2){
                mensaje += "Palabras Incorrectas: " + intentos*(-1);
            }else if (Tipo.tipo==1){
                mensaje += "Palabras Incorrectas: " + Tipo.intentos;
            }
            builder.setMessage(mensaje);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (Tipo.tipo == 1) {
                        registrar();
                    } else {
                        finish();
                    }
                }
            });
            Dialog dialog = builder.create();
            dialog.show();
        }catch (Exception e){
            finish();
        }
    }

    //Metodo para registrar cuando se encuentra en tipo de juego por defecto
    private void registrar() {
        bd = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CORRECTAS, correctas);
        values.put(Utilidades.INCORRECTAS, Tipo.intentos);
        values.put(Utilidades.DESPLEGADAS, desplegadas);

        bd.insert(Utilidades.TABLA_PUNTAJES, Utilidades.CORRECTAS, values);
        finish();
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
                timerPalabra.cancel();
                break;
            case 2:
                if (listaColores.get(resultados[1] - 1).equals(listaColores.get(numeroC))) {
                    correctas++;
                } else {
                    intentos--;
                }
                timerPalabra.cancel();
                break;
            case 3:
                if (listaColores.get(resultados[2] - 1).equals(listaColores.get(numeroC))) {
                    correctas++;
                } else {
                    intentos--;
                }
                timerPalabra.cancel();
                break;
            case 4:
                if (listaColores.get(resultados[3] - 1).equals(listaColores.get(numeroC))) {
                    correctas++;
                } else {
                    intentos--;
                }
                timerPalabra.cancel();
                break;
            case 5:
                intentos--;
                timerPalabra.cancel();
                break;
            case 6:
                break;
        }
        generarNumeros();
        txtDesplegadas.setText("Desplegadas " + desplegadas);
        txtCorretas.setText("Correctas " + correctas);
        txtIntentos.setText("Intentos " + intentos);
    }

    //se comprueba el tipo de juego
    private void comprobarTipoJuego() {
        if (Tipo.tipo == 1) {
            intentos = Tipo.intentos;
            txtTiempo.setVisibility(View.INVISIBLE);
        } else if (Tipo.tipo == 2) {
            txtIntentos.setVisibility(View.INVISIBLE);
            tiempoTotal = Tipo.tiempoPartida;
            tiempoPartida();
        }
        tiempoPalabra = Tipo.tiempoPalabra;
    }

    //Corre el tiempo por partida en el juego en modo tiempo
    private void tiempoPartida() {
        timerPartida = new CountDownTimer(tiempoTotal, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTiempo.setText("Tiempo total " + millisUntilFinished / 1000);
                tiempoPausaTo = (int) millisUntilFinished / 1000;
            }

            @Override
            public void onFinish() {
                tiempoTo = 0;
                termina();
            }
        };
        timerPartida.start();
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
    }

    //Controla el tiempo por cada palabra
    private void empezarTiempoPalabra() {
        timerPalabra = new CountDownTimer(Tipo.tiempoPalabra, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoPausa = (int) millisUntilFinished / 1000;
                txtTiempoPalabra.setText("Tiempo " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                if (Tipo.tipo == 2) {
                    if (tiempoTo == 0) {
                        termina();
                    } else {
                        comprobar(5);
                    }
                } else if (Tipo.tipo == 1) {
                    if (intentos == 1) {
                        termina();
                    } else {
                        comprobar(5);
                    }
                }
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

        empezarTiempoPalabra();
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
        play = findViewById(R.id.play);


        if (Tipo.tipo==1){
            intentos=Tipo.intentos;
        }
        txtDesplegadas.setText("Desplegadas " + desplegadas);
        txtCorretas.setText("Correctas " + correctas);
        txtIntentos.setText("Intentos " + intentos);
    }

}
