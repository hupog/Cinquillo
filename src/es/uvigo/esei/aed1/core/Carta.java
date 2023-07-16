/*
 * Representa una carta, formada por un número y su palo correspondiente
 */


package es.uvigo.esei.aed1.core;

public class Carta {
    
    
    enum Palo {
        Bastos,Espadas,Copas,Oros
    }
    private Palo palo;
    
    
    //private String palo;
    private int numero; 

    /**
     *
     * @param palo
     * @param numero
     */
    public Carta(Palo palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(numero);
        sb.append(" de ");
        sb.append(palo);
        return sb.toString();
    }
}
