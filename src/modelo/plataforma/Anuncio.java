package modelo.plataforma;

import enums.TipoAnuncio;

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
    public Anuncio(String id, String empresa, int duracionSegundos, String audioURL, TipoAnuncio tipo, int impresiones, double presupuesto, boolean activo) {
        this.id = id;
        this.empresa = empresa;
        this.duracionSegundos = duracionSegundos;
        this.audioURL = audioURL;
        this.tipo = tipo;
        this.impresiones = impresiones;
        this.presupuesto = presupuesto;
        this.activo = activo;
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

    // Metodos
    public void reproducir(){}
    public void registrarImpresion(){}
    public double calcularCostoPorImpresion(){
        return 0;
        //TODO
    }
    public void desactivar(){}
}
