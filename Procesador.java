/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package despachador;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author a_lin
 */
public class Procesador implements Comparable {

    public int tiempoFinal, id;

    public LinkedList<Terminado> terminado = new LinkedList<Terminado>();

    public int getTiempo() {
        return tiempoFinal;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Procesador) {
            return ((Procesador) o).tiempoFinal - tiempoFinal;
        }
        return 0;
    }

    @Override
    public String toString() {
        return " Tiempo = " + tiempoFinal;
    }

    class SortbyTF implements Comparator<Procesador> {

        @Override
        public int compare(Procesador t, Procesador t1) {
            return t.tiempoFinal - t1.tiempoFinal;
        }

    }
}
