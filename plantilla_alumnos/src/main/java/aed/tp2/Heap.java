package aed.tp2;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

    private ArrayList<HandleDeHeap> arr;

    public Heap() {
        this.arr = new ArrayList<>();
    }// Complejidad: O(1)

    public int tamaño() {
        return this.arr.size();
    }//Complejidad: O(1)

    public boolean estaVacio() {
        return this.arr.isEmpty();
    }//Complejidad: O(1)

    public T consultarMax() {
        if (estaVacio())
            return null;
        return this.arr.get(0).valor;
    }//Complejidad: O(1)

    public T obtener(int i) {
        if (i < 0 || i >= tamaño())
            return null;
        return this.arr.get(i).valor;
    }//Complejidad: O(1)

    public Handle<T> agregar(T valor) {
        int indice = tamaño();//O(1)
        HandleDeHeap h_valor = new HandleDeHeap(indice, valor);//O(1)
        this.arr.add(h_valor); // O(1) amortizado, agrega el handledeheap al arrayList en la ultima posición
        siftUp(indice); //O(log n), reordena cuando un nodo entró o su prioridad aumenta
        return h_valor; //O(1) devuelve el handle para poder acceder después al elemento
    }//Complejidad: O(log n)

    public T desencolar() {
        if (estaVacio()) //O(1)
            return null;
        T maximo = consultarMax(); // O(1) guarda la raiz en la variable maximo
        eliminarEnHeap(0); //O(log n) elimina la raiz y reordena
        return maximo; //O(1)
    }//Complejidad: O(log n)

    
    public Handle<T> desencolarHandle() {
        if (estaVacio()) // O(1)
            return null;
        Handle<T> handleMaximo = this.arr.get(0); // O(1), guarda la referencia al handledeheap de la raíz
                                                    
        eliminarEnHeap(0);// O(log n), saca la referencia al handleheap del índice 0 y reordena 
        return handleMaximo; // O(1) devuelve el Handle desencolado , que está fuera del heap
    }//Complejidad: O(log n)


    //Borra el elemento que esta en la posicion i */
    public void eliminarEnHeap(int i) {
        if (i < 0 || i >= tamaño())
            return;
        int ultimo = tamaño() - 1;
        swap(i, ultimo);
        this.arr.remove(ultimo);

        if (i < tamaño()) { //O(1)
            siftUp(i); // O(log n)
            siftDown(i); //O(log n)
        }
    }//Complejidad: O(log n)

    public void modificarEnHeap(int i, T nuevoValor) {
        if (i < 0 || i >= tamaño())
            return;
        this.arr.get(i).valor = nuevoValor; // O(1)
        siftUp(i); // O(log n)
        siftDown(i); // O(log n)
    }//Complejidad: O(log n)

    private void swap(int i, int j) { // intercambia los HandleDeHeaps en los indices i, j del arraylist 
        HandleDeHeap guardavalor = this.arr.get(i);
        this.arr.set(i, this.arr.get(j));
        this.arr.set(j, guardavalor);

        this.arr.get(i).pos = i; // actualiza las posiciones de los handledeheaps que se intercambiaron
        this.arr.get(j).pos = j;
    }//Complejidad: O(1)

    // siftUp lo usamos cuando un nodo aumenta su prioridad o reciÉn entra
    private void siftUp(int i) {
        int padre = (i - 1) / 2;
        while (i > 0 && this.arr.get(i).valor.compareTo(this.arr.get(padre).valor) > 0) {// O(log n)
            swap(i, padre); // O(1)
            i = padre;
            padre = (i - 1) / 2;
        }
    }//Complejidad O(log n)



    // siftDown lo usamos cuando un nodo baja su prioridad o al mandar el ultimo elemento a la raiz
    private void siftDown(int i) {
        int izq = 2 * i + 1; 
        int der = 2 * i + 2;  
        int mayor = i; //Asume inicialmente que el padre tiene el valor máximo

        //Si el hijo izquierdo existe y es mayor que el valor actual en mayor, actualiza el índice
        if (izq < tamaño() && this.arr.get(izq).valor.compareTo(this.arr.get(mayor).valor) > 0) {
            mayor = izq; //O(1)
        }
        
        if (der < tamaño() && this.arr.get(der).valor.compareTo(this.arr.get(mayor).valor) > 0) {
            mayor = der; // guarda al indice del hijo derecho si es el máximo encontrado hasta ahora
        }

        //Si el nodo actual no es el mayor de los 3,  intercambia con el hijo más grande y sigue bajando
        if (mayor != i) {
            swap(i, mayor); // el elemento más chico pasa a la posición 'mayor' 
            siftDown(mayor); // Sigue evaluando recursivamente desde la nueva posición
        }
    } //Complejidad: O(log n) 

    /* ====================================================================================
     *                  Clase HandleDeHeap
     * ====================================================================================
    */

    private class HandleDeHeap implements Handle<T> {
        private int pos;
        private T valor;

        public HandleDeHeap(int pos, T valor) {
            this.pos = pos;
            this.valor = valor;
        }

        @Override
        public T valor() {
            return this.valor;
        }

        @Override
        public void eliminar() {
            eliminarEnHeap(this.pos);
        }

        @Override
        public void modificar(T nuevoValor) {
            modificarEnHeap(this.pos, nuevoValor);
        }

        @Override
        public boolean equals(Handle<T> otro) {
            if (this == otro)
                return true;
            if (otro == null)
                return false;
            T thisValor = this.valor();
            T otroValor = otro.valor();
            if (thisValor == null)
                return otroValor == null;
            return thisValor.equals(otroValor);
        }
    }
}
