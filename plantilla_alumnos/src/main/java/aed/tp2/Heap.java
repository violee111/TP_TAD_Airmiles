package aed.tp2;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

    private ArrayList<HandleDeHeap> arr;

    public Heap() {
        this.arr = new ArrayList<>();
<<<<<<< HEAD
    }

    public int tamaño() {
        return this.arr.size();
    }

    public boolean estaVacio() {
        return this.arr.isEmpty();
    }
=======
    }// Complejidad: O(1)

    public int tamaño() {
        return this.arr.size();
    }//Complejidad: O(1)

    public boolean estaVacio() {
        return this.arr.isEmpty();
    }//Complejidad: O(1)
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949

    public T consultarMax() {
        if (estaVacio())
            return null;
        return this.arr.get(0).valor;
<<<<<<< HEAD
    }
=======
    }//Complejidad: O(1)
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949

    public T obtener(int i) {
        if (i < 0 || i >= tamaño())
            return null;
        return this.arr.get(i).valor;
<<<<<<< HEAD
    }

    public Handle<T> agregar(T valor) {
        int indice = tamaño();
        HandleDeHeap h_valor = new HandleDeHeap(indice, valor);
        this.arr.add(h_valor); // agrega el handledeheap al array
        siftUp(indice); // reordena si es necesario
        return h_valor; // devuelve el handle para poder acceder después al elemento
    }

    public T desencolar() {
        if (estaVacio())
            return null;
        T maximo = consultarMax(); /* guarda la raiz en la variable maximo */
        eliminarEnHeap(0); /* elimina la raiz, el elemento con mayor prioridad */
        return maximo;
    }

    /* saca al usuario , guarda la direccion donde estaba en el heap */
        public Handle<T> desencolarHandle() {
            if (estaVacio())
                return null;
            Handle<T> handleMaximo = this.arr.get(0); /*handlemaximo guarda la direccion al handledeheap de la posicion 0 */
                                                    
            eliminarEnHeap(0);/* el heap saca la referencia al handleheap 0 de su arraylist y reacomoda */
            return handleMaximo; //*al guardar  en la variable handleMaximo aunque no exista en el heap, la devuelve */
        }

    /* borra el elemento que esta en la posicion i */
=======
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
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949
    public void eliminarEnHeap(int i) {
        if (i < 0 || i >= tamaño())
            return;
        int ultimo = tamaño() - 1;
        swap(i, ultimo);
        this.arr.remove(ultimo);

<<<<<<< HEAD
        if (i < tamaño()) {
            siftUp(i);
            siftDown(i);
        }
    }
=======
        if (i < tamaño()) { //O(1)
            siftUp(i); // O(log n)
            siftDown(i); //O(log n)
        }
    }//Complejidad: O(log n)
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949

    public void modificarEnHeap(int i, T nuevoValor) {
        if (i < 0 || i >= tamaño())
            return;
<<<<<<< HEAD
        this.arr.get(i).valor = nuevoValor;
        siftUp(i);
        siftDown(i);
    }

    private void swap(int i, int j) { /* intercambia la posicion de los elementos de los indices i, j */
=======
        this.arr.get(i).valor = nuevoValor; // O(1)
        siftUp(i); // O(log n)
        siftDown(i); // O(log n)
    }//Complejidad: O(log n)

    private void swap(int i, int j) { // intercambia los HandleDeHeaps en los indices i, j del arraylist 
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949
        HandleDeHeap guardavalor = this.arr.get(i);
        this.arr.set(i, this.arr.get(j));
        this.arr.set(j, guardavalor);

<<<<<<< HEAD
        this.arr.get(i).pos = i;
        this.arr.get(j).pos = j;
    }

    /*
     * evalua, se compara con el padre , si es mayor, swapea y actualiza las
     * posiciones
     */
    private void siftUp(int i) {
        int padre = (i - 1) / 2;
        while (i > 0 && this.arr.get(i).valor.compareTo(this.arr.get(padre).valor) > 0) {
            swap(i, padre);
            i = padre;
            padre = (i - 1) / 2;
        }
    }

    /*
     * evalua, se compara con los hijos, si es menor que alguno , actualiza su
     * posicion
     */
    private void siftDown(int i) {
        int izq = 2 * i + 1;
        int der = 2 * i + 2;
        int mayor = i;
        /*
         * en mayor va a guardar al que tenga posicion mayor, si el izq es mayor, guarda
         * el indice en mayor
         */
        if (izq < tamaño() && this.arr.get(izq).valor.compareTo(this.arr.get(mayor).valor) > 0) {
            mayor = izq; /*
                          * si el valor en indice del hijo izq supero al valor del padre, mayor cambia y
                          * guarda el indice del izq
                          */
        }
        /* se fija si el hijo derecho es mayor al elemento que esta guardado en mayor */
        if (der < tamaño() && this.arr.get(der).valor.compareTo(this.arr.get(mayor).valor) > 0) {
            mayor = der; /* guarda al indice del hijo derecho si es el mayor elemento */
        }

        /*
         * si mi indice inicial i no tiene el mayor valor habiendo comparado con los
         * hijos,swapea de posicion
         */
        if (mayor != i) {
            swap(i, mayor); /*
                             * cuando swapea, el contenido de mayor pasa a la posicion de i, y el contenido
                             * de i pasa a la de mayor
                             */
            siftDown(mayor);/*
                             * ahora en mayor , esta un elemento mas chico, que se va a comparar con los
                             * hijos nuevos que tiene al bajar un nivel
                             */
        }
    }

    /*
     * ==========Clase HandleDeHeap==========================
     */
=======
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
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949

    private class HandleDeHeap implements Handle<T> {
        private int pos;
        private T valor;

        public HandleDeHeap(int pos, T valor) {
            this.pos = pos;
            this.valor = valor;
        }

<<<<<<< HEAD
        /*
         * no se si sacar o no estos getPos y setPos porque lo podria necesitar otra
         * clase externa ¿tal vez? o conviene usar setposicion en el metodo swap?
         */
        public int getPosicion() {
            return this.pos;
        }

        public void setPosicion(int nueva_pos) {
            this.pos = nueva_pos;
        }

=======
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949
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
