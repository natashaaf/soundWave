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
    public EstadisticasCreador(Creador creador) {
        this.creador = creador;
        // Inicializa y calcula estadísticas
        this.calcularEstadisticas();
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
                ", categoriasFrecuentes=" + categoriasFrecuentes +
                '}';
    }

}
