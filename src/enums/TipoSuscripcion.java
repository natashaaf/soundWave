package enums;

public enum TipoSuscripcion {
    GRATUITO(0.0, false, 50, false),
    PREMIUM(9.99, true, -1, true),
    FAMILIAR(14.99, true, -1, true),
    ESTUDIANTE(4.99, true, -1, true);

    //Atributos
    private double precioMensual;
    private boolean sinAnuncios;
    private int limiteReproducciones;
    private boolean descargasOffline;
    private String nombre;

    // Constructores

    TipoSuscripcion(double precioMensual, boolean sinAnuncios, int limiteReproducciones, boolean descargasOffline, String nombre) {
        this.sinAnuncios = sinAnuncios;
        this.precioMensual = precioMensual;
        this.limiteReproducciones = limiteReproducciones;
        this.descargasOffline = descargasOffline;
        this.nombre = nombre;
    }

    // Getters and setters


    // Metodo


    public double getPrecioMensual() {
        return precioMensual;
    }

    public boolean isSinAnuncios() {
        return sinAnuncios;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public boolean tieneReproduccionesIlimitadas() {
        if (limiteReproducciones == -1)
            return true;
        else
            return false;
    }
    @Override
    public String toString() {
        return "TipoSuscripcion{" +
                "nombre='" + nombre + '\'' +
                ", precioMensual=" + precioMensual +
                '}';
    }
}



