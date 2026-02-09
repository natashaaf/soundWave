package modelo.plataforma;

import enums.TipoAnuncio;
import modelo.contenido.Contenido;

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
    }

    // Constructores basico
    public Anuncio(String empresa, TipoAnuncio tipo, double presupuesto){
        this(empresa, tipo, presupuesto, "");
    }


    // Getters and setters
    public String getId() {
        return id;}

    public void setId(String id) {
        this.id = id;}

    public String getEmpresa() {
        return empresa;}

    public void setEmpresa(String empresa) {
        this.empresa = empresa;}

    public int getDuracionSegundos() {
        return duracionSegundos;}

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;}

    public String getAudioURL() {
        return audioURL;}

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;}

    public TipoAnuncio getTipo() {
        return tipo;}

    public void setTipo(TipoAnuncio tipo) {
        this.tipo = tipo;}

    public int getImpresiones() {
        return impresiones;}

    public void setImpresiones(int impresiones) {
        this.impresiones = impresiones;}

    public double getPresupuesto() {
        return presupuesto;}

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;}

    public boolean isActivo() {
        return activo;}

    public void setActivo(boolean activo) {
        this.activo = activo;}

    // Métodos
    public void reproducir() {
        if (puedeMostrarse()) {
            System.out.println("Reproduciendo anuncio de: " + this.empresa);
            registrarImpresion();
        } else {
            desactivar();
            System.out.println("Anuncio no disponible.");
        }
    }

    public void registrarImpresion(){
        this.impresiones++;
        if(!puedeMostrarse()){
            desactivar();
        }
    }
    public double calcularCostoPorImpresion() {
        // Obtenemos el costo desde el Enum TipoAnuncio
        return this.tipo.getCostoPorImpresion();
    }
    public double calcularCostoTotal() {
        return this.impresiones * calcularCostoPorImpresion();
    }

    public int calcularImpresionesRestantes() {
        double presupuestoRestante = this.presupuesto - calcularCostoTotal();
        if (presupuestoRestante <= 0) return 0;
        // Calculamos cuántas impresiones completas podemos pagar aún
        return (int) (presupuestoRestante / calcularCostoPorImpresion());
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    public boolean puedeMostrarse() {
        // Debe estar activo y tener presupuesto para al menos una impresión más
        return this.activo && (this.presupuesto - calcularCostoTotal() >= calcularCostoPorImpresion());
    }

    // Overrides

    @Override
    public String toString() {
        return "Anuncio{" +
                "id='" + id + '\'' +
                ", empresa='" + empresa + '\'' +
                ", duracionSegundos=" + duracionSegundos +
                ", audioURL='" + audioURL + '\'' +
                ", tipo=" + tipo +
                ", impresiones=" + impresiones +
                ", presupuesto=" + presupuesto +
                ", activo=" + activo +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Anuncio anuncio = (Anuncio) obj;
        return id.equals(anuncio.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}