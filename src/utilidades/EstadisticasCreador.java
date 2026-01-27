package utilidades;

import enums.CategoriaPodcast;

import java.util.HashMap;

public class EstadisticasCreador {
    private int totalEpisodios;
    private long totalReproducciones;
    private double promedioReproducciones;
    private CategoriaPodcast episodioMasPopular;
    private HashMap categoriasFrecuentes;

    //construtor
    public EstadisticasCreador(int totalEpisodios, long totalReproducciones, double promedioReproducciones, CategoriaPodcast episodioMasPopular, HashMap categoriasFrecuentes) {
        this.totalEpisodios = totalEpisodios;
        this.totalReproducciones = totalReproducciones;
        this.promedioReproducciones = promedioReproducciones;
        this.episodioMasPopular = episodioMasPopular;
        this.categoriasFrecuentes = categoriasFrecuentes;
    }
    //getter and setter

    public int getTotalEpisodios() {
        return totalEpisodios;
    }

    public void setTotalEpisodios(int totalEpisodios) {
        this.totalEpisodios = totalEpisodios;
    }

    public long getTotalReproducciones() {
        return totalReproducciones;
    }

    public void setTotalReproducciones(long totalReproducciones) {
        this.totalReproducciones = totalReproducciones;
    }

    public double getPromedioReproducciones() {
        return promedioReproducciones;
    }

    public void setPromedioReproducciones(double promedioReproducciones) {
        this.promedioReproducciones = promedioReproducciones;
    }

    public CategoriaPodcast getEpisodioMasPopular() {
        return episodioMasPopular;
    }

    public void setEpisodioMasPopular(CategoriaPodcast episodioMasPopular) {
        this.episodioMasPopular = episodioMasPopular;
    }

    public HashMap getCategoriasFrecuentes() {
        return categoriasFrecuentes;
    }

    public void setCategoriasFrecuentes(HashMap categoriasFrecuentes) {
        this.categoriasFrecuentes = categoriasFrecuentes;
    }

    //metodo
    public String generarReporte() {
        return "utilidades.EstadisticasCreador{" +
                "totalEpisodios=" + totalEpisodios +
                ", totalReproducciones=" + totalReproducciones +
                ", promedioReproducciones=" + promedioReproducciones +
                ", episodioMasPopular=" + episodioMasPopular +
                ", categoriasFrecuentes=" + categoriasFrecuentes +
                '}';
    }

}
