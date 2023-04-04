/**
 * Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento Primera entrega). 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.Collection;
import java.util.*;


public class Juego {
    private final IU iu;


    public Juego(IU iu) {
        this.iu = iu;
    }

    public void jugar() {
        
        //Se crea la baraja
        Baraja baraja = new Baraja();
        
        //Se obtiene informacion de quien va a jugar
        Collection listaJugadores = iu.pedirDatosJugadores();
        Iterator it = listaJugadores.iterator();
        
        //Se crean los Jugadores
        List<Jugador> jugadores = new ArrayList<>();
        
        //Metemos a los jugadores en la lista
        for (int i = 0; i < listaJugadores.size(); i++) {
            Jugador nuevoJugador = new Jugador(it.next().toString());
            jugadores.add(nuevoJugador);
        }
        
        //Se baraja y se reparten las cartas entre los jugadores
        baraja.barajar();
        for (int i = 0; i < 48 / jugadores.size(); i++) {
            for (int j = 0; j < jugadores.size(); j++) {
                jugadores.get(j).añadirCartas(baraja.sacarCarta());
            }
        }
        
        //Se enseñan las cartas de cada Jugador
        iu.mostrarJugadores(jugadores);
        
        
        //Se elije un jugador al azar para empezar la partida
        int posTurno = (int) (Math.random() * jugadores.size());
        System.out.println("El Jugador que empezará la partida es " + jugadores.get(posTurno).getNombre());
        
        
    }   

}
