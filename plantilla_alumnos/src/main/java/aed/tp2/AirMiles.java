package aed.tp2;

public class AirMiles implements SistemaAirMiles {
    private ABB<Integer, Usuario> usuarios;
    private TriePromociones promociones;
    private Heap<Usuario> rankingHeap;

    private Usuario masAcumulador;
    private Usuario masCanjeador;
    
    public AirMiles() {
        this.usuarios = new ABB<>();
        this.promociones = new TriePromociones();
        this.rankingHeap = new Heap<>();
        this.masAcumulador = null;
        this.masCanjeador = null;
    }

    @Override
    public void registrarUsuario(int id) {
        Usuario nuevo = new Usuario(id);
        this.usuarios.agregar_nuevo_usuario(id, nuevo);

        Handle<Usuario> handle = this.rankingHeap.agregar(nuevo);
        nuevo.setHandleHeap(handle);

        actualizarExtremos(nuevo);
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

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void canjearMillas(int id, double cantidad) {
        Usuario u = this.usuarios.obtener(id);
        if (u == null) {
            return;
        }
        u.canjearMillas(cantidad);
        if (u.getHandleHeap() != null) {
            u.getHandleHeap().modificar(u);
        }
        actualizarExtremos(u);
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public double consultarSaldo(int id) {
        Usuario u = this.usuarios.obtener(id);
        if (u == null) {
            return 0.0;
        }
        return u.getMillasDisponibles();
    }

    public String consultarCategoria(int id) {
       Usuario u = this.usuarios.obtener(id);
       return u.uConsultarCategoria();
    }


    public int[] rankingMillas(int n) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocion(String nombre, double factor) {
        Promocion p = new Promocion(nombre, factor);
        this.promociones.insertar(p);
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        Promocion p = new Promocion(nombre, factor, tope);
        this.promociones.insertar(p);
    }

    public void finalizarPromocion(String nombre) {
        this.promociones.eliminar(nombre);
    }

    public int elMasAcumulador() {
        if (this.masAcumulador == null) {
            return 0;
        }
    return this.masAcumulador.getId();
    }

    public int elMasCanjeador() {
        if (this.masCanjeador == null) {
            return 0;
        }
        return this.masCanjeador.getId();
    }

    public int[][] paresTransferidoresExclusivos() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
