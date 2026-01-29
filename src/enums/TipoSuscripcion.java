package enums;

public enum TipoSuscripcion {
    GRATUITO,
    PREMIUM,
    FAMILIAR,
    ESTUDIANTE;

    private double precioMensual;
    private boolean sinAnuncios;
    private int limiteReproducciones;
    private boolean descargasOffline;

    // Construtores
    TipoSuscripcion() {
    }
    // Getters and setters
    public double getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    public boolean isSinAnuncios() {
        return sinAnuncios;
    }

    public void setSinAnuncios(boolean sinAnuncios) {
        this.sinAnuncios = sinAnuncios;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public void setLimiteReproducciones(int limiteReproducciones) {
        this.limiteReproducciones = limiteReproducciones;
    }

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }
}
