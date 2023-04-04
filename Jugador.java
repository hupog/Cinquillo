/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;

import java.util.ArrayList;
import java.util.List;
import pila.Pila;

public class Jugador {
    private String nombre;
    private List<Carta> mano;
    private int puntosAcumulados;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<Carta>();
        this.puntosAcumulados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMano(List<Carta> mano) {
        this.mano = mano;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
    
    //Insertar una carta a la mano
    public void añadirCartas(Carta carta){
        mano.add(carta);
    }
    
    public void enseñarMano(){
        for (Carta carta : mano) {
            System.out.println(carta.toString());
        }
    }
    
}
