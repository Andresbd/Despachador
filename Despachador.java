package despachador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

/**
 *
 * @author Aldo
 */
public class Despachador extends javax.swing.JFrame {

    public static int TCC, TB, PRO, CUA;
    public static List<Proceso> proList = new LinkedList<Proceso>();
    public static LinkedList<Procesador> pQueue = new LinkedList<Procesador>();

    public static void ejecucion(Proceso p, Procesador m) {

        int TE, TVC, TT, tb;
        int tcc = TCC;
        int TI;
        int GAP;

        TE = p.ms;
        TVC = TE / CUA;
        if (TE % CUA == 0) {
            TVC--;
            TVC = TVC * 10;
        }
        if (TVC == 1) {
            TVC = 10;
        }
        tb = p.bloqueo * TB;
                
        
        if (p.tLlegada >= m.tiempoFinal) {
            Terminado t = new Terminado();
            tcc = 0;
            t.TB = tb;
            t.TI = p.tLlegada;
            t.TE = TE;
            t.TVC = TVC;
            TT = tcc + TE + TVC + tb;
            t.TF = t.TI + TT;
            t.TT = TT;
            t.GAP = t.TI - m.tiempoFinal;
            m.tiempoFinal = t.TF;
            t.nommbre = p.nombre;
            m.terminado.add(t);
            System.out.printf("%s   %d   %d   %d   %d   %d   %d   %d   %d\n", t.nommbre, tcc, t.TE, t.TVC, t.TB, t.TT, t.TI, t.TF, t.GAP);
        }
        else {

        Terminado t = new Terminado();
        tcc = TCC;
        t.TB = tb;
        t.TE = TE;
        t.TVC = TVC;
        TT = tcc + TE + TVC + tb;
        t.TT = TT;
        t.TI = m.tiempoFinal;
        t.TF = t.TI + TT;
        m.tiempoFinal = t.TF;
        t.nommbre = p.nombre;
        t.GAP = 0;
        m.terminado.add(t);
        System.out.printf("%s   %d   %d   %d   %d   %d   %d   %d   %d\n", t.nommbre, tcc, t.TE, t.TVC, t.TB, t.TT, t.TI, t.TF, t.GAP);
        }
    }

    //Recibimos valores de los procesos; Nombre, MS, Bloqueos
    public static void getData() {

        int proID = 0;

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("C:\\Users\\a_lin\\Documents\\Sistemas Operativos\\SO.A01172912.Despachador\\Despachador\\src\\procesos.txt"));
            String read = null;
            while ((read = in.readLine()) != null) {
                String[] splited = read.split("\\s+");
                for (String part : splited) {

                    Proceso p = new Proceso();

                    String[] res = part.split(";");
                    p.setNombre(res[0]);
                    int mili = Integer.parseInt(res[1]);
                    p.setMS(mili);
                    int block = Integer.parseInt(res[2]);
                    p.setBloqueo(block);
                    int inicioProceso = Integer.parseInt(res[3]);
                    p.setTLlegada(inicioProceso);
                    proList.add(p);
                    p.id = proID;
                    proID++;
                }
            }
        } catch (IOException e) {
            System.out.println("There was a problem: " + e);
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }

    public static void splitProcesors() {

        System.out.println("Nombre  TCC  TE  TVC  TB  TT  TI  TF  GAP");
        for (int z = 0; z < proList.size(); z++) {
            Procesador p = new Procesador();
            p = pQueue.remove();
            ejecucion(proList.get(z), p);
            pQueue.add(p);
            ordenarLista(pQueue);
        }
        showResults(pQueue);
    }
    
    public static void showResults(LinkedList<Procesador>pQueue1){
        for(int i=0; i<=pQueue1.size();i++){
            Procesador p = new Procesador();
            p=pQueue1.remove();
            LinkedList done = new LinkedList();
            done = p.terminado;
            System.out.println("El procesador: " +p.id+ " tiene los siguientes procesos");
            System.out.println("Nombre  TCC  TE  TVC  TB  TT  TI  TF  GAP");
            for(int x=0; x<=done.size(); x++){
                Terminado t = new Terminado();
                t=(Terminado)done.remove();
                System.out.printf("%s   %d   %d   %d   %d   %d   %d   %d   %d\n", t.nommbre, TCC, t.TE, t.TVC, t.TB, t.TT, t.TI, t.TF, t.GAP);            
            }
        }
    }

    public static void ordenarLista(LinkedList pQueue) {
        for (int i = 0; i < pQueue.size() - 1; i++) {

            int temp = 0;

            Procesador p = (Procesador) pQueue.get(i);
            int a = p.tiempoFinal;
            if (pQueue.size() > 1) {
                Procesador q = (Procesador) pQueue.get(i + 1);
                int b = q.tiempoFinal;

                if (a > b) {
                    temp = a;
                    p.tiempoFinal = b;
                    q.tiempoFinal = temp;
                }
            }
        }
    }

    public static void generarMicro() {

        for (int i = 0; i < PRO; i++) {

            Procesador m = new Procesador();
            m.tiempoFinal = 0;
            m.id = i;
            pQueue.add(m);
        }
    }

    public static void main(String[] args) {

        getData();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el tiempo de bloqueo: ");
        TB = scanner.nextInt();

        System.out.print("Introduce el número de procesadores: ");
        PRO = scanner.nextInt();

        System.out.print("Introduce el tamaño de cuantum: ");
        CUA = scanner.nextInt();

        System.out.print("Introduce el cambio de contexto: ");
        TCC = scanner.nextInt();

        generarMicro();

        splitProcesors();

        System.out.println(pQueue);
    }
}
