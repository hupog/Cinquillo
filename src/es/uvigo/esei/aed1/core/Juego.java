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

    //Se crea la baraja
    Baraja baraja = new Baraja();
    //Se crea mesa
    Mesa mesa = new Mesa();

    private int puntosOro = 2;

    public Juego(IU iu) {
        this.iu = iu;
    }

    public void jugar() {
        boolean AsOros = false;

        //Se obtiene informacion de quien va a jugar
        Collection<String> listaJugadores = iu.pedirDatosJugadores();
        Iterator it = listaJugadores.iterator();

        //Se crean los Jugadores
        List<Jugador> jugadores = new ArrayList<>();

        //Metemos a los jugadores en la lista
        for (int i = 0; i < listaJugadores.size(); i++) {
            Jugador nuevoJugador = new Jugador(it.next().toString());
            jugadores.add(nuevoJugador);
        }

        do {
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
            boolean hayGanador = false;
            int contador = elegirJugador(jugadores);

            do {

                iu.mostrarJugador(jugadores.get(contador));

                if (iu.tieneCartasValidas(jugadores.get(contador), mesa)) {
                    int num;
                    do {

                        do {
                            num = iu.leeNum("Seleccion una numero de su mano (0,1,2..)");
                        } while (num < 0 || num > iu.obtenerMano(jugadores.get(contador)).size() - 1);

                        if (!mesa.cartaValida(iu.obtenerMano(jugadores.get(contador)).get(num))) {
                            iu.mostrarMensaje("Carta incorrecta");
                        }

                    } while (!mesa.cartaValida(iu.obtenerMano(jugadores.get(contador)).get(num)));

                    //SI SALE EL AS DE OROS
                    if (jugadores.get(contador).comprobarAsOros(num)) {
                        iu.mostrarMensaje("Se ha introducido el As de oros");
                        jugadores.get(contador).setPuntosPartida(jugadores.get(contador).getPuntosPartida() + puntosOro);
                        AsOros = true;
                    }

                    iu.mostrarMensaje("Introduciendo Carta...");

                    //INSERTAR CARTA Y ELIMINAR
                    mesa.insertarCarta(iu.obtenerMano(jugadores.get(contador)).get(num));
                    jugadores.get(contador).eliminarCarta(iu.obtenerMano(jugadores.get(contador)).get(num));

                    //SE ACABA LA PARTIDA
                    if (jugadores.get(contador).size() == 0) {
                        jugadores.get(contador).setPuntosPartida(jugadores.get(contador).getPuntosPartida() + 4);
                        hayGanador = true;
                    }

                    System.out.println("MESA: \n");

                    iu.mostrarMensaje(mesa.toString());

                    //ACTUALIZA EL CONTADOR
                    if (contador == jugadores.size() - 1) {
                        contador = 0;
                    } else {
                        contador++;
                    }

                } else {
                    iu.mostrarMensaje("No tienes cartas válidas para echar, se saltará al siguiente jugador.");
                    if (contador == jugadores.size() - 1) {
                        contador = 0;
                    } else {
                        contador++;
                    }
                    iu.mostrarMensaje(mesa.toString());
                }

            } while (!hayGanador);

            baraja = new Baraja();
            mesa = new Mesa();
            for (int i = 0; i < jugadores.size(); i++) {
                if (!jugadores.isEmpty()) {
                    jugadores.get(i).eliminarMano();
                }
            }

            puntosOro += 2;

        } while (!AsOros);

        int aux = 0;
        int j = 0;
        Deque<Jugador> jFinales = new ArrayDeque();
        for (int i = 0; i < jugadores.size(); i++) {
            if (jFinales.isEmpty()) {
                aux = jugadores.get(i).getPuntosPartida();
                jFinales.addFirst(jugadores.get(i));
            } else if (jugadores.get(i).getPuntosPartida() >= aux) {
                aux = jugadores.get(i).getPuntosPartida();
                jFinales.addFirst(jugadores.get(i));
            } else if (jugadores.get(i).getPuntosPartida() < aux) {
                jFinales.addLast(jugadores.get(i));
            }

            iu.mostrarMensaje("La lista de ganadores es", jFinales.toString());

        }

    }

    public int elegirJugador(List<Jugador> jugadores) {
        int posTurno = (int) (Math.random() * jugadores.size());
        iu.mostrarMensaje("El Jugador que empezará la partida es " + jugadores.get(posTurno).getNombre());

        //Devuelvo esto porque lo necesito para el bucle de la partida
        return posTurno;
    }

}
