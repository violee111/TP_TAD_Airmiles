package aed.tp2;
public class ABB<K extends Comparable<K> , V> {
// el ABB tiene a K y V porque lo que se va a guardar es un diccionario

private  class Nodo { // tiene que estar privado para evitar modificaciones en las caracteristicas de mi clase
 K clave; // es el id del usuario
 V valor; // es el usuario 
 Nodo padre;
 Nodo izquierda;
 Nodo derecha;

 public Nodo (K clave , V valor){

    this.clave = clave;
    this.valor = valor;
    this.padre = null;
    this.izquierda = null;
    this.derecha = null;
 }

}

private Nodo raiz;
private int cantidad;

public ABB (){
    this.raiz = null;
    this.cantidad= 0;
}

// aca vamos a recorrer el arbol desde la raiz para ver si la clave es decir si el usuario ya existia.
// vamos bajando por izquierda o derecha segun corresponda. si recorrio todo el arbol y nunca encontro ningun conincidencia entonces da false
public boolean  pertenece_usuario (K clave) {
    Nodo actual = this.raiz;

    while (actual != null) {
       if (actual.clave.compareTo(clave) > 0) {// significa que actual.dato es mayor que elem
          actual = actual.izquierda;
       } 
       else if (actual.clave.compareTo(clave)<0){
        actual = actual.derecha;
       }
       else {
        return true;
       }
    }

    return false;
}
//nos devulve la cantidad de usuarios que hay dentro del arbol
public int cantidad_de_usuarios_en_sistema (){
    return (this.cantidad);
}

//aca incertamos un nuevo usuarioen el ABB pero para eso el usuario no debe de estar repetido.
//si el arbol esta vacio pasa a ser la raiz del arbol.Si no, busco cuál va a ser su nodo padre 
// bajando por las ramas, lo engancho a izquierda o derecha según la clave
public void agregar_nuevo_usuario (K clave, V valor){

    Nodo nuevo_usuario = new Nodo (clave , valor); // defino el nodo que va a ingresar.
    Nodo actual = this.raiz;
    Nodo padre = null;
    
    if (! pertenece_usuario (clave)){
    if (this.raiz == null){
        this.raiz = nuevo_usuario;
    }
    else{
          // tengo que buscar el nodo padre del que va a ser mi nuevo usuario
          while (actual!=null) { // cuando actual sea igual a null significa que el nodo que esta por arriva de ese null es el padre del nuevo usuario a agregar 
             padre = actual;
             if (actual.clave.compareTo(clave)>0) {
               actual = actual.izquierda; 
             } 
             else { // no va a ocurrir el caso de = 0 porque ya me asegure de que la K no pertnecesca 
                 actual = actual.derecha;
             }
          }

          if (padre.clave.compareTo(clave)>0) {
            padre.izquierda = nuevo_usuario;
            nuevo_usuario.padre = padre;
          }
          else{
            padre.derecha = nuevo_usuario;
            nuevo_usuario.padre = padre;
          }
     }
     this.cantidad = this.cantidad+1;

   }
 }
// Busca al usuario por su clave igual que en pertenece, pero en vez de tirar un booleano 
// me devuelve el valor asociado a esa clave.
public V obtener (K clave){
    Nodo actual = this.raiz;

    while (actual !=null) {
        if (actual.clave.compareTo(clave)>0) {
           actual = actual.izquierda;            
        }
        else if (actual.clave.compareTo(clave)<0){
            actual = actual.derecha;
        }
        else{
            return actual.valor;
        }
    
     }
    return (null);
   }

   //se instnacia el iterador para poder recorrer los elementos del árbol de menor a mayor.
public Iterador<V> iterar_arbol (){ // instancio- creo el iterado dentro de esta clase 
      return new  ABB_iterador() ; 
   }

   public class ABB_iterador implements  Iterador<V>{
    private Nodo actual;

// Constructor del iterador: arranco en la raíz y bajo todo a la izquierda 
// para pararme en el valor más chico de todo el árbol
    public ABB_iterador(){
        this.actual = raiz;
        if (this.actual !=null) {
        while (this.actual.izquierda!= null) {
          this.actual = this.actual.izquierda; // comienza actual valiendo siempre el nodo mas a la izquierda
        }
      }

   }

// Chequea si todavía quedan nodos por recorrer.
   @Override 
   public boolean haySiguiente(){
    return this.actual !=null; // si es igual a null significa que no tiene siguientes
   }

// Devuelve el valor del nodo actual y avanza el puntero al siguiente 
// Si tengo subárbol derecho, voy a la derecha y bajo todo a la izquierda para buscar el mínimo.  
  @Override // Si no hay derecha, subo hasta encontrar de cuál venía por izquierda.
  public V siguiente(){
    V res = this.actual.valor;

    if (this.actual.derecha != null) {
         this.actual = this.actual.derecha;
         while (this.actual.izquierda!=null){ // bajamos todo lo posible para encontrar el minimo de actual.derecha si lo tiene
            this.actual = this.actual.izquierda;
         }        
    }
    else{//si no hay hijo derecho eso significa que no hay mas elementos(nodos) tenemos que subir
      
        while (this.actual.padre !=null && this.actual.padre.derecha == this.actual ){
         this.actual = this.actual.padre;

        } // si el nodo es un nodo de izquierda y no tiene derecha entonces se avanza con su padre (se sube un nodo)
        this.actual = this.actual.padre;
    }
    return res; 
  }
  @Override
    public boolean hayAnterior() {
        return false;
    }

    @Override
    public V anterior() {
        return null;
    }


 }




}