import java.io.*;

public class Despachador {

    public static void main(String []args) {

            Proceso p = new Proceso(1);
            p.Prioridad(5);
            p.Tiempo(20);
            p.printProceso();
    }
}