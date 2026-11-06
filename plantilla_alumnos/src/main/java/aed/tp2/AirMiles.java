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
        this.usuarios.agregar_nuevo_ususario(id, nuevo);

        Handle<Usuario> handle = this.rankingHeap.agregar(nuevo);
        nuevo.setHandleHeap(handle);

        actualizarExtremos(nuevo);
    }

    //funcion auxiliar para actualizar extremos
    private void actualizarExtremos (Usuario u) {
        if (this.masAcumulador == null || u.getMillasTotalesHistoricas() > this.masAcumulador.getMillasTotalesHistoricas()){
            this.masAcumulador = u;
        }
        if (this.masCanjeador == null || u.getMillasTotalesCanjeadas() > this.masCanjeador.getMillasTotalesCanjeadas()){
            this.masCanjeador = u;
        }
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void canjearMillas(int id, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public double consultarSaldo(int id) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public String consultarCategoria(int id) {
       throw new UnsupportedOperationException("Sin implementar");
    }

    public int[] rankingMillas(int n) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocion(String nombre, double factor) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void finalizarPromocion(String nombre) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasAcumulador() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasCanjeador() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int[][] paresTransferidoresExclusivos() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
