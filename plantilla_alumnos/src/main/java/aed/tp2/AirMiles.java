package aed.tp2;

import java.util.ArrayList;

public class AirMiles implements SistemaAirMiles {
    private ABB<Integer, Usuario> usuarios;
    private TriePromociones promociones;
    private Heap<Usuario> rankingHeap;

    private Usuario masAcumulador;
    private Usuario masCanjeador;
    
    // constructor de AirMiles, que inicializa las estructuras vacías en O(1)
    public AirMiles() {
        this.usuarios = new ABB<>();
        this.promociones = new TriePromociones();
        this.rankingHeap = new Heap<>();
        this.masAcumulador = null;
        this.masCanjeador = null;
    }

    //da de alta un usuario nuevo en O(log (U))
    @Override
    public void registrarUsuario(int id) {
        Usuario nuevo = new Usuario(id); // instancia usuario en O(1)
        this.usuarios.agregar_nuevo_usuario(id, nuevo); // lo inserta en el ABB en O(log (U))

        Handle<Usuario> handle = this.rankingHeap.agregar(nuevo); //en cola en el heap en O(log (U))
        nuevo.setHandleHeap(handle); // asocia el handle al usuario en O(1)

        actualizarExtremos(nuevo); // actualiza puneros extremos en O(1)
    }

    //funcion auxiliar para actualizar extremos
    private void actualizarExtremos (Usuario u) {
        if (this.masAcumulador == null || u.getMontoMillasHist() > this.masAcumulador.getMontoMillasHist()){
            this.masAcumulador = u;
        }
        if (this.masCanjeador == null || u.getMillasCanjeadasHist() > this.masCanjeador.getMillasCanjeadasHist()){
            this.masCanjeador = u;
        }
    }

    //da de alta un usuario nuevo con bono de referido en O(log (U))
    // misma forma que registrarUsuario pero con bonoReferido y acreditando millas al referidor
    public void registrarUsuarioConReferidor(int id, int ref) {
        Usuario nuevo = new Usuario(id, 500.0); //registra usuario con bonoReferido
        this.usuarios.agregar_nuevo_usuario(id, nuevo);

        Handle<Usuario> handleDeNuevo = this.rankingHeap.agregar(nuevo); //lo agrega a ABB y lo inserta en el heap, que lo ubixa donde correspone en el handle por las 500 millas
        nuevo.setHandleHeap(handleDeNuevo);

        Usuario referidor = this.usuarios.obtener(ref); // busca el referidor en el ABB
        if (referidor != null) {
            referidor.acreditarMillas(500.0);
            if (referidor.getHandleHeap() != null) {
                referidor.getHandleHeap().modificar(referidor); //cambia saldo del referidor, entonces cambia prioridad en el heap
            }
            actualizarExtremos(referidor);
        }
        actualizarExtremos(nuevo);
    } // en total cuasta O(log U) + O(1) = O(log U) 

<<<<<<< HEAD
public void acumularMillas(int id, double distancia, String nombrePromo) {
    Usuario valor = this.usuarios.obtener(id);  
    double millas_a_sumar = distancia; 

    if (nombrePromo != null) {
        Promocion promo = this.promociones.obtener(nombrePromo);
        if (promo != null) {
            double candidato = distancia * promo.getFactor();

            if (promo.tieneTope()) {
            
                if (promo.getMillasTotales() + candidato <= promo.getTope()) {
                    millas_a_sumar = candidato;
                    promo.sumaMillasTotales(candidato);

                    if (promo.alcanzaTope()) {
                        this.promociones.eliminar(nombrePromo);
                    }
                }
                // Si no entra en el tope millas_asumar se queda igual con distancia
            } else {
                millas_a_sumar = candidato;
            }
        }
    }

    valor.acreditarMillas(millas_a_sumar);

    if (this.masAcumulador == null || valor.getMontoMillasHist() > this.masAcumulador.getMontoMillasHist()) {
        this.masAcumulador = valor;
    }
}

=======
    // acrediat millas por vuelvo teniendo en cuenta las promociones en O(log (U) + |L|)
    public void acumularMillas(int id, double distancia, String nombrePromo) {
        Usuario valor = this.usuarios.obtener(id);  
        double millas_a_sumar = distancia; 

        if (nombrePromo != null) {
            Promocion promo = this.promociones.obtener(nombrePromo); // busca en el Trie en O(log (|L|))
            if (promo != null) {
                double candidato = distancia * promo.getFactor();

                if (promo.tieneTope()) {
                
                    if (promo.getMillasTotales() + candidato <= promo.getTope()) {
                        millas_a_sumar = candidato;
                        promo.sumaMillasTotales(candidato);

                        if (promo.alcanzaTope()) {
                            this.promociones.eliminar(nombrePromo); //remueva la promo del Trie en O(|L|)
                        }
                    }
                    // Si no entra en el tope millas_asumar se queda igual con distancia
                } else {
                    millas_a_sumar = candidato;
                }
            }
        }   

        valor.acreditarMillas(millas_a_sumar);

        if (this.masAcumulador == null || valor.getMontoMillasHist() > this.masAcumulador.getMontoMillasHist()) {
            this.masAcumulador = valor;
        }
    }

