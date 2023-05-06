/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> mano;
    private int puntosPartida;
    private int puntosAsDeOro;
    private int puntosAcumulados;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.puntosPartida = 0;
        this.puntosAsDeOro = 0;
        this.puntosAcumulados = puntosPartida + puntosAsDeOro;
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

    public int getPuntosPartida() {
        return puntosPartida;
    }

    public int getPuntosAsDeOro() {
        return puntosAsDeOro;
    }

    public void setPuntosPartida(int puntosPartida) {
        this.puntosPartida = puntosPartida;
    }

    public void setPuntosAsDeOro(int puntosAsDeOro) {
        this.puntosAsDeOro = puntosAsDeOro;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
    
    

    //Insertar una carta a la mano
    public void añadirCartas(Carta carta){
        mano.add(carta);
    }
    
    public void enseñarMano(){
        int i = 0;
        for (Carta carta : mano) {
            System.out.println("[" + i + "]" + "\t" + carta.toString());
            i++;
        }
    }
    
    public int size(){
        if(getMano().isEmpty()){
            return 0;
        }else{
            return getMano().size();
        }
    }
    
    public void removeCarta(Carta c){
        getMano().remove(c);
    }
    
    public boolean tieneCartasValidas (Mesa mesa){
        for(int i = 0; i < mano.size(); i++){
            if(mesa.cartaValida(mano.get(i))){
                return true;
            }
        }
        return false;
    }
    
    
}
