package aed.tp2;

public class Heap<T> {
    private T[] elems;
    private HandleDeHeap<T>[] handles;
    private int cant;
    private static final int tamaño_inicial = 16;

    public int tamaño() {
        return this.cant;
    }

    public boolean estaVacio() {
        return this.cant == 0;
    }

    public T consultarMax() {
        if (estaVacio())
            return null;
        return this.elems[0];
    }

    public Handle<T> agregar( T valor) {
        int indice = this.cant;
        HandleDeHeap<T>
    }
}
