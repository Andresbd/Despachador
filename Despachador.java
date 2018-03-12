import java.io.*;

public class Despachador {

    public stattic void main(String []args) {

        for (i=0;i<=5;i++) {
            Proceso p = new Proceso(i);
            p.Prioridad(5);
            p.Tiempo(20);
            p.printProceso();
        }
    }
}