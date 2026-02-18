package modelo.plataforma;

import enums.TipoAnuncio;
// Error en producción...

/**
 * Gestiona los anuncios para usuarios con plan gratuito.
 * Se encarga de controlar la duración, el presupuesto disponible y
 * cuántas veces puede reproducirse antes de agotarse el saldo.
 */
public class Anuncio {

    // Atributos
    private String id;
    private String empresa;
    private int duracionSegundos;
    private String audioURL;
    private TipoAnuncio tipo;
    private int impresiones;
    private double presupuesto;
    private boolean activo;

    // Constructores

    public Anuncio(String empresa, TipoAnuncio tipo, double presupuesto, String audioURL) {
        this.empresa = empresa;
        this.audioURL = audioURL;
        this.tipo = tipo;
        this.presupuesto = presupuesto;
        this.activo = true; // Por defecto, el anuncio empieza funcionando
    }

    public Anuncio(String empresa, TipoAnuncio tipo, double presupuesto){
        this(empresa, tipo, presupuesto, "");
    }

    // Getters and setters

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public TipoAnuncio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnuncio tipo) {
        this.tipo = tipo;
    }

    public int getImpresiones() {
        return impresiones;
    }

    public void setImpresiones(int impresiones) {
        this.impresiones = impresiones;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Métodos


    public void reproducir() {}

    public void registrarImpresion(){}

    /** Consulta el precio de una sola reproducción según el tipo de anuncio. */
    public double calcularCostoPorImpresion() {
        return this.tipo.getCostoPorImpresion();
    }

    /** Multiplica las veces que sonó el anuncio por lo que cuesta cada una.*/
    public double calcularCostoTotal() {
        return this.impresiones * calcularCostoPorImpresion();
    }

    public int calcularImpresionesRestantes() {
        return 0;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    /**
     * Revisa si el anuncio está habilitado y si el dinero restante alcanza para una vez más.
     */
    public boolean puedeMostrarse() {
        return this.activo && (this.presupuesto - calcularCostoTotal() >= calcularCostoPorImpresion());
    }

    // Overrides

    @Override
    public String toString() {
        return "Anuncio: " + empresa + " [" + tipo + "] - Vistas: " + impresiones;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Anuncio anuncio = (Anuncio) obj;
        return id != null && id.equals(anuncio.id);
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }
}