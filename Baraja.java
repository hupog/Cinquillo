/*
* Representa la baraja española pero con 8 y 9, en total 48 cartas, 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
 */

package es.uvigo.esei.aed1.core;

import cola.Cola;
import cola.EnlazadaCola;
import es.uvigo.esei.aed1.core.Carta.Palo;


public class Baraja {

    private final Cola <Carta> baraja;    //La baraja va ha ser una cola de cartas
    
    public Baraja (){
        baraja = new EnlazadaCola<>();
        
        for(int i = 0; i < 12;i++){
            baraja.insertar(new Carta(Palo.Espadas,i+1));
        }
        for(int i = 0; i < 12;i++){
            baraja.insertar(new Carta(Palo.Oros,i + 1));
        }
        for(int i = 0; i < 12;i++){
            baraja.insertar(new Carta(Palo.Bastos,i + 1));
        }
        for(int i = 0; i < 12;i++){
            baraja.insertar(new Carta(Palo.Copas,i + 1));
        }
    }
    
    public void insertarCarta(Carta c){
        baraja.insertar(c);
    }
    
    public Carta sacarCarta(){
        if(baraja.esVacio()){
            return null;
        }
        Carta carta = baraja.primero();
        baraja.suprimir();
        return carta;
    }
    
    public int numCartas(){
        return baraja.tamaño();
    }
    
    public void barajar(){
        
        Carta[] arrayCartas = new Carta[baraja.tamaño()];   
        //Se genera un array con el mismo tamaño que la Baraja
        
        for (int i = 0; i < arrayCartas.length ; i++){  
             arrayCartas[i] = sacarCarta();
        }
        //Se insertan las cartas de Baraja en el array, dejando este vacío
        
        for (int j = 0; j < arrayCartas.length ; j++){
             int numeroAleatorio = (int) (Math.random()*arrayCartas.length);
             Carta cartaTemporal = arrayCartas[j]; 
             arrayCartas[j]=arrayCartas[numeroAleatorio];
             
             arrayCartas[numeroAleatorio]=cartaTemporal;
        }
        /**Dentro del array, se cambian de posición todas las cartas comenzando
         * por las situadas en el 0 hasta el final del array
         */

        for (Carta arrayCarta : arrayCartas) {
            baraja.insertar(arrayCarta);
        }
    }
    
   
}
