/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.core.Carta.Palo;
import es.uvigo.esei.aed1.iu.IU;
import java.util.Collection;
import java.util.*;


public class Juego {
    private final IU iu;
    //Se crea la baraja
    Baraja baraja = new Baraja();
    //Se crea mesa
    Mesa mesa = new Mesa();


    public Juego(IU iu) {
        this.iu = iu;
    }

    public void jugar() {
        
        
        
        //Se obtiene informacion de quien va a jugar
        Collection<String> listaJugadores = iu.pedirDatosJugadores();
        Iterator<String> it = listaJugadores.iterator();
        
        //Se crean los Jugadores
        List<Jugador> jugadores = new ArrayList<>();
        //Metemos a los jugadores en la lista
        for (int i = 0; i < listaJugadores.size(); i++) {
            Jugador nuevoJugador = new Jugador(it.next());
            jugadores.add(nuevoJugador);
        }
        
        
        
        //Se enseñan las cartas de cada Jugador
        iu.mostrarJugadores(jugadores);
        
        //CONTADOR QUE VA A LLEVAR EL NUMERO DE PUNTOS

        //Se elije un jugador al azar para empezar la partida
        boolean hayGanador = false;
        boolean asOros = false;
        
        
        do{

           do {
               
               
               //Se baraja y se reparten las cartas entre los jugadores
               baraja.barajar();
               for (int i = 0; i < 48 / jugadores.size(); i++) {
                   for (int j = 0; j < jugadores.size(); j++) {
                       jugadores.get(j).añadirCartas(baraja.sacarCarta());
                   }
               }

               int contador = elegirJugador(jugadores);
               iu.mostrarJugador(jugadores.get(contador));

            if (jugadores.get(contador).tieneCartasValidas(mesa)) {
                int num;
                
                do {
                    
                    do{
                        num = iu.leeNum("Seleccion una numero de su mano (0,1,2..)");
                    }while(num < 0 || num > jugadores.get(contador).getMano().size()-1);
                    
                    
                    
                    if (!mesa.cartaValida(jugadores.get(contador).getMano().get(num))) {
                        iu.mostrarMensaje("Carta incorrecta");
                    }
                    
                } while (!mesa.cartaValida(jugadores.get(contador).getMano().get(num)));
                
                
                iu.mostrarMensaje("Introduciendo Carta...");
                
                
                //SI INTRODUCE EL AS DE OROS
                if(jugadores.get(contador).getMano().get(num).getPalo().equals(Palo.Oros) && jugadores.get(contador).getMano().get(num).getNumero()==1){
                    jugadores.get(contador).setPuntosAcumulados(jugadores.get(contador).getPuntosAcumulados()+1);
                    asOros = true;
                    
                }

                mesa.insertarCarta(jugadores.get(contador).getMano().get(num));
                
                
                //JUGADOR SE QUEDA SIN CARTAS
                if(jugadores.get(contador).size() == 0){
                    hayGanador = true;
                    jugadores.get(contador-1).setPuntosAcumulados(jugadores.get(contador-1).getPuntosAcumulados()+1);
                }

                System.out.println("MESA: \n");

                iu.mostrarMensaje(mesa.toString());
                
                //CONTROL DE CONTADOR
                if (contador == jugadores.size() - 1) {
                    contador = 0;
                } else {
                    contador++;
                }
                siguienteJugador(contador, jugadores.size());

            } else {
                iu.mostrarMensaje("No tienes cartas válidas para echar, se saltará al siguiente jugador.");
                
                if (contador == jugadores.size() - 1) {
                    contador = 0;
                } else {
                    contador++;
                }
                
                iu.mostrarMensaje(mesa.toString());
            }
            
            
            //ELIMINAR CARTAS DE TODOS LO JUGADORES PARA LA BARAJA
            for(int i = 0; i < jugadores.size(); i++){
                if(!jugadores.get(i).getMano().isEmpty()){
                    for(int j= 0; j < jugadores.get(i).size(); j++){
                        
                    }
                }
            }
            
            
        } while (!hayGanador);
           
        }while(!asOros);
           
        
        //iu.mostrarMensaje("El ganador es, ", jugadores.get(contador-1).getNombre());

    }
    
//    public boolean tieneCartasValidas (Jugador jugador, Mesa mesa){
//        for(int i = 0; i < jugador.getMano().size(); i++){
//            if(mesa.cartaValida(jugador.getMano().get(i))){
//                return true;
//            }
//        }
//        return false;
//    }
    
    public int elegirJugador(List<Jugador> jugadores){
        int posTurno = (int) (Math.random() * jugadores.size());
        iu.mostrarMensaje("El Jugador que empezará la partida es " + jugadores.get(posTurno).getNombre());
        
        //Devuelvo esto porque lo necesito para el bucle de la partida
        return posTurno;
    }
    
    
    public int siguienteJugador(int contador, int numJugadores) {

        if (contador == numJugadores - 1) {
            contador = 0;
        } else {
            contador++;
        }
        return contador;
    }



}
