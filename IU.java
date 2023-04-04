/**
 * Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una entrada/salida en modo texto 
 * Se recomienda una implementación modular.
 */

package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Jugador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class IU {
    private final Scanner teclado;

    public IU() {
            teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    /**
     * Lee un num. de teclado
     * 
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    public int leeNum(String msg) {
        do {
                System.out.print(msg);

                try {
                        return teclado.nextInt();
                } catch (InputMismatchException exc) {
                        teclado.next();
                        System.out.println("Entrada no válida. Debe ser un entero.");
                }
        } while (true);
    }

    public String leeString(String msg) {
        System.out.print(msg);
        return teclado.next();
    }

    public String leeString(String msg, Object... args) {
        System.out.printf(msg, args);
        return teclado.next();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarMensaje(String msg, Object... args) {
        System.out.printf(msg, args);
    }



    public Collection<String> pedirDatosJugadores(){
        Collection<String> jugadores = new ArrayList();
        int numJugadores = 0;
        do{
        numJugadores = leeNum("Cuantos jugadores van a jugar?: ");
        }while(numJugadores != 3 && numJugadores != 4);
        for (int i = 0; i < numJugadores; i++) {
            String nombre;
            nombre = leeString("Como se llamará el jugador?: ");
            jugadores.add(nombre);
        }
        return jugadores;
    }


    public void mostrarJugador(Jugador jugador){
        System.out.println(jugador.getNombre());
        jugador.enseñarMano();
    }

    public void mostrarJugadores(Collection<Jugador> jugadores){
        Iterator<Jugador> it = jugadores.iterator();
        for (int i = 0; i < jugadores.size(); i++) {
            mostrarJugador(it.next());
        }
    }
    
}
