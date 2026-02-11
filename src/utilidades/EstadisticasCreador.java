package utilidades;

import modelo.artistas.Creador;
import modelo.contenido.Podcast;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de procesar y gestionar las métricas de un creador de contenido.
 * Implementada según la sección 8.2 de la documentación técnica.
 */
public class EstadisticasCreador {

    // Atributos privados
    private Creador creador;
    private int totalEpisodios;
    private int totalReproducciones;
    private double promedioReproducciones;
    private int totalSuscriptores;
    private int totalLikes;
    private int duracionTotalSegundos;
    private Podcast episodioMasPopular;
    private HashMap<Integer, Integer> episodiosPorTemporada;

    // Constructor
    public EstadisticasCreador(Creador creador) {
        this.creador = creador;
        this.episodiosPorTemporada = new HashMap<>();
        // Inicializa y calcula estadísticas.
        calcularEstadisticas();
    }

    // Métodos privados
    private void calcularEstadisticas() {
        if (creador == null || creador.getEpisodios() == null) {
            return;
        }

        this.totalEpisodios = creador.getEpisodios().size();
        this.totalSuscriptores = creador.getSuscriptores();
        this.totalReproducciones = 0;
        this.totalLikes = 0;
        this.duracionTotalSegundos = 0;
        this.episodiosPorTemporada = new HashMap<>();
        
        Podcast popular = null;

        for (Podcast p : creador.getEpisodios()) {
            this.totalReproducciones += p.getReproducciones();
            this.totalLikes += p.getLikes();
            this.duracionTotalSegundos += p.getDuracionSegundos();

            // Episodios por temporada
            int temp = p.getTemporada();
            this.episodiosPorTemporada.put(temp, this.episodiosPorTemporada.getOrDefault(temp, 0) + 1);

            // Episodio más popular
            if (popular == null || p.getReproducciones() > popular.getReproducciones()) {
                popular = p;
            }
        }
        this.episodioMasPopular = popular;

        if (this.totalEpisodios > 0) {
            this.promedioReproducciones = (double) this.totalReproducciones / this.totalEpisodios;
        } else {
            this.promedioReproducciones = 0.0;
        }
    }

    private String formatearDuracion(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segs);
    }

    // Métodos públicos
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estadísticas del Creador: ").append(creador != null ? creador.getNombre() : "N/A").append("\n");
        sb.append("Total Episodios: ").append(totalEpisodios).append("\n");
        sb.append("Total Reproducciones: ").append(totalReproducciones).append("\n");
        sb.append("Promedio Reproducciones: ").append(String.format("%.2f", promedioReproducciones)).append("\n");
        sb.append("Total Suscriptores: ").append(totalSuscriptores).append("\n");
        sb.append("Total Likes: ").append(totalLikes).append("\n");
        sb.append("Duración Total: ").append(formatearDuracion(duracionTotalSegundos)).append("\n");
        sb.append("Episodio Más Popular: ").append(episodioMasPopular != null ? episodioMasPopular.getTitulo() : "N/A").append("\n");
        sb.append("Episodios Por Temporada: ").append(episodiosPorTemporada).append("\n");
        sb.append("Engagement: ").append(String.format("%.2f", calcularEngagement())).append("%\n");
        sb.append("Crecimiento Mensual Estimado: ").append(estimarCrecimientoMensual()).append("\n");
        return sb.toString();
    }

    public double calcularEngagement() {
        if (totalReproducciones == 0) return 0.0;
        return ((double) totalLikes / totalReproducciones) * 100;
    }

    public int estimarCrecimientoMensual() {
        // Estimación simple: 5% de suscriptores actuales
        return (int) (totalSuscriptores * 0.05);
    }

    // Getters
    public Creador getCreador() {
        return creador;
    }

    public int getTotalEpisodios() {
        return totalEpisodios;
    }

    public int getTotalReproducciones() {
        return totalReproducciones;
    }

    public double getPromedioReproducciones() {
        return promedioReproducciones;
    }

    public int getTotalSuscriptores() {
        return totalSuscriptores;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getDuracionTotalSegundos() {
        return duracionTotalSegundos;
    }

    public Podcast getEpisodioMasPopular() {
        return episodioMasPopular;
    }

    public HashMap<Integer, Integer> getEpisodiosPorTemporada() {
        return new HashMap<>(episodiosPorTemporada);
    }

    // Overrides
    @Override
    public String toString() {
        return "EstadisticasCreador{" +
                "creador=" + (creador != null ? creador.getNombre() : "N/A") +
                ", totalEpisodios=" + totalEpisodios +
                ", totalReproducciones=" + totalReproducciones +
                ", promedioRP=" + promedioReproducciones +
                ", totalSuscriptores=" + totalSuscriptores +
                ", totalLikes=" + totalLikes +
                ", duracionTotal=" + duracionTotalSegundos +
                '}';
    }
}