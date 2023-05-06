/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uvigo.esei.aed1.core;

import java.util.*;

/**
 *
 * @author roimo
 */
public class Mesa {
    
    //private List<Deque<Carta>> mesa;
    private final Deque<Carta>[] mesa;
    
//    public Mesa(){
//        mesa = new ArrayList(); 
//    }
    
    public Mesa() {
       this.mesa = new Deque[4];
       for (int i = 0; i < mesa.length; i++) {
           mesa[i] = new ArrayDeque<>();
       }
    }
    
    
    
    public boolean cartaValida(Carta c){
        
        int pos = c.getPalo().ordinal();
        if(mesa[pos].isEmpty() && c.getNumero() == 5){
            return true;
        }else return !mesa[pos].isEmpty() && (mesa[pos].getFirst().getNumero() == c.getNumero()+1 || c.getNumero() -1 == mesa[pos].getLast().getNumero());
        
        
    }
    
    
//    public boolean cartaValidas(Carta c) {
//        boolean five = false;
//        if (c.getNumero() == 5) {
//            five = true;
//        }
//        if (mesa.isEmpty()) {
//            return five;
//        } else if (five) {
//
//            return five;
//        }
//        for (int i = 0; i < mesa.size(); i++) {
//            if (mesa.get(i).getFirst().getPalo().equals(c.getPalo())) {
//                return mesa.get(i).getLast().getNumero() == c.getNumero() - 1 || mesa.get(i).getFirst().getNumero() == c.getNumero() + 1;
//            }
//
//        }
//        return false;
//    }
    
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        
//        for(int i = 12; i > 0; i--){
//            for(int j = 0;j < mesa.size();j++){
//                if(mesa.get(j).getLast().getNumero() == i){
//                    Carta aux = mesa.get(j).removeLast();
//                    sb.append(aux.toString()).append("\t\t");
//                    mesa.get(j).addFirst(aux);
//                }else{
//                    sb.append("X\t\t\t");
//                }
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesa: \n");
        for (Deque<Carta> palo : mesa) {
            for (Carta c: palo) {
                sb.append(c.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
    
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        
//        for (Deque<Carta> mesa1 : mesa) {
//            for (int j = 12; j > 0; j--) {
//                if (mesa1.getLast().getNumero() == j) {
//                    Carta aux = mesa1.removeLast();
//                    sb.append(aux.toString()).append("\t\t");
//                    mesa1.addFirst(aux);
//                } else {
//                    sb.append("X\t\t\t");
//                }
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
    
//    public String toString2(){
//        StringBuilder sb = new StringBuilder();
//        
//        for(int i = 0; i < 4; i++){
//            mesa[i].getFirst().toString();
//            
//        }
//        
//        return sb.toString();
//    }
    
    
    public void insertarCarta (Carta c){
        int pos = c.getPalo().ordinal();
        if(cartaValida(c)){
            if (c.getNumero() <= 5) {
                mesa[pos].addFirst(c);
            } else {
                mesa[pos].addLast(c);
            }
        }
    }
    
//    public void insertarCarta(Carta c,Jugador j) {
//        
//        boolean five = false;
//        if (c.getNumero() == 5) {
//            five = true;
//        }
//        if (mesa.isEmpty() && five) {
//            Deque<Carta> aux = new ArrayDeque<>();
//            aux.add(c);
//            mesa.add(aux);
//            j.removeCarta(c);
//        } else {
//            if (!five) {
//                for (int i = 0; i < mesa.size(); i++) {
//                    if (mesa.get(i).getFirst().getPalo().equals(c.getPalo())) {
//                        if (c.getNumero() > 5) {
//                            mesa.get(i).addLast(c);
//                            j.removeCarta(c);
//                        } else {
//                            mesa.get(i).addFirst(c);
//                            j.removeCarta(c);
//                        }
//                    }
//                }
//
//            } else {
//                Deque<Carta> aux = new ArrayDeque<>();
//                aux.add(c);
//                mesa.add(aux);
//                j.getMano().remove(c);
//            }
//        }
//    }
}
