/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package despachador;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author a_lin
 */
public class Proceso {

    String nombre;
    int id, ms, bloqueo, tLlegada;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nom) {
        nombre = Nom;
    }

    public int getMs() {
        return ms;
    }

    public void setMS(int aMs) {
        ms = aMs;
    }

    public int getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(int blo) {
        bloqueo = blo;
    }
    
    public int getTLlegada(){
        return tLlegada;
    }
    
    public void setTLlegada(int tArrives){
        tLlegada = tArrives;
    }

    @Override
    public String toString() {
        return "MS = " + ms + " Bloqueo = " + bloqueo + "Inicializacion = " + tLlegada;
    }

    class Sortbyms implements Comparator<Proceso> {

        @Override
        public int compare(Proceso t, Proceso t1) {
            return t.ms - t1.ms;
        }

    }

    public void MAX(LinkedList proList) {

        Collections.sort(proList, new Sortbyms());

    }
}

