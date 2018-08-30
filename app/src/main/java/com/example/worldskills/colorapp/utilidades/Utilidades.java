package com.example.worldskills.colorapp.utilidades;


//Esta clase sirve para crear la base de datos
public class Utilidades {

    public static final String TABLA_PUNTAJES = "puntajes";
    public static final String DESPLEGADAS = "desplegadas";
    public static final String CORRECTAS = "correctas";
    public static final String INCORRECTAS = "incorrectas";
    public static final String CREAR_TABLA_PUNTAJES = "CREATE TABLE " + TABLA_PUNTAJES
            + "( " + DESPLEGADAS + " INTEGER, "
            + CORRECTAS + " INTEGER, "
            + INCORRECTAS + " INTEGER)";
}
