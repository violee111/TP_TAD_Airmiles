package aed.tp2;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private double millasDisponibles;
    private double montoMillasHist;
    private double millasCanjeadasHist;

    //para manejo de transferencia s exclusivas (complejidad(1))
    private Usuario compaTransferencia; //cmabiar nombre, no se me ocurrio algo mejor
    private boolean huboTransferencia;
    private boolean esTransferidorInvalido;

    private Handle<Usuario> handleHeap; // para rankingMillas

    public Usuario (int id) {
        this.id = id;
        this.millasDisponibles = 0.0;
        this.montoMillasHist = 0.0;
        this.millasCanjeadasHist = 0.0;
        this.compaTransferencia = null;
        this.huboTransferencia = false;
        this.esTransferidorInvalido = false;
    }

    public Usuario(int id, double bonoReferido) {
        this(id);
        if (bonoReferido > 0.0) {
            this.millasDisponibles = bonoReferido;
            this.montoMillasHist = bonoReferido;
        }
    }

    public int getId() {
        return this.id;
    }

    public double getMillasDisponibles() {
        return this.millasDisponibles;
    }

    public double getMontoMillasHist() {
        return this.montoMillasHist;
    }

    public double getMillasCanjeadasHist() {
        return this.millasCanjeadasHist;
    }

    public Handle<Usuario> getHandleHeap() {
        return this.handleHeap;
    }

    public void setHandleHeap(Handle<Usuario> handle) {
        this.handleHeap = handle;
    }

    //para acreditar millas por vuelos o bonos de promociones (complejidad(1))
    public void acreditarMillas(double cant) {
        this.millasDisponibles += cant;
        this.montoMillasHist += cant;
    }

    //para canjear millas que el usuario tiene disponibles (copmlejidad O(1))
    public void canjearMillas(double cant) {
        this.millasDisponibles -= cant;
        this.millasCanjeadasHist += cant;
    }

    //descontar millas cuando se hace una transferencia enviada (complejidad O(1))
    public void debitarTransferencia(double cant) {
        this.millasDisponibles -= cant;
    }

    //para registrar interaccion entre usuarios (interaccion = alguna transferencia)
    public void registrarInteraccionCon(Usuario otro) {
        this.huboTransferencia = true;
        if (this.esTransferidorInvalido) {
            return;
        }
        if (this.compaTransferencia == null) {
            this.compaTransferencia = otro;
        } else if (this.compaTransferencia.getId() != otro.getId()) {
            this.esTransferidorInvalido = true;
            this.compaTransferencia = null;
        }
    }

    public Usuario getCompaTransferencia() {
        return this.compaTransferencia;
    }

    public boolean esTransferidorInvalido() {
        return this.esTransferidorInvalido;
    }

    //devuelve categoria segun millas disponibles (complejidad O(1))
    public String consultarCategoria() {
        if (this.millasDisponibles < 10000.0) {
            return "Bronce";
        } else if (this.millasDisponibles < 50000.0) {
            return "Plata";
        } else {
            return "Oro";
        }
    }

    @Override
    public int compareTo(Usuario otro) {
        if (Double.compare(this.millasDisponibles, otro.millasDisponibles) != 0) {
            return Double.compare(this.millasDisponibles, otro.millasDisponibles);
        } else {
            return Integer.compare(this.id, otro.id);
        }
    }
}
