package aed.tp2;

public class Promocion {
    private String nombre;
    private double factor;
    private boolean tieneTope;
    private double tope;
    private double millasTotales;

    //constructor para promociones SIN tope
    public Promocion(String nombre, double factor) {
        this.nombre = nombre;
        this.factor = factor;
        this.tieneTope = false;
        this.tope = 0.0;
        this.millasTotales = 0.0;
    }

    //constructor para promociones CON tope
    public Promocion(String nombre, double factor, double tope) {
        this(nombre, factor);
        this.tieneTope = true;
        this.tope = tope;
    }

    // devuelve el nombre de la promocion en O(1)
    public String getNombre() {
        return this.nombre;
    }

    // devuelve el factor en O(1)
    public double getFactor() {
        return this.factor;
    }
    
    // devuelve si la promocion tiene tope en O(1)
    public boolean tieneTope() {
        return this.tieneTope;
    }

    // devuelve el tope de la promocion en O(1)
    public double getTope() {
        return this.tope;
    }

    // devuelve la cantidad de millas acumuladas por la promocion en O(1)
    public double getMillasTotales() {
        return this.millasTotales;
    }

    //para sumar la cantidad de millas por promocion (complejidad O(1))
    public void sumaMillasTotales(double cant) {
        this.millasTotales += cant;
    }

    //para checkear que la promocion alcanzo tope (complejidad O(1)), y si tiene la da de baja
    public boolean alcanzaTope() {
        return this.tieneTope && this.millasTotales >= this.tope;
    }

}