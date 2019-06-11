package Classes;

import javafx.util.Pair;

public class NodoIP {

    private Pair<String, Integer> Actual, Anterior, Siguiente;

    public NodoIP() {
    }

    public Pair<String, Integer> getActual() {
        return Actual;
    }

    public void setActual(Pair<String, Integer> Actual) {
        this.Actual = Actual;
    }

    public Pair<String, Integer> getAnterior() {
        return Anterior;
    }

    public void setAnterior(Pair<String, Integer> Anterior) {
        this.Anterior = Anterior;
    }

    public Pair<String, Integer> getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(Pair<String, Integer> Siguiente) {
        this.Siguiente = Siguiente;
    }

    

}
