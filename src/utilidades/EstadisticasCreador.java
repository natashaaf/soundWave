package utilidades;

import enums.CategoriaPodcast;
import modelo.artistas.Creador;
import modelo.contenido.Podcast;

import java.util.ArrayList;
import java.util.HashMap;

public class EstadisticasCreador {
    Creador creador;
    private int totalEpisodios;
    private long totalReproducciones;
    private double promedioReproducciones;
    private int totalSuscriptores;
    private int totalLikes;
    private int duracionTotalSegundos;
    private Podcast episodioMasPopular;
    private HashMap<Integer, Integer> episodiosPorTemporada;

    //construtor

//    public EstadisticasCreador(Creador creador, int totalEpisodios, long totalReproducciones, double promedioReproducciones, int totalSuscriptores, int totalLikes, int duracionTotalSegundos, Podcast episodioMasPopular, HashMap<Integer, Integer> episodiosPorTemporada) {
//        this.creador = creador;
//        this.totalEpisodios = totalEpisodios;
//        this.totalReproducciones = totalReproducciones;
//        this.promedioReproducciones = promedioReproducciones;
//        this.totalSuscriptores = totalSuscriptores;
//        this.totalLikes = totalLikes;
//        this.duracionTotalSegundos = duracionTotalSegundos;
//        this.episodioMasPopular = episodioMasPopular;
//        this.episodiosPorTemporada = episodiosPorTemporada;
//    }

    public EstadisticasCreador() {

    }


    //getter and setter

    // Métodos

    public int getDuracion() {
        return this.duracionTotalSegundos;
    }






    public String generarReporte() {
        return "utilidades.EstadisticasCreador{" +
                "totalEpisodios=" + totalEpisodios +
                ", totalReproducciones=" + totalReproducciones +
                ", promedioReproducciones=" + promedioReproducciones +
                ", episodioMasPopular=" + episodioMasPopular +
                '}';
    }

}
