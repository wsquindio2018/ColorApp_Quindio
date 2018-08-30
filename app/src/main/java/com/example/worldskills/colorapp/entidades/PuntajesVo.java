package com.example.worldskills.colorapp.entidades;

public class PuntajesVo {

    //Esta clase sirve para capturar los datos de la partida, para que posteriormente sean almacenados en uan lista
    private int desplegadas;
    private int correctas;
    private int incorrectas;

    public int getDesplegadas() {
        return desplegadas;
    }

    public void setDesplegadas(int desplegadas) {
        this.desplegadas = desplegadas;
    }

    public int getCorrectas() {
        return correctas;
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(int incorrectas) {
        this.incorrectas = incorrectas;
    }
}
