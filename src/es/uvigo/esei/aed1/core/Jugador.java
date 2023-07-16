/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */
package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.core.Carta.Palo;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> mano;
    private int puntosPartida;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.puntosPartida = 0;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getMano() {
        return mano;
    }


    public int getPuntosPartida() {
        return puntosPartida;
    }


    public void setPuntosPartida(int puntosPartida) {
        this.puntosPartida = puntosPartida;
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
    
    public boolean comprobarAsOros(int num){
        return getMano().get(num).getNumero() == 1 && getMano().get(num).getPalo().equals(Palo.Oros);
    }
    
    public void eliminarMano(){
        int tam = size();
        for (int i = 0; i < tam; i++) {
            if(!getMano().isEmpty()){
                mano.remove(i);
            }
            
        }
    }
    public void eliminarCarta(Carta c){
        getMano().remove(c);
    }
}