    // canjea millas del usuario en O(log(U))
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949
    public void canjearMillas(int id, double cantidad) {
        Usuario u = this.usuarios.obtener(id);
        if (u == null) {
            return;
        }
        //no me di cuenta que puse el mismo nombre, pero este canjearMillas es el implementado en Usuario
        u.canjearMillas(cantidad);
        if (u.getHandleHeap() != null) {
            u.getHandleHeap().modificar(u);
        }
        actualizarExtremos(u);
    }

    // transfiere millas de un usuario a otro en O(log (U))
    public void transferirMillas(int origen, int destino, double cantidad) {
<<<<<<< HEAD
        Usuario dador = this.usuarios.obtener(origen);
        Usuario receptor = this.usuarios.obtener(destino);

=======
        //busca usuarios rn ABB
        Usuario dador = this.usuarios.obtener(origen);
        Usuario receptor = this.usuarios.obtener(destino);

        // descuenta del usuario "origen" y  acredita en usuario "destino" en O(1)
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949
        if ((dador != null && receptor != null && dador != receptor)&& cantidad <= dador.getMillasDisponibles() && cantidad >0) {
            dador.debitarTransferencia(cantidad);
            receptor.acreditarTransferencia(cantidad);
            dador.registrarInteraccionCon(receptor);
            receptor.registrarInteraccionCon(dador);
            }
<<<<<<< HEAD
=======
        // modifica prioridad de usuarios en heap en O(log (U))
            dador.getHandleHeap().modificar(dador);
            receptor.getHandleHeap().modificar(receptor);
>>>>>>> f85140c66d20b07eb8aee66c534ac95c149d5949
    }

    // consulta saldo disponible de millas de un usuario en O(log (U))
    public double consultarSaldo(int id) {
        Usuario u = this.usuarios.obtener(id);
        if (u == null) {
            return 0.0;
        }
        return u.getMillasDisponibles();
    }

    // consulta categoría de un usuario en O(log (U))
    public String consultarCategoria(int id) {
       Usuario u = this.usuarios.obtener(id);
       return u.uConsultarCategoria();
    }


    public int[] rankingMillas(int n) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    // da de alta una promoción SIN tope en el Trie en O(|L|)
    public void iniciarPromocion(String nombre, double factor) {
        Promocion p = new Promocion(nombre, factor);
        this.promociones.insertar(p);
    }

    // da de alta una promocion CON tope en el Trie en O(|L|)
    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        Promocion p = new Promocion(nombre, factor, tope);
        this.promociones.insertar(p);
    }

    // da de baja una promocion activa en el Trie en O(|L|)
    public void finalizarPromocion(String nombre) {
        this.promociones.eliminar(nombre);
    }

    // devuelve el id del usuario con mas millas acumuladas en O(1)
    public int elMasAcumulador() {
        if (this.masAcumulador == null) {
            return 0;
        }
    return this.masAcumulador.getId();
    }

    // devuelve el id del usuario con mas millas canjeadas en O(1)
    public int elMasCanjeador() {
        if (this.masCanjeador == null) {
            return 0;
        }
        return this.masCanjeador.getId();
    }

    // devuelve un arreglo de tuplas con los pares de usuarios exclusivos, es decir, 
    // que se transfieren millas entre sí en O(U)
    public int[][] paresTransferidoresExclusivos() {
           Iterador <Usuario> iterar = this.usuarios.iterar_arbol();
         

          ArrayList <int[]> lista_de_pares = new ArrayList<int[]>();
          
          while (iterar.haySiguiente()) {

              Usuario u = iterar.siguiente();
                if(!u.esTransferidorInvalido() && u.getCompaTransferencia()!=null && !(u.getCompaTransferencia().esTransferidorInvalido()) && u.getCompaTransferencia().getCompaTransferencia() == u){ // con esto verifico todo lo necesesario para saber que mis usuarios son ambos transferidoores validos y existen 
                   
                  if (u.getId()< u.getCompaTransferencia().getId()) { // filtro las tuplas de esta forma me aseguro de que no halla repetidos (a,b)y (b,a).
                      
                   int[] tupla = new int[]{(u.getId()),(u.getCompaTransferencia().getId())};
                   lista_de_pares.add(tupla);
                  }
                }
          }
          
          int[][] resultado = new int[lista_de_pares.size()][2]; // tengo que pasar todo a una lsita simple porque sino tengo incompatibilidad de tipos 
          for (int i = 0 ; i <lista_de_pares.size(); i++) {
             resultado[i] = lista_de_pares.get(i); 
          } 

          
         return (resultado);
        }
}
