public class Proceso {

    int id, prioridad, tiempo;

    public Proceso(int id) {
        this.id = id;
    }

    public void Prioridad(int prio) {
        prioridad = prio;
    }

    public void Tiempo(int ti) {
        tiempo = ti;
    }

    public void printProceso() {
        System.out.println(prioridad);
        System.out.println(tiempo);
    }
}