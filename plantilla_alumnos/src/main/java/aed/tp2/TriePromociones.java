package aed.tp2;

public class TriePromociones {

    private static final int cant_letras_ascii = 256;

    private static class NodoTrie {
        Promocion promo;
        boolean esClave;
        NodoTrie[] hijos;
        int cantDeHijos;

        NodoTrie() {
            this.promo = null;
            this.esClave = false; // es una bandera que me dice que el camino que recorri con los nodos hasta el ultimo forma una promocion 
            this.hijos = new NodoTrie[cant_letras_ascii];
            this.cantDeHijos = 0;
        }
    }

    private NodoTrie raiz;
    private int tamaño;

    public TriePromociones() {
        this.raiz = new NodoTrie();
        this.tamaño = 0;
    }

    public boolean estaVacio() {
        return this.tamaño == 0;
    }

    //para buscar promocion por nombre -> complejidad en peor caso O(|L|)
    public Promocion obtener(String nombre) {
        if (nombre == null)
            return null;

        NodoTrie actual = this.raiz;
        for (int i = 0; i < nombre.length(); i += 1) {
            int n = (int) nombre.charAt(i);
            if (n < 0 || n >= cant_letras_ascii || actual.hijos[n] == null) {
                return null;
            }
            actual = actual.hijos[n];
        }
        if (actual.esClave) {
            return actual.promo;
        }
        return null;
    }
    public void insertar (Promocion  p){
        String nombre_promo = p.getNombre();
        NodoTrie actual = this.raiz;

        for (int i = 0; i<nombre_promo.length(); i++) {
            
            int n = (int) nombre_promo.charAt(i);

            if (actual.hijos[n] == null) {
                actual.hijos[n] = new NodoTrie(); //instancio el nodo que va a estar en la posicion n reprecentando al caracter i del nombre_promo 
                actual.cantDeHijos ++;         
            }

            actual = actual.hijos[n]; // paso al siguiente nodo para continuar con el for
        }
        if (!actual.esClave) { // me fijo que la promocion que me mandaron no sea una que ya estaba guardada
            actual.esClave = true; // como no estaba guardada es una promocion nueva entonces se aumenta el tamaño del trie y se actualiza esClave.
            this.tamaño = this.tamaño +1; // el tamano aumenta una ves que ya agregaste una nueva palabra
        }

        actual.promo = p;
    }
    public void eliminar (Promocion p){
        if (p == null || p.getNombre() == null) { 
            return;
        }
        String clave = p.getNombre();
        this.raiz = eliminarRecursivo (this.raiz,clave, 0); // uso una funcion recursiva para recorrer nodo por nodo 
 }
    public NodoTrie eliminarRecursivo(NodoTrie actual, String clave, int i) {

        if (actual == null) { // el primer caso es que actual sea null en ese caso retorno eso porque ya no tengo ninun nodo que analizar o buscar
            return (actual);
        }

        if (i == clave.length()) { // esto va a ocurrir ya cuando llegamos al final del nodo y encontramos el ultimo nodo que seria el que reprecenta la ultima palabra de la promocion
            if (actual.esClave) {
               actual.esClave = false;
               actual.promo = null;
               this.tamaño = this.tamaño -1 ;
              }      
                    
          }

        else{
              
             int n = (int) clave.charAt(i);
             if (actual.hijos[n] == null) { // si pasa esto es xq nunca estuvo la palabra actual en el sistema para borrar
                 return actual;
                 }

            actual.hijos[n] = eliminarRecursivo(actual.hijos[n], clave, i+1); // aplico el caso recursivo 

            if (actual.hijos[n] == null) {// si el hijo se borro entonces restamos un hijo
               actual.cantDeHijos--; 
            }
        }


        if (!actual.esClave && actual.cantDeHijos == 0 && i>0) { // si no es clave y ademas no tiene hijos se lo va a borrar
            return null;
        }
         return (actual);
 }

    // para verificar si una promocion existe en elsistema -> complejiddad den el peor caso O(|L|)
    public boolean pertenece(String nombre) {
        return obtener(nombre) != null;
    }


}
