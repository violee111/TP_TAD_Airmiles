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

    // constructor del usuaeio con millas iniciales en 0
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

    // devuelve el id del usuario en O(1)
    public int getId() {
        return this.id;
    }
    
    // devuelve las millas disponibles del usuario en O(1)
    public double getMillasDisponibles() {
        return this.millasDisponibles;
    }

    // devuelve el monto total de millas acumuladas por el usuario en O(1)
    public double getMontoMillasHist() {
        return this.montoMillasHist;
    }

    // devuelve el monto total de millas canjeadas (historicamente) por el usuario en O(1)
    public double getMillasCanjeadasHist() {
        return this.millasCanjeadasHist;
    }

    // devuelve el handle del usuario en el heap de ranking en O(1)
    public Handle<Usuario> getHandleHeap() {
        return this.handleHeap;
    }

    // conecta el handle del heap al usuario en O(1)
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

    //para acreditar millas recibidas en una transferencia (complejidad O(1))
    public void acreditarTransferencia(double  cant){
        this.millasDisponibles = this.millasDisponibles + cant;
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

    // devuelve la referencia al usuario con el que tuvo transferencia (complejidad O(1))
    public Usuario getCompaTransferencia() {
        return this.compaTransferencia;
    }

    // devuelve true si el usuario cumple que es exclisivo en O(1)
    public boolean esTransferidorInvalido() {
        return this.esTransferidorInvalido;
    }

    //devuelve categoria segun millas disponibles (complejidad O(1))
    public String uConsultarCategoria() {
        if (this.millasDisponibles < 10000.0) {
            return "Bronce";
        } else if (this.millasDisponibles < 50000.0) {
            return "Plata";
        } else if (this.millasDisponibles < 100000.0) {
            return "Oro";
        } else {
            return "Platino";
        }
    }

    // para consultar el factorCategoria
    public double factorCategoria() {
        if (this.montoMillasHist < 10000.0) {
            return 1.0; //bronce
        } else if (this.montoMillasHist < 50000.0) {
            return 1.5; // plata
        } else if (this.montoMillasHist < 1000000.0) {
            return 2.0; // oro 
        } else {
            return 0.0; // platino, como alcanzo el maximno, su factor paso a ser 0.0factor paso a ser 0.0
        }
    }
    
    // compara las millas disponibles de los usuarios para ordenar 
    // el heap por mayor monto de millas disponibles, si hay un empate,
    // se compara por id de usuario (complejidad O(1))
    @Override
    public int compareTo(Usuario otro) {
        if (Double.compare(this.millasDisponibles, otro.millasDisponibles) != 0) {
            return Double.compare(this.millasDisponibles, otro.millasDisponibles);
        } else {
            return Integer.compare(this.id, otro.id);
        }
    }
}
