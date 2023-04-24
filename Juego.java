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
        //Se crea la mesa
        Mesa mesa = new Mesa();

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
        //A partir de aqui comienza el bucle del juego
        //Se baraja y se reparten las cartas entre los jugadores
        repartirCartas(baraja, jugadores);

        //Se enseñan las cartas de cada Jugador
        iu.mostrarJugadores(jugadores);

        //Se elije un jugador al azar para empezar la partida
        boolean hayGanador = false;
        int posTurno = elegirJugador(jugadores);
        int contador = posTurno;
        //Aqui empieza el turno
        do {
            for (int i = 0; i < jugadores.size(); i++) {
                mesa.toString();
                if (tieneCartasValidas(jugadores.get(contador), mesa)) {
                    int num;
                    do {
                        num = iu.leeNum("Que carta se quiere colocar?(0, 1, 2, 3...): ");

                    } while (!mesa.cartaValida(jugadores.get(contador).getMano().get(num)));

                    if (jugadores.get(contador).getMano().isEmpty()) {
                        hayGanador = true;
                        iu.mostrarMensaje("El jugador", contador, "se ha quedado sin cartas, por tanto ha ganado.");
                        break;
                    } else {
                        siguienteJugador(contador, jugadores.size());
                    }
                }else{
                    iu.mostrarMensaje("No tienes cartas válidas para echar, se saltará al siguiente jugador.");
                    siguienteJugador(contador, jugadores.size());
                }
            }
        } while (hayGanador == false);
    }
    
    public void repartirCartas(Baraja baraja, List<Jugador> jugadores){
        baraja.barajar();
        for (int i = 0; i < 48 / jugadores.size(); i++) {
            for (int j = 0; j < jugadores.size(); j++) {
                jugadores.get(j).añadirCartas(baraja.sacarCarta());
            }
        }
    }
    
    public int elegirJugador(List<Jugador> jugadores){
        int posTurno = (int) (Math.random() * jugadores.size());
        iu.mostrarMensaje("El Jugador que empezará la partida es " + jugadores.get(posTurno).getNombre());
        
        //Devuelvo esto porque lo necesito para el bucle de la partida
        return posTurno;
    }
    
    public boolean tieneCartasValidas(Jugador jugador, Mesa mesa){
        for (int i = 0; i < jugador.getMano().size(); i++) {
            if(mesa.cartaValida(jugador.getMano().get(i))){
                return true;
            }
        }
        return false;
    }

    public static void siguienteJugador(int contador, int numJugadores){
        
        if(contador == numJugadores){
                        contador =0;
                    }else{
                        contador++;
                    }
    }
}